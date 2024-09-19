# coding: utf-8
from abc import ABC
from concurrent.futures import ThreadPoolExecutor

from handler.base_handler import BaseHandler

EXECUTOR = ThreadPoolExecutor(max_workers=4)


class DebugHandler(BaseHandler, ABC):
    __mem_space__ = []
    executor = ThreadPoolExecutor(max_workers=4)

    async def get(self):
        self.write({
            'ts': self.get_query_argument('ts'),
            'tb': self.get_query_argument('tb')
        })
