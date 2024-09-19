# coding: utf-8
import asyncio
import uuid

from tornado import gen
from tornado.concurrent import run_on_executor


@gen.coroutine
def payload(req):
    m = {
        1: {},
        2: {},
        3: {},
        4: {}
    }
    lop = 3000 * 3 + 1
    for i in range(lop):
        m[1][i] = str(uuid.uuid1()) + str(uuid.uuid1())
        m[2][i] = uuid.uuid1()
        m[3][i] = uuid.uuid1()
        m[4][i] = uuid.uuid1()

        # if i % 3000 == 0:
        #     print(req.arguments)
    return 'done'


async def async_payload(req):
    payload(req)
    return 'done'


@gen.coroutine
def tornado_payload():
    lop = 10000 * 20000
    for i in range(lop):
        pass
    yield 'done'


@gen.coroutine
def tornado_async_payload():
    yield payload()
    # yield gen.sleep(5)
    return 'done'


async def sleep_payload():
    await asyncio.sleep(.06)


@run_on_executor
def heavy_blocking_task():
    for i in range(1000000000):
        pass
