# coding: utf-8
import math
from abc import ABC
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime

from handler.base_handler import BaseHandler
from handler.payload import *


class ThreadPoolHandler(BaseHandler, ABC):
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
        now = datetime.now()
        ts = math.floor(datetime.timestamp(now) * 1000)
        print('pre-payload', ts - int(self.request.arguments.get('ts')[0]))

        await self.heavy_blocking_task()

        def callback():
            print('done at', self.request.request_time())
            self.write({'msg': 'ok'})

        callback()
