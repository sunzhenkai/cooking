# coding: utf-8
import os
from multiprocessing import Lock
import time

lock = Lock()


def start():
    print("in work %d" % os.getpid())
    lock.acquire()
    try:
        # TODO do work
        for i in range(0, 500):
            print("did work %s - %d" % (i, os.getpid()))
            time.sleep(1)
    finally:
        lock.release()
