# coding: utf-8
import bisect
import random
from typing import Optional


def tst():
    a = [1, 3, 5, 7, 9]
    target = 3
    print(bisect.bisect_left(a, target))
    print(bisect.bisect_right(a, target))


def opt() -> Optional[str]:
    if random.randint(0, 10) > 5:
        return None
    else:
        return 'hello'


if __name__ == '__main__':
    tst()
    print(opt())
