class A:
    cache = {}

    def __init__(self, name=None):
        self.name = name

    @classmethod
    def build(cls, name):
        if name not in cls.cache:
            cls.cache[name] = cls(name)
        return cls.cache[name]

    def foo(self):
        print('foo:', self.name)


def ts(cls: A.__class__):
    return cls()


if __name__ == '__main__':
    ts(A).foo()
    A.build('hi').foo()
