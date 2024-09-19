# coding: utf-8
import jwt

class Proxy:
    def __init__(self, d):
        self.d = d

    def __getattr__(self, item):
        return getattr(self.d, item)


class Cls:
    def __init__(self, msg):
        self.msg = msg

    def foo(self, data):
        print('foo', self.msg, data)

    def bar(self):
        print('bar', self.msg)


if __name__ == '__main__':
    # cls = Cls('wii')
    # prx = Proxy(cls)
    # prx.foo('hi')

    KEY = "5vHWAWDQnVx7whmy0DQocQtKRnb281cWHHHlbumbUSE"
    SECRET_KEY = "Z1WLkmwE47by4uPb2mPnG93lCuxwsp2ZBAA_abCZEJo"
    ALGORITHM = "HS256"
    # code = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJyZWNvbW1lbmRfYXBwIiwiZXhwIjoxNjQ5Njg2NjE0LCJpYXQiOjE2NDk2ODY1OTQsImlzcyI6InNob3BsYXp6YSIsIm5iZiI6MTY0OTY4NjU5NCwic3ViIjoiMjAwMzYxIiwiZG9tYWluIjoiIn0.UPRhgsKyR_h9_5jSl4Y3Kq1YGwFcDqepf4sJH_jD_0o'
    code = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJyZWNvbW1lbmRfYXBwIiwiZXhwIjoxNjQ5Njg2OTcxLCJpYXQiOjE2NDk2ODY5NTEsImlzcyI6InNob3BsYXp6YSIsIm5iZiI6MTY0OTY4Njk1MSwic3ViIjoiMzA5NjI1IiwiZG9tYWluIjoiIn0.eUFEfl6ep9zxY9z70Jq1KGAZgCvj8Ar4gDzcZLwm7uc'
    decoded_token = jwt.decode(code, SECRET_KEY, audience="recommend_app", algorithms=[ALGORITHM])
    print(decoded_token)