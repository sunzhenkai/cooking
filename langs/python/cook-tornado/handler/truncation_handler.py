# coding: utf-8
import uuid
from abc import ABC

from tornado.web import HTTPError

from handler.base_handler import BaseHandler


class TruncationHandler(BaseHandler, ABC):
    __mem_space__ = []

    def initialize(self):
        super(TruncationHandler, self).initialize()
        print('TruncationHandler initialize')

    def prepare(self):
        super(TruncationHandler, self).prepare()
        print('TruncationHandler prepare')

    async def get(self):
        print(self.__process__.memory_info().rss)
        mem = str(self.__process__.memory_info().rss/1024/1024) + 'MB'
        if self.__process__.memory_info().rss > 1024 * 1024 * 100:
            raise HTTPError(500, 'memory overflow:', mem)
        self.write({'msg': 'ok', 'mem': mem})
        for i in range(100000):
            self.__mem_space__.append(uuid.uuid1())
