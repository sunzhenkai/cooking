# coding: utf-8
import asyncio
import time
from abc import ABC

import tornado.gen

from handler.base_handler import BaseHandler

TIME_TO_SLEEP = .3


def payload():
    for i in range(10000 * 200):
        pass


async def async_payload():
    payload()


class TornadoCoroutineHandler(BaseHandler, ABC):
    @tornado.gen.coroutine
    def get(self):
        yield payload()  # time.sleep(TIME_TO_SLEEP)
        self.write({'msg': 'ok'})


class NativeCoroutineHandler(BaseHandler, ABC):
    async def get(self):
        # await asyncio.sleep(TIME_TO_SLEEP)
        await async_payload()
        self.write({'msg': 'ok'})


class NativeCoroutineTaskHandler(BaseHandler, ABC):
    async def get(self):
        await asyncio.create_task(async_payload())
        self.write({'msg': 'ok'})


class NativeCoroutineTaskTruncationHandler(BaseHandler, ABC):
    async def _do_get(self):
        await async_payload()

    async def get(self):
        await asyncio.wait([self._do_get()], timeout=0.1)
        self.write({'msg': 'ok'})


class NoCoroutineHandler(BaseHandler, ABC):
    def get(self):
        # time.sleep(TIME_TO_SLEEP)
        payload()
        self.write({'msg': 'ok'})
