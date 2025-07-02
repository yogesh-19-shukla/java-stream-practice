package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question4 {

    /*
    {Engineering={Dev=80000.0, QA=60000.0}, HR={Recruiter=50000.0}}
     */


    record Employee(String name, String department, String role, double salary) {}

    public static Map<String, Map<String, Double>> groupAndAvg(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.groupingBy(
                                Employee :: role,
                                Collectors.averagingDouble(Employee::salary)
                        )
                ));
    }
}
