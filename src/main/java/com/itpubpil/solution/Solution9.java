package com.itpubpil.solution;

/**
 * @author wangjingbiao
 * createDate: 2021/11/15 周一 4:26 下午
 * desc:
 */
public class Solution9 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(10));
        System.out.println(solution.isPalindrome(-121));

        System.out.println(solution.isPalindrome2(121));
        System.out.println(solution.isPalindrome2(10));
        System.out.println(solution.isPalindrome2(-121));
    }

    // 第一种解法，转String，反转后比较
    private static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            String str = String.valueOf(x);
            StringBuilder reverseStr = new StringBuilder(str).reverse();
            return str.equals(reverseStr.toString());
        }
        // 第二种解法，计算数值
        public boolean isPalindrome2(int x) {
            if (x < 0) return false;
            int reversedNumber = 0;
            int remaining = x;
            while (remaining != 0) {
                reversedNumber = reversedNumber * 10 + remaining % 10;
                remaining /= 10;
            }
            return reversedNumber == x;
        }
    }
}
