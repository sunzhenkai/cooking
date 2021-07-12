# coding: utf-8
import datetime
import math
from abc import ABC
from concurrent.futures import ThreadPoolExecutor

import tornado
from tornado.gen import with_timeout

from handler.base_handler import BaseHandler
from handler.payload import *


class ThreadPoolTimeoutHandler(BaseHandler, ABC):
    __mem_space__ = []
    executor = ThreadPoolExecutor(max_workers=4)

    @run_on_executor
    def heavy_blocking_task(self):
        for i in range(1000000000):
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

        task = self.heavy_blocking_task()
        await with_timeout(datetime.timedelta(seconds=1), [task], quiet_exceptions=tornado.gen.TimeoutError)

        def callback():
            print('done at', self.request.request_time())
            self.write({'msg': 'ok'})

        callback()
