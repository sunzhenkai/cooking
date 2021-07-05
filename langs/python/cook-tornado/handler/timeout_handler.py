# coding: utf-8
import math
from abc import ABC
from datetime import datetime

from handler.base_handler import BaseHandler
from handler.payload import *


class TimeoutHandler(BaseHandler, ABC):
    # @coroutine
    async def get(self):
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
        now = datetime.now()
        ts = math.floor(datetime.timestamp(now) * 1000)
        # print(ts, self.request.arguments.get('ts')[0])
        print('pre-payload', ts - int(self.request.arguments.get('ts')[0]))
        await async_payload(self.request)
        self.write({'msg': 'ok',
                    #             'r': r
                    })
