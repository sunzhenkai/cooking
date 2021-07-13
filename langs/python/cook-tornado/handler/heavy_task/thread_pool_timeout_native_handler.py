# coding: utf-8
import asyncio
import datetime
import math
from abc import ABC
from concurrent.futures import ThreadPoolExecutor
import concurrent

import tornado
from tornado.gen import with_timeout

from handler.base_handler import BaseHandler


class NativeThreadPoolTimeoutHandler(BaseHandler, ABC):
    __mem_space__ = []
    executor = ThreadPoolExecutor(max_workers=1)

    def heavy_blocking_task(self):
        # await asyncio.sleep(3)
        for i in range(300000000):
            pass
        print("pool: heavy task done")

    async def get(self):
        print(self.request.request_time())
        print(self.request.connection)
        print(self.__process__.memory_info())
        print(self.__process__.memory_info().rss)
        now = datetime.datetime.now()
        ts = math.floor(datetime.datetime.timestamp(now) * 1000)
        print('pre-payload', ts - int(self.request.arguments.get('ts')[0]))

        # ft = asyncio.ensure_future(self.heavy_blocking_task())
        ft = self.executor.submit(self.heavy_blocking_task)
        # task = self.heavy_blocking_task()
        # ft = self.executor.submit(task)
        # ft = self.executor.submit(self.heavy_blocking_task)
        try:
            # concurrent.futures.wait()
            # await asyncio.wait([ft], timeout=0.5)
            await with_timeout(datetime.timedelta(seconds=0.5), ft, quiet_exceptions=tornado.gen.TimeoutError)
        except tornado.gen.TimeoutError:
            ft.cancel()
            print('cancel:', ft.cancel())
            print('running:', ', canceled:', ft.cancelled())
            pass
            # ft = asyncio.Future()
            # ft.cancelled()
            # task.cancel('timeout')
            # print('cancel: ', task)
            # ft.cancel()
            # print('task cancelled:', task.cancelled(), ', task done:', task.done())
            # print('task result:', task.result())
            # print('task cancelled:', ft.cancelled(), ', task done:', ft.done())
            # print('task type:', type(task))

        print('done at', self.request.request_time())
        self.write({'msg': 'ok'})
