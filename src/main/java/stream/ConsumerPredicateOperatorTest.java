package test.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsumerPredicateOperatorTest {


    /**
      Consumer interface - As opposed to the Supplier, the Consumer accepts a generified argument and returns nothing.
      It is a function that is representing side effects.
     */
    @Test
    public void consumerTest() {
        List<String> names = Arrays.asList("John", "Freddy", "Samuel");
        names.forEach(name -> System.out.println("Hello, " + name));

        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 25);
        ages.put("Freddy", 24);
        ages.put("Samuel", 30);

        ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
    }

    /**
     * Predicate interface - In mathematical logic, a predicate is a function that receives a value and returns
     * a boolean value.
     */
    @Test
    public void predicateTest() {
        List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

        List<String> namesWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        Assert.assertEquals(2, namesWithA.size());
    }

    /**
     * Operator interfaces are special cases of a function that receive and return the same value type.
     * The UnaryOperator interface receives a single argument.
     * One of its use cases in the Collections API is to replace all values in a list with some computed values of the same type:
     */
    @Test
    public void operatorTest() {
        List<String> names = Arrays.asList("bob", "josh", "megan");

        names.replaceAll(String::toUpperCase);

        Assert.assertEquals("BOB", names.get(0));

        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);

        int sum1 = values.stream().reduce(0, (i1, i2) -> i1 + i2);
        int sum2 = values.stream().mapToInt(Integer::intValue).sum();
        Integer sum3 = values.stream().collect(Collectors.summingInt(Integer::intValue));
        Assert.assertEquals(sum1, sum2);
        Assert.assertEquals(sum3, Integer.valueOf(sum2));
    }
}
