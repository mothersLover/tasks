package test.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfaceTest {

    @Test
    public void test() {
        List<ParentInterface> interfaces = new ArrayList<>();
        interfaces.add(new ParentImpl());
        interfaces.add(new ParentImpl());
        interfaces.add(new ParentImpl());
        interfaces.add(new ParentImpl());
        interfaces.add(new ChildImpl());
        interfaces.add(new ChildImpl());
        interfaces.add(new ChildImpl());
        interfaces.add(new ChildImpl());
        interfaces.add(new ChildImpl());
        interfaces.forEach(ParentInterface::doSomething2);

        Map<String, Integer> stringIntegerMap = new HashMap<>();
    }

    private static class ParentImpl implements ParentInterface {

        @Override
        public void doSomething2() {
            System.out.println("Parent 2");
        }
    }

    private static class ChildImpl implements ChildInterface {

        @Override
        public void doSomething2() {
            System.out.println("Child 2");
        }
    }
}
