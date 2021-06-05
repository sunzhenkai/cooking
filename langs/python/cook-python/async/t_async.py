# coding: utf-8
import time
import asyncio


async def dsa():
    print('dsa')
    time.sleep(1)


async def dsb():
    print('dsb')
    await dsa()
    time.sleep(1)


async def dsc():
    print('dsc')
    await dsa()
    time.sleep(1)


async def ds():
    fb = dsb()
    fc = dsc()
    await fb
    await fc
    # for i in range(1000000):
    #     pass


# def sa

if __name__ == '__main__':
    asyncio.run(ds())
