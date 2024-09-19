# coding: utf-8
import datetime
import math
import random
from abc import ABC
from concurrent.futures import ThreadPoolExecutor

import tornado
from tornado.gen import with_timeout

from handler.base_handler import BaseHandler
from utils.future_utils import ensure_future


class TestThreadPoolTimeoutHandler(BaseHandler, ABC):
    __mem_space__ = []
    executor = ThreadPoolExecutor(max_workers=1)

    # @run_on_executor
    @tornado.gen.coroutine
    def heavy_blocking_task(self):
        # lp = random.randint(0, 100000000)
        lp = 10
        print("pool: heavy task start, lp:", lp)
        # yield tornado.gen.sleep(3)
        # yield tornado.gen.sleep(3000)
        for i in range(lp):
            pass
        print("pool: heavy task done")

    @tornado.gen.coroutine
    def get(self):
        print(self.request.request_time())
        print(self.request.connection)
        print(self.__process__.memory_info())
        print(self.__process__.memory_info().rss)
        now = datetime.datetime.now()
        ts = math.floor(datetime.datetime.timestamp(now) * 1000)
        print('pre-payload', ts - int(self.request.arguments.get('ts')[0]))

        # ft = asyncio.ensure_future(self.heavy_blocking_task())
        # ft = self.executor.submit(self.heavy_blocking_task)
        # task = self.heavy_blocking_task()
        # ft = self.executor.submit(task)
        # ft = self.executor.submit(self.heavy_blocking_task)
        # rt = loop.run_in_executor(self.executor, self.heavy_blocking_task)
        # crt = self.heavy_blocking_task()
        # loop_main = asyncio.get_event_loop()

        # loop.run_in_executor()
        # def wait_ft(f):
        #     loop = asyncio.get_event_loop()
        #     # print(hex(id(loop)), hex(id(loop_main)))
        #     crt = f()
        #     print('wait ft:', crt)
        #     loop.run_until_complete(crt)

        ft = ensure_future(self.heavy_blocking_task, self.executor)
        # ft = self.executor.submit(wait_ft, self.heavy_blocking_task)
        # concurrent.futures.wait([ft], timeout=1)
        # if not ft.done():
        #     ft.cancel()
        # print('....', inspect.isawaitable(self.heavy_blocking_task))
        # print('....', inspect.iscoroutinefunction(self.heavy_blocking_task))
        try:
            yield with_timeout(datetime.timedelta(seconds=1), future=ft, quiet_exceptions=tornado.gen.TimeoutError)
            self.write({'msg': 'ok'})
        except tornado.gen.TimeoutError:
            ft.cancel()
            self.write({'msg': 'timeout'})

        # print(crt, type(crt))
        # yield with_timeout(datetime.timedelta(seconds=1), ft)
        # rt = self.heavy_blocking_task()
        # await asyncio.wait_for(rt, timeout=1)
        # print(rt)
        # concurrent.futures.wait()
        # await asyncio.wait([ft], timeout=0.5)
        # await with_timeout(datetime.timedelta(seconds=0.5), ft, quiet_exceptions=tornado.gen.TimeoutError)
        # except tornado.gen.TimeoutError:
        # print(ft.cancel())
        # print('cancel:', ft.cancel())
        # print('running:', ', canceled:', ft.cancelled())
        # print('timeout error')
        # pass
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
