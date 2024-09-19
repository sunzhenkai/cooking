# coding: utf-8
import asyncio
import concurrent.futures
import datetime
import math
from abc import ABC
from concurrent.futures import ThreadPoolExecutor

import tornado
from tornado.gen import with_timeout

from handler.base_handler import BaseHandler


class ThreadPoolTimeoutHandler(BaseHandler, ABC):
    __mem_space__ = []
    executor = ThreadPoolExecutor(max_workers=1)

    # @run_on_executor
    def heavy_blocking_task(self):
        # tornado.gen.sleep(3)
        print("pool: heavy task start")
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

        # task = self.heavy_blocking_task()
        ft = self.executor.submit(self.heavy_blocking_task)
        # concurrent.futures.wait([ft], timeout=2)
        # if not ft.done():
        #     ft.cancel()
        # print(ft)
        try:
            await with_timeout(datetime.timedelta(seconds=2), ft, quiet_exceptions=tornado.gen.TimeoutError)
        except tornado.gen.TimeoutError:
            # ft = asyncio.Future()
            ft.cancel()
        # task.cancel('timeout')
        # print('cancel: ', task.cancel())
        # ft.cancel()
        # print('task cancelled:', task.cancelled(), ', task done:', task.done())
        # print('task result:', task.result())
        # print('task cancelled:', ft.cancelled(), ', task done:', ft.done())
        # print('task type:', type(task))

        print('done at', self.request.request_time())
        self.write({'msg': 'ok'})
