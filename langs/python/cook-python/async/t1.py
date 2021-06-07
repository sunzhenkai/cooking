# coding: utf-8
import asyncio
import threading
import time

cnt = 0
flag = True


def set_flag(v):
    global flag
    flag = v


def reset_cnt():
    global cnt
    cnt = 0


def sync_payload():
    global cnt
    time.sleep(1)
    cnt = cnt + 1


async def async_payload():
    global cnt
    await asyncio.sleep(1)
    cnt = cnt + 1


def do_sync_payload():
    sync_payload()
    sync_payload()
    sync_payload()


async def do_async_payload():
    await asyncio.gather(asyncio.create_task(async_payload()),
                         asyncio.create_task(async_payload()),
                         asyncio.create_task(async_payload()))
    # tasks = [asyncio.create_task(async_payload()),
    #          asyncio.create_task(async_payload()),
    #          asyncio.create_task(async_payload())
    #          ]
    # for task in tasks:
    #     await task


def sync_loop():
    reset_cnt()
    set_flag(True)
    threading.Timer(3, set_flag, args=[False]).start()
    while flag:
        do_sync_payload()
    print('sync cnt:', cnt)


def async_loop():
    reset_cnt()
    set_flag(True)
    threading.Timer(3, set_flag, args=[False]).start()
    while flag:
        asyncio.run(do_async_payload())
    print('async cnt:', cnt)


if __name__ == '__main__':
    async_loop()
    sync_loop()
