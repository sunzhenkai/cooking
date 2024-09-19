# coding: utf-8

class A:
    def fa(self):
        print('call fa')


class B:
    def __init__(self, a: A):
        self._a = a
        pass

    def __getattr__(self, item):
        print('call __getattr__ with %s' % item)
        return self._a.__getattribute__(item)

    def __getattribute__(self, item):
        print('call __getattribute__ with %s' % item)
        return super(B, self).__getattribute__(item)

    def delegate(self) -> A:
        return self


a = A()
b = B(a).delegate()
b.fa()


def fc(a):
    print(a)


v = {'a': 'abc'}
fc(**v)
