# coding: utf-8

class BaseClass:
    def _private(self):
        print('private')


class PrivateMethodTestClass(BaseClass):
    def f(self):
        self._private()

    def _f(self):
        print('private _f')


if __name__ == '__main__':
    pmt = PrivateMethodTestClass()
    pmt.f()
