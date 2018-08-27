package test.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupWordsFromFile {
    private static final String REGEX = "[EYUIOA]";
    private final static String PATH_TO_FILE = "C:/UBS/DEV/test.txt";
    
    public static void main(String... ars) {
        try {
            Path path = Paths.get(PATH_TO_FILE);
            wordsCount(path);
            sequentialAlgorithm(path);
            parallelAlgorithm(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void wordsCount(Path path) throws IOException {
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        long count = lines.flatMap(line -> Stream.of(line.split(" "))).map(String::toUpperCase).count();
        System.out.println("Words count - " + count);
    }

    private static void sequentialAlgorithm(Path path) throws IOException {
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" "))).map(String::toUpperCase);
        long start = System.currentTimeMillis();
        Map<Integer, List<String>> map = words.collect(Collectors.groupingBy(word -> word.length() - word.replaceAll(REGEX, "").length()));
        long end = System.currentTimeMillis();
        System.out.println("Sequential algorithm takes - " + (end - start));
    }

    private static void parallelAlgorithm(Path path) throws IOException {
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" "))).map(String::toUpperCase);
        long start = System.currentTimeMillis();
        Map<Integer, List<String>> map = words.parallel().collect(Collectors.groupingBy(word -> word.length() - word.replaceAll(REGEX, "").length()));
        long end = System.currentTimeMillis();
        System.out.println("Parallel algorithm takes - " + (end - start));
    }
}
