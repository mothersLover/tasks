def is_rotated(original, rotated):
    s1s2 = original + rotated
    if original in s1s2:
        return True
    return False

example = "bottleofwater"
rotated = 'waterbottleof'
flag = is_rotated(example, rotated)
if not flag:
    print(rotated + " is not rotated string of - " + example)
    raise ValueError('Should be true!')
print(rotated + " is rotated string of - " + example)
