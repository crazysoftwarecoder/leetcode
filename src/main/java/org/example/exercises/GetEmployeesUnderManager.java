package org.example.exercises;

import java.util.HashMap;
import java.util.Map;

public class GetEmployeesUnderManager {

    public Map<String, Integer> getEmployeesUnderManager(Map<String, String> employeeManagerPair) {
        Map<String, Integer> res = new HashMap<>();
        for (Map.Entry<String, String> entry : employeeManagerPair.entrySet()) {
            if (!res.containsKey(entry.getKey())) {
                // employee add num
                res.put(entry.getKey(), 0);
            }
            if (!res.containsKey(entry.getValue())) {
                res.put(entry.getValue(), 0);
            }
            res.put(entry.getValue(), res.get(entry.getValue())+1);
        }
        return res;
    }
}
