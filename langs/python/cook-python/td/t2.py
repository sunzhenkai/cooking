# coding: utf-8
from threading import Thread


def payload():
    for i in range(100000000):
        pass
    print('run payload done')


td = Thread(target=payload)
td.start()
td.join(timeout=1)
print('join done')
