# coding: utf-8
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime


def payload():
    # for i in range(100000):
    #     pass
    pass


def always_create():
    with ThreadPoolExecutor(max_workers=1) as executor:
        executor.submit(payload)


def create_once(executor: ThreadPoolExecutor):
    executor.submit(payload)


def main():
    loop = 100000
    # ts = math.floor(datetime.timestamp(datetime.now()) * 1000)
    executor = ThreadPoolExecutor(max_workers=1)

    # start = datetime.now()
    # for i in range(loop):
    #     create_once(executor)
    # print(datetime.now() - start)
    #
    # start = datetime.now()
    # for i in range(loop):
    #     always_create()
    # print(datetime.now() - start)

    start = datetime.now()
    for i in range(loop):
        pass
    print(datetime.now() - start)

    start = datetime.now()
    for i in range(loop):
        ThreadPoolExecutor(max_workers=1)
    print(datetime.now() - start)


if __name__ == '__main__':
    main()
