def print_table(table, start=1, end=10):
    if start > end:
        return
    for i in range(start, end):
        print("{} X {} = {}".format(table, i, table * i))


print_table(9)
print_table(1, 20, 50)


def sum_two_values(first, second):
    return first + second


values = sum_two_values(5, 5)
print(values)
