import unittest

def fun(x):
    return x + 1

class MyTest(unittest.TestCase):
    def test(self):
        self.assertEqual(fun(3), 4)
    def test_unit(self):
        self.assertEqual(fun(3), 4)