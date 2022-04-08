# coding: utf-8
from test_a import *
from test_b import *


def suite():
    st = unittest.TestSuite()
    st.addTest(unittest.makeSuite(TestA))
    st.addTest(unittest.makeSuite(TestB))
    return st


if __name__ == '__main__':
    runner = unittest.TextTestRunner()
    runner.run(suite())
