# coding: utf-8

import requests


def test_native():
    r = requests.post('http://localhost:9040/api/cook/native')
    print(r.json())


def test_biz():
    r = requests.post('http://localhost:9040/api/cook/biz')
    print(r.json())


if __name__ == '__main__':
    test_biz()
