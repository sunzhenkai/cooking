# coding: utf-8
import asyncio
import concurrent
from concurrent.futures import ThreadPoolExecutor


def payload():
    for i in range(100000000):
        pass
    print('run payload done')


async def do():
    pool = ThreadPoolExecutor(max_workers=1)
    fts = [pool.submit(payload) for _ in range(10)]
    done, pending = concurrent.futures.wait(fts, timeout=5)
    print('done:', done, ', pending:', pending)
    for ft in fts:
        ft.cancel()
    await asyncio.sleep(10)

    pool.shutdown(wait=False)


loop = asyncio.get_event_loop()
loop.run_until_complete(do())
