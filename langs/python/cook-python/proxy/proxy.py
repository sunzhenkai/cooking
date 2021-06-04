# coding: utf-8


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
    cls = Cls('wii')
    prx = Proxy(cls)
    prx.foo('hi')
