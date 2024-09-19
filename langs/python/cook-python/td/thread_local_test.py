# coding: utf-8

import threading
from concurrent.futures import ThreadPoolExecutor
from threading import current_thread
from time import sleep

thread_local = threading.local()


def hi():
    inited = getattr(thread_local, 'inited', None)
    if inited is None:
        print("Nice to meet you", current_thread().name)
        thread_local.inited = True
    else:
        print("Welcome back", current_thread().name)
    sleep(0.1)


hi()
hi()

pool = ThreadPoolExecutor(5)

for i in range(10):
    pool.submit(hi)
