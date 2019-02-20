package test.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class ReverseLinkedList {

    @Test
    public void test() {
        List<Integer> integers = new LinkedList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers = reverseList(integers);
        Assert.assertEquals(Integer.valueOf(5), integers.get(0));
        Assert.assertEquals(Integer.valueOf(4), integers.get(1));
        Assert.assertEquals(Integer.valueOf(3), integers.get(2));
        Assert.assertEquals(Integer.valueOf(2), integers.get(3));
        Assert.assertEquals(Integer.valueOf(1), integers.get(4));
    }

    public static List<Integer> reverseList(List<Integer> listToReverse) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(listToReverse.size());
        listToReverse.forEach(stack::addLast);
        LinkedList<Integer> newList = new LinkedList<>();
        while (!stack.isEmpty()) {
            newList.add(stack.pollLast());
        }
        return newList;
    }
}
