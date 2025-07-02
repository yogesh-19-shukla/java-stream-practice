package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Question5 {


    /* EXPECTED OUTPUT
    [
          java=4,
          is=2,
          cool=2,
          powerful=2,
          streams=2
    ]

     */

    public static void main(String[] args) {
        List<String> sentences = List.of(
                "Java is cool",
                "Java is powerful",
                "Streams in Java are cool",
                "Streams make Java powerful"
        );

        List<Map.Entry<String, Long>> topWords = top5Words(sentences);
        topWords.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }

    public static List<Map.Entry<String, Long>> top5Words(List<String> sentences) {
        return sentences.stream()
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\s+")))  // ["java","is","cool","java","is","powerful"....]
                .filter(word -> !word.isBlank())   // remove empty words
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))   // word count
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry::getKey))  // Sort by frequency desc
                .limit(5)
                .collect(Collectors.toList());

    }
}
