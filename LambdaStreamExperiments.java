package test.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaStreamExperiments {

    public static void main(String... asr) {
        List<String> collect = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

//                List<Integer> collect3 = set.stream().mapToInt(Character::getNumericValue).boxed().collect(Collectors.toList());

//                Map<Integer, Long> collect1 = as.chars().boxed().collect(Collectors.groupingBy(c -> c, Collectors.counting()));

//                Map<Long, List<String>> collect2 = upperStrings.collect(Collectors.groupingBy(c -> (Long) set.stream().filter(c::contains).count()));
    }
}
