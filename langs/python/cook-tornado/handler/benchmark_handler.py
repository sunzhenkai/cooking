# coding: utf-8
import asyncio
import time
from abc import ABC

import tornado.gen

from handler.base_handler import BaseHandler

TIME_TO_SLEEP = .3


class TornadoCoroutineHandler(BaseHandler, ABC):
    @tornado.gen.coroutine
    def get(self):
        yield time.sleep(TIME_TO_SLEEP)
        self.write({'msg': 'ok'})


class NativeCoroutineHandler(BaseHandler, ABC):
    async def get(self):
        await asyncio.sleep(TIME_TO_SLEEP)
        self.write({'msg': 'ok'})


class NativeCoroutineTaskHandler(BaseHandler, ABC):
    async def get(self):
        await asyncio.create_task(asyncio.sleep(TIME_TO_SLEEP))
        self.write({'msg': 'ok'})


class NativeCoroutineTaskTruncationHandler(BaseHandler, ABC):
    async def _do_get(self):
        await asyncio.sleep(TIME_TO_SLEEP)

    async def get(self):
        await asyncio.wait([self._do_get()], timeout=0.1)
        self.write({'msg': 'ok'})


class NoCoroutineHandler(BaseHandler, ABC):
    def get(self):
        time.sleep(TIME_TO_SLEEP)
        self.write({'msg': 'ok'})
