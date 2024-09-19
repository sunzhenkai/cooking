# coding: utf-8
import threading


def cls():
    while True:
        pass


if __name__ == '__main__':
    ts = []
    for i in range(5):
        ts.append(threading.Thread(target=cls))
    print(ts)
    for t in ts:
        t.start()

    for t in ts:
        t.join()
