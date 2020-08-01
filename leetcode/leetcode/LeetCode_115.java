package leetcode;

//115. 不同的子序列
// if (s.charAt(i)== t.charAt(j))dp[i][j]==dp[i-1][j-1]+dp[i][j-1] else dp[i][j]=dp[i][j-1]

public class LeetCode_115 {
    public int numDistinct(String s, String t) {
        int dp[][]=new int[t.length()+1][s.length()+1];
        dp[0][0]=1;
        for (int i=1;i<t.length()+1;i++){
            dp[i][0]=0;
        }
        for(int j=1;j<s.length()+1;j++){
            dp[0][j]=1;
        }
        for (int i=1;i<t.length()+1;i++){
            for (int j=1;j<s.length()+1;j++){
                if (s.charAt(j-1)==t.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1]+dp[i][j-1];
                }else dp[i][j]=dp[i][j-1];
            }
        }
        return dp[t.length()][s.length()];
    }
}
