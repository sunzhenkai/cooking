# coding: utf-8
import math
from abc import ABC
from datetime import datetime

from handler.base_handler import BaseHandler


class BlockMainIOLoopHandler(BaseHandler, ABC):
    __mem_space__ = []

    def heavy_blocking_task(self):
        for i in range(1000000000):
            pass
        print("block: heavy task done")

    async def get(self):
        print(self.request.request_time())
        print(self.request.connection)
        print(self.__process__.memory_info())
        print(self.__process__.memory_info().rss)
        now = datetime.now()
        ts = math.floor(datetime.timestamp(now) * 1000)
        print('pre-payload', ts - int(self.request.arguments.get('ts')[0]))

        self.heavy_blocking_task()

        def callback():
            print('done at', self.request.request_time())
            self.write({'msg': 'ok'})

        callback()
