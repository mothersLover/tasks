x = int(raw_input())
y = int(raw_input())
n = int(raw_input())


def print_pairs(first, second, third):
    p = 0
    ar = []
    for i in range(first + 1):
        for j in range(second + 1):
            if i + j != third:
                ar.append([])
                ar[p] = [i, j]
                p += 1
    print ar


print_pairs(x, y, n)
print [[i, j] for i in range(x + 1) for j in range(y + 1) if ((i + j) != n)]
