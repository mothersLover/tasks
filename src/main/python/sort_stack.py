from collections import deque


def take_deepest(stack):
    if len(stack) < 1:
        return stack
    pop = stack.pop()
    take_deepest(stack)
    push_to_deep(stack, pop)


def push_to_deep(stack, element):
    if len(stack) < 1:
        stack.append(element)
        return stack
    pop = stack.pop()
    if element > pop:
        push_to_deep(stack, element)
        stack.append(pop)
    else:
        stack.append(pop)
        stack.append(element)
    return stack


def sort_stack(stack):
    take_deepest(stack)
    return stack


stack = deque()
stack.append(4)
stack.append(3)
stack.append(1)
stack.append(2)
stack.append(5)
stack.append(6)
stack.append(7)
stack.append(9)
stack.append(8)
stack1 = sort_stack(stack)
print stack1
