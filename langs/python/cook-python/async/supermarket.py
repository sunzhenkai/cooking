# coding: utf-8

import random
import asyncio

fruits = ['apple', 'banana', 'orange', 'cherry', 'mango', 'pear', 'watermelon', 'grape']
shelf = [random.choice(fruits) for _ in range(3)]


async def fill(num: int):
    await asyncio.sleep(1)
    for _ in range(num):
        shelf.append(random.choice(fruits))


async def take(num: int):
    count = 0
    while count < num:
        if shelf:
            goods = shelf.pop()
            yield goods
        else:
            await fill(random.randint(1, 3))
            goods = shelf.pop()
            yield goods
        count += 1
        await asyncio.sleep(1)


async def buy():
    pocket = []
    async for good in take(15):
        pocket.append(good)
        print(pocket, flush=True)


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(buy())
