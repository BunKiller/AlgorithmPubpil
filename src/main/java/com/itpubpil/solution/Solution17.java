package com.itpubpil.solution;

import java.util.*;

/**
 * @author wangjingbiao
 * createDate: 2021/11/12 周五 3:50 下午
 * desc:
 */
public class Solution17 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations("234"));
    }

    static class Solution {
        private static final Map<Character, Set<String>> numMap = new HashMap<>(8);

        static {
            numMap.put('2', new HashSet<>(Arrays.asList("a", "b", "c")));
            numMap.put('3', new HashSet<>(Arrays.asList("d", "e", "f")));
            numMap.put('4', new HashSet<>(Arrays.asList("g", "h", "i")));
            numMap.put('5', new HashSet<>(Arrays.asList("j", "k", "l")));
            numMap.put('6', new HashSet<>(Arrays.asList("m", "n", "o")));
            numMap.put('7', new HashSet<>(Arrays.asList("p", "q", "r", "s")));
            numMap.put('8', new HashSet<>(Arrays.asList("t", "u", "v")));
            numMap.put('9', new HashSet<>(Arrays.asList("w", "x", "y", "z")));
        }

        public List<String> letterCombinations(String digits) {
            if (digits == null || "".equals(digits)) {
                return new ArrayList<>();
            }

            List<String> result = new ArrayList<>(numMap.get(digits.charAt(0)));
            for (int i = 1; i < digits.length(); i++) {
                Set<String> numDigitSet = numMap.get(digits.charAt(i));
                List<String> tmpResult = new ArrayList<>();
                result.forEach(str -> {
                    numDigitSet.forEach(numValue -> {
                        tmpResult.add(str + numValue);
                    });
                });
                result = tmpResult;
            }
            return result;
        }
    }

    /**
     * 递归算法解决问题，效率更高
     */
    static class Solution2 {
        private final String[] letterMap = {"abc", //2
                "def", //3
                "ghi", //4
                "jkl", //5
                "mno", //6
                "pqrs", //7
                "tuv", //8
                "wxyz" //9
        };
        private ArrayList<String> res;

        public List<String> letterCombinations(String digits) {
            res = new ArrayList<String>();
            if (digits.equals("")) return res;
            findCombination(digits, 0, "");
            return res;
        }

        private void findCombination(String digits, int index, String currentCombination) {
            if (index == digits.length()) {
                res.add(currentCombination);
                return;
            }
            char digit = digits.charAt(index);
            String mappedLetters = letterMap[digit - '0'];
            for (int i = 0; i < mappedLetters.length(); i++) {
                findCombination(digits, index + 1, currentCombination + mappedLetters.charAt(i));
            }
        }
    }
}
