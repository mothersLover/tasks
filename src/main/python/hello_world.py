print("Hello world!")


def my_first_method(number):
    for i in range(number):
        if i % 2 == 0:
            print(i*i)


def reverse_loop(number):
    for i in range(number, 0, -1):
        print(i)

my_first_method(5)
reverse_loop(10)