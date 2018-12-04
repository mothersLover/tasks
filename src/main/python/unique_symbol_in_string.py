import unittest


def get_char_number(char):
    a = ord('a')
    z = ord('z')
    char_number = ord(char)
    if a <= char_number <= z:
        return char_number - a
    return -1


def offset(number, vector):
    mask = 1 << number
    if vector & mask == 0:
        vector |= mask
    else:
        return -1
    return vector


def is_unique(string):
    vector = 0
    for char in string:
        number = get_char_number(char)
        if number != -1:
            vector = offset(number, vector)
            if vector == -1:
                return False
    return True


class IsUniqueSymbolsInStringTestCase(unittest.TestCase):
    def runTest(self):
        self.assertEqual(False, is_unique("abcdefghtyf"))
        self.assertEqual(False, is_unique("abcdefghtyfc"))
        self.assertEqual(True, is_unique("qazwsxedcrfvtgb"))


def test():
    assert is_unique("abcdefghtyf") is False
    assert is_unique("abcdefghtyfc") is False
    assert is_unique("qazwsxedcrfvtgb") is True


testcase = unittest.FunctionTestCase(test())
