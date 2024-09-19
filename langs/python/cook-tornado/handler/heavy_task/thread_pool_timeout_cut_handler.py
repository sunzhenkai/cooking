# coding: utf-8
import asyncio
import datetime
import math
from abc import ABC
from concurrent.futures import ThreadPoolExecutor
from threading import Thread

from handler.base_handler import BaseHandler


class CutThreadPoolTimeoutHandler(BaseHandler, ABC):
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

        ect = ThreadPoolExecutor(max_workers=1)

        loop = asyncio.get_event_loop()
        task = loop.run_in_executor(ect, self.heavy_blocking_task)
        await asyncio.wait([task], timeout=1)

        # ft = ect.submit(self.heavy_blocking_task)
        # await asyncio.wait([as], timeout=1)
        # print('submit done:', ft)
        # await asyncio.wait([ft], timeout=1)
        ect.shutdown(wait=False)

        print('done at', self.request.request_time())
        self.write({'msg': 'ok'})
