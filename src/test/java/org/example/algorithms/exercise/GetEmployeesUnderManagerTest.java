package org.example.algorithms.exercise;

import org.example.exercises.GetEmployeesUnderManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetEmployeesUnderManagerTest {

    private GetEmployeesUnderManager obj = new GetEmployeesUnderManager();

    @Test
    public void testWithMapEntries() {
        var employeeManagerMap = Map.of(
                "A", "C",
                "B", "C",
                "C", "F",
                "D", "E",
                "E", "F",
                "F", "F");
        var res = obj.getEmployeesUnderManager(employeeManagerMap);
        var expected = Map.of(
                "A", 0,
                "B", 0,
                "C", 2,
                "D", 0,
                "E", 1,
                "F", 5);
        Assertions.assertEquals(expected, res);
    }
}
