# coding: utf-8
import threading
import time


def schedule(interval: int, func):
    threading.Thread(target=_do_schedule, args=[interval, func])


def _do_schedule(interval: int, func):
    print(interval)
    while True:
        func()
        time.sleep(interval)


def print_sth():
    print('sth')


if __name__ == '__main__':
    schedule(1, print_sth)
    print('done')
