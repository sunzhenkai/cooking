# coding: utf-8
import asyncio
import threading
import time
from datetime import datetime
from typing import Callable


class Scheduler:
    cache: set[str] = set()

    @classmethod
    async def _do_schedule(cls, name: str, delay: int, interval: int, cb: Callable, args, kwargs):
        await asyncio.sleep(delay)
        while name in cls.cache:
            try:
                cb(*args, **kwargs)
            except Exception as e:
                print('execute target failed, e=', e)
            await asyncio.sleep(interval)

    @classmethod
    def _schedule_wrapper(cls, name: str, delay: int, interval: int, cb: Callable, args, kwargs):
        asyncio.run(cls._do_schedule(name, delay, interval, cb, args, kwargs))

    @classmethod
    def schedule(cls, name: str, delay: int, interval: int, cb: Callable, *args, **kwargs):
        assert name not in cls.cache, 'duplicate scheduler with name ' + name
        try:
            threading.Thread(target=cls._schedule_wrapper,
                             args=(name, delay, interval, cb, args, kwargs),
                             daemon=True).start()
        except (InterruptedError, KeyboardInterrupt, Exception, SystemExit):
            print('.....')
            pass
        cls.cache.add(name)

    @classmethod
    def stop(cls, name: str):
        if name in cls.cache:
            cls.cache.remove(name)


def cbk(a, b, c):
    print('execute at', datetime.now(), 'with args:', (a, b, c))


if __name__ == '__main__':
    Scheduler.schedule('first', 1, 1, cbk, 'a', 'b', c='c')
    Scheduler.schedule('second', 1, 1, cbk, 'd', 'e', c='f')
    time.sleep(5)
    Scheduler.stop('first')
    time.sleep(10)
