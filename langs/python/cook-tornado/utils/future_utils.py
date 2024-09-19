# coding: utf-8
import asyncio


def ensure_loop():
    loop = asyncio.get_event_loop()
    if loop is None:
        loop = asyncio.new_event_loop()
        asyncio.set_event_loop(loop)
    return loop


def ensure_future(fn, executor, *args, **kwargs):
    def wrapper(f, ag, kwa):
        result = f(*ag, **kwa)
        if asyncio.isfuture(result):
            loop = ensure_loop()
            result = loop.run_until_complete(result)
        return result

    return executor.submit(wrapper, fn, args, kwargs)
