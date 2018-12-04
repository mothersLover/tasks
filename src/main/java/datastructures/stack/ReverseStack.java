package datastructures.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;

public class ReverseStack {

    @Test
    public void test() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);
        stack.addLast(4);
        reverseStack(stack);
        Object[] objects = stack.toArray();
        Object[] compare = new Object[] {4, 3, 2, 1};
        Assert.assertArrayEquals(compare, objects);
    }

    public <T> void reverseStack(ArrayDeque<T> stack) {
        if (stack.isEmpty()) {
            return;
        }
        T element = stack.pollLast();
        reverseStack(stack);
        insertAtBottom(stack, element);
    }

    private <T> void insertAtBottom(ArrayDeque<T> stack, T element) {
        if (stack.isEmpty()) {
            stack.addLast(element);
            return;
        }
        T e = stack.pollLast();
        insertAtBottom(stack, element);
        stack.addLast(e);
    }
}
