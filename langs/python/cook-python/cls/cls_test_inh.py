# coding: utf-8

class A:
    def init(self):
        print('init A')


class B(A):
    def init(self):
        super(B, self).init()
        print('init B')

    def a(self, d, b=None, e=None):
        print('d=%s, b=%s, e=%s' % (d, b, e))


class C(B):
    def init(self):
        super(B, self).init()
        super(C, self).init()
        print('init C')

    def a(self, *args, **kwargs):
        super(C, self).a(*args, **kwargs)
        pass


if __name__ == '__main__':
    c = C()
    c.init()
    c.a(1, e=2, b=3)
