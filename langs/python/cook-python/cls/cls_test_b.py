class A:
    def a(self):
        print('a')


class B:
    def b(self):
        print('b')


class C(A, B):
    def c(self):
        print('c')
        self.a()
        self.b()


c = C()
c.c()
