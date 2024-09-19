#!coding: utf-8

import argparse

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('-f', '--foo', action='append', help='foo help')
    args = parser.parse_args('--foo hel --foo a'.split())
    # args = parser.parse_args()
    print('a' in args)
    print('foo' in args)
    pass
