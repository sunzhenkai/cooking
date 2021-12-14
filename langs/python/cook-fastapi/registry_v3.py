# coding: utf-8
import functools
import os
import signal
import threading
import time

# import sched

# s = sched.scheduler(time.time, time.sleep)
closed = False


def timer(duration: int = 1):
    def loop(fc, *args, **kwargs):
        global closed
        try:
            while True:
                fc(*args, **kwargs)
                time.sleep(3)
        except KeyboardInterrupt as e:
            print(e)
            raise e
            # print(e)
        # if not closed:
        #     tm = threading.Timer(duration, loop, args=(fc, *args), kwargs=kwargs)
        #     tm.start()

    def decorator(f):
        @functools.wraps(f)
        def wrapper(*args, **kwargs):
            t = threading.Thread(target=loop, daemon=True, args=(f, *args), kwargs=kwargs)
            t.start()
            # loop(f, *args, **kwargs)
            pass

        return wrapper

    return decorator


@timer(duration=1)
def reg():
    print("reg")


# reg()


def start():
    print("in work %d - %d" % (os.getpid(), os.getppid()))
    # thread = threading.Thread(target=reg)
    # thread.start()
    signal.signal(signal.SIGINT, close)
    signal.signal(signal.SIGTERM, close)
    # signal.signal(signal.SIGKILL, close)
    # while not closed:
    #     time.sleep(1)


def close(*args):
    print('closing', flush=True)
    global closed
    closed = True
    time.sleep(1)
    print('close done', flush=True)
