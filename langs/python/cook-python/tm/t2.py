# coding: utf-8
import asyncio
import logging


def payload():
    print('enter')
    logging.info('enter')
    for i in range(10000 * 20000):
        pass
    print('done')
    return 'done'


async def async_payload():
    print('alive', flush=True)
    logging.info('enter')
    payload()
    print('done')
    return 'done'

task = asyncio.ensure_future(async_payload())
asyncio.wait_for(task, 1)

# loop = asyncio.get_event_loop()
# fut = asyncio.run_coroutine_threadsafe(async_payload(), loop)
# try:
#     r = fut.result()
#     print(r)
# except Exception as e:
#     print(e)

# asyncio.run_coroutine_threadsafe(asyncio.wait_for(async_payload(), 10000000), loop)

# payload()
