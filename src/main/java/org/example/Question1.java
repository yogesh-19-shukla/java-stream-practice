package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question1 {
/*  EXPECTED OUTPUT
{
  "prime": [2, 3, 5, 7],
  "Non-Prime": [0, 1, 4, 6, 8, 9]
}
 */
    public static void main(String[] args) {
        List<Integer> numbers = List.of(-3, 0, 2, 3, 4, 4, 5, 6, 7, 8, 9, -1, 1, 0, 2);
        Map<String, List<Integer>> answer = processNumber(numbers);
        System.out.println(answer);
    }

    private static Map<String, List<Integer>> processNumber(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n<0 ? 0 : n)
                .distinct()
                .collect(Collectors.partitioningBy(Question1::isPrime))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey() ? "Prime" : "Non-Prime",
                        e -> e.getValue().stream()
                                .sorted().collect(Collectors.toList())
                ));
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
