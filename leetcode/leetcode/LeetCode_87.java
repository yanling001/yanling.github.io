package leetcode;

//扰乱字符串 区间dp
public class LeetCode_87 {
    public boolean isScramble(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        if (n != m) {
            return false;
        }
        boolean[][][] dp = new boolean[n][n][n + 1];//n+1表示跨度1到n
        //初始化当跨度为一时：
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        //dp   dp[i][j][k]===
        //OR1<=w<=k−1OR_{1<=w<=k-1}OR1<=w<=k−1​  {dp[i][j][w]  &&  dp[i+w][j+w][k−w]}\left\{ dp[i][j][w]\ \ \&\& \ \  dp[i+w][j+w][k-w] \right\}{dp[i][j][w]  &&  dp[i+w][j+w][k−w]}  或
        //OR1<=w<=k−1OR_{1<=w<=k-1}OR1<=w<=k−1​  {dp[i][j+k−w][w]  &&  dp[i+w][j][k−w]}\left\{ dp[i][j+k-w] [w] \ \ \&\& \ \  dp[i+w][j][k-w] \right\}{dp[i][j+k−w][w]  &&  dp[i+w][j][k−w]}
        //跨度从2到n
        for (int len = 2; len <= n; len++) {
            //s1串
            for (int i = 0; i <= n-len; i++) {
                //s2串
                for (int j = 0; j <= n-len; j++) {
                    // 枚举划分位置 从k=1开始因为跨度最小位1
                    for (int k = 1; k <= len - 1; k++) {

                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len-k ，S2 起点 i + 前面长度k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }

                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
