package com.itpubpil.solution;

/**
 * @author wangjingbiao
 * createDate: 2021/11/25 周四 5:30 下午
 * desc: 8号问题，字符串转换整数
 */
public class Solution8 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi("2147483648"));
        System.out.println(solution.myAtoi("-2147483648"));
    }

    static class Solution {
        public int myAtoi(String s) {
            s = s.trim();
            if (s.length() == 0) {
                return 0;
            }
            boolean isNegative = s.charAt(0) == '-';
            boolean isPositiveSign = s.charAt(0) == '+';
            StringBuilder digitsBuilder = new StringBuilder();
            boolean leadingZeroFlag = true;
            for (int i = isNegative || isPositiveSign ? 1 : 0; i < s.length(); i++) {
                if (s.charAt(i) == '0' && leadingZeroFlag) {
                    continue;
                }
                leadingZeroFlag = false;
                char c = s.charAt(i);
                if (c < '0' || c > '9') {
                    break;
                }
                digitsBuilder.append(c);
            }
            if (digitsBuilder.length() == 0) {
                return 0;
            }
            String digitsString = digitsBuilder.toString();
            if (digitsString.length() > 11) {
                if (isNegative) {
                    return Integer.MIN_VALUE;
                }
                return Integer.MAX_VALUE;
            }
            long parsedValue = isNegative ? -Long.parseLong(digitsString) : Long.parseLong(digitsString);
            if (parsedValue > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (parsedValue < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return isNegative ? Integer.parseInt("-" + digitsString) : Integer.parseInt(digitsString);
        }
    }

    /**
     * 更优的一种解法
     */
    static class Solution2 {
        public int myAtoi(String s) {
            char[] chars = s.trim().toCharArray();
            int result = 0;
            // 符号位 true:+ false:-
            boolean isPositive = true;
            // 当前是否已经有效输入过
            boolean hasInput = false;
            for (char c : chars) {
                if (c >= '0' && c <= '9' || c == '+' || c == '-') {
                    // 遇到符号位
                    if (c == '-' || c == '+') {
                        // 如果有效输入过，直接返回
                        if (hasInput) {
                            return result * (isPositive ? 1 : -1);
                        }
                        isPositive = c == '+';
                        hasInput = true;
                        continue;
                    }
                    hasInput = true;
                    // 检查是否越界，因为符号拿开了，所以只用判断超过最大值
                    if ((result * 10L + (c - '0')) > Integer.MAX_VALUE) {
                        // 特别注意，如果是负数，需要再 -1
                        return Integer.MAX_VALUE * (isPositive ? 1 : -1) + (isPositive ? 0 : -1);
                    }
                    // 为 result 做输入
                    result = result * 10 + (c - '0');
                } else {
                    break;
                }
            }
            return result * (isPositive ? 1 : -1);
        }
    }

}
