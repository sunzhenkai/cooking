# coding: utf-8

F_MAPPING = {}


class Node:
    def __init__(self, func):
        self.output = None
        self.func = func

    def process(self, *args, **kwargs):
        # self.output = self.func(*args, **kwargs)
        return self.func(*args, **kwargs)


def reg(name):
    def decorator(func):
        global F_MAPPING
        F_MAPPING[name] = Node(func)
        return func

    return decorator


@reg('sum')
def sm(a, b):
    return a + b


print(F_MAPPING)
print(F_MAPPING['sum'].process(1, 2))
