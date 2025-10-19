package org.example.algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int lengthOfW = words[0].length();
        HashMap<String, Integer> fMap = new HashMap<>();
        for (String word : words) {
            fMap.put(word, fMap.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();

        for (int i=0;i<s.length();i++) {
            int wordsRem = words.length;
            Map<String, Integer> currMap = (Map<String, Integer>) fMap.clone();
            for (int j=0;j<words.length;j++) {
                StringBuilder sb = new StringBuilder();
                for (int k=i+(j*lengthOfW);k<Math.min(i+(j*lengthOfW) + lengthOfW, s.length());k++) {
                    sb.append(s.charAt(k));
                }
                String currW = sb.toString();
                if (!currMap.containsKey(currW) || currMap.get(currW) <= 0) {
                    currMap = (Map<String, Integer>) fMap.clone();
                    break;
                }
                currMap.put(currW, currMap.get(currW) - 1);
                wordsRem--;
            }
            if (wordsRem == 0) {
                res.add(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        var sol = new Solution();
        System.out.println(sol.findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
    }
}