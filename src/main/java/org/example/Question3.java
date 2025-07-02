package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Question3 {

/*  EXPECTED OUTPUT
{
    1=[2, 4], 2=[1], 3=[3]
}
*/


    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,1,2,3,3,3,4);
        Map<Long, List<Integer>> answer = processNumber(numbers);
        System.out.println(answer);
    }

    private static Map<Long, List<Integer>> processNumber(List<Integer> numbers) {
        Map<Integer, Long> freqMap = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(freqMap);
        return freqMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry :: getValue,
                        Collectors.mapping(Map.Entry :: getKey, Collectors.toList())
                ));
    }
}
