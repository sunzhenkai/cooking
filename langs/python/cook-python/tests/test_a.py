# coding: utf-8
import unittest


class TestA(unittest.TestCase):
    def test_a(self):
        self.assertTrue(True, 'ok')

    def test_ac(self):
        self.assertTrue(False, 'f')
