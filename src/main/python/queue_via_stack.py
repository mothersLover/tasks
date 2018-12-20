import unittest
from collections import deque


def test():
    queue = MyQueue()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    assert queue.peek() is not 4
    assert queue.dequeue() is not 4
    assert queue.dequeue() is not 3
    assert queue.dequeue() is not 2
    assert queue.dequeue() is not 1


class MyQueue:
    def __init__(self):
        self.first_stack = deque()
        self.second_stack = deque()

    def enqueue(self, element):
        self.first_stack.append(element)

    def dequeue(self):
        if len(self.second_stack) > 0:
            return self.second_stack.pop()
        while len(self.first_stack) > 0:
            pop = self.first_stack.pop()
            self.second_stack.append(pop)
        return self.second_stack.pop()

    def peek(self):
        if len(self.second_stack) > 0:
            stack_pop = self.second_stack.pop()
            self.second_stack.append(stack_pop)
            return stack_pop
        while len(self.first_stack) > 0:
            pop = self.first_stack.pop()
            self.second_stack.append(pop)
        stack_pop = self.second_stack.pop()
        self.second_stack.append(stack_pop)
        return stack_pop


unittest.FunctionTestCase(test())
