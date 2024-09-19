# coding: utf-8
import math
from abc import ABC
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime

from handler.base_handler import BaseHandler
from handler.payload import *

EXECUTOR = ThreadPoolExecutor(max_workers=4)


class TimeoutHandler(BaseHandler, ABC):
    __mem_space__ = []
    executor = ThreadPoolExecutor(max_workers=4)

    @run_on_executor
    def heavy_blocking_task(self):
        for i in range(100000000):
            pass
        print("heavy task done")

    # @coroutine
    # @unblock
    async def get(self):
        print(self.request.request_time())
        print(self.request.connection)
        print(self.__process__.memory_info())
        print(self.__process__.memory_info().rss)
        # await gen.with_timeout(timedelta(seconds=1), async_payload())
        # await async_payload()
        # fut = asyncio.run_coroutine_threadsafe(async_payload(), asyncio.get_event_loop())
        # r = fut.result(15)
        # result = yield from asyncio.wait_for(async_payload(), 1)
        # yield gen.with_timeout(time.time() + 1, gen.sleep(5))
        # yield gen.with_timeout(time.time() + 1, tornado_async_payload())
        # await sleep_payload()
        # await sleep_payload()
        # print(self.request.arguments)
        for i in range(10000):
            self.__mem_space__.append(uuid.uuid1())
        now = datetime.now()
        ts = math.floor(datetime.timestamp(now) * 1000)
        # print(ts, self.request.arguments.get('ts')[0])
        print('pre-payload', ts - int(self.request.arguments.get('ts')[0]))

        # await async_payload(self.request)

        await self.heavy_blocking_task()

        def callback():
            print('done', self.request.request_time())
            self.write({'msg': 'ok'})

        # executor
        # EXECUTOR.submit(partial(heavy_blocking_task)).add_done_callback(
        #     lambda future: IOLoop.instance().add_callback(partial(callback))
        # )

        # await asyncio.sleep(1)
        # return {'msg': 'ok'}
        # EXECUTOR.submit(heavy_blocking_task).add_done_callback(callback)
        callback()
