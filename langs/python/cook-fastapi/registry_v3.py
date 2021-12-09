# coding: utf-8
import functools
import os
import signal
import threading
from multiprocessing import Lock
import time

# import sched

# s = sched.scheduler(time.time, time.sleep)
closed = False


def timer(duration: int = 1):
    def loop(fc, *args, **kwargs):
        global closed
        fc(*args, **kwargs)
        if not closed:
            tm = threading.Timer(duration, loop, args=(fc, *args), kwargs=kwargs)
            tm.start()

    def decorator(f):
        @functools.wraps(f)
        def wrapper(*args, **kwargs):
            loop(f, *args, **kwargs)

        return wrapper

    return decorator


@timer(duration=1)
def reg():
    print("reg")


reg()


def start():
    print("in work %d - %d" % (os.getpid(), os.getppid()))
    # thread = threading.Thread(target=reg)
    # thread.start()
    signal.signal(signal.SIGTERM, close)
    signal.signal(signal.SIGKILL, close)
    # signal.signal(signal.SIGINT, close)


def close(s, f):
    global closed
    closed = True
    time.sleep(1)
    print('close done')
