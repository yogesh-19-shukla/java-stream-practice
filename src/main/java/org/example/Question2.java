package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question2 {

    /*  EXPECTED OUTPUT
{
Even    =   [0, 2, 4, 6, 8],
Odd     =   [1, 3, 5, 7, 9]
}
 */


    public static void main(String[] args) {
        List<Integer> numbers = List.of(-3, 0, 2, 3, 4, 4, 5, 6, 7, 8, 9, -1, 1, 0, 2);
        Map<String, List<Integer>> answer = processNumber(numbers);
        System.out.println(answer);
    }

    private static Map<String, List<Integer>> processNumber(List<Integer> numbers) {
       return numbers.stream()
                .map(Math::abs)
                .distinct()
                .collect(Collectors.partitioningBy(
                        n -> n%2==0,
                        Collectors.collectingAndThen(Collectors.toList(),list ->{
                            Collections.sort(list);
                            return list;
                        })
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey() ? "Even" : "Odd",
                        Map.Entry::getValue
                ));
    }
}
