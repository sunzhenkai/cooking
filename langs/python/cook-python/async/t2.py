# coding: utf-8
import asyncio


async def slp():
    await asyncio.sleep(1)


async def tst():
    # done
    try:
        await asyncio.wait_for(slp(), timeout=1.1)
        print('done')
    except Exception:
        print('failed')

    # failed
    try:
        await asyncio.wait_for(slp(), timeout=0.9)
        print('done')
    except Exception:
        print('failed')


if __name__ == '__main__':
    asyncio.run(tst())
