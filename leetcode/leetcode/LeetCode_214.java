package leetcode;

public class LeetCode_214 {
    public String shortestPalindrome(String s) {
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int i = 0;
        for (; i < n; i++) {
            if (s.substring(0, n - i).equals(r.substring(i))) {
                break;
            }
        }
        return new StringBuilder(s.substring(n - i)).reverse() + s;

    }
}
