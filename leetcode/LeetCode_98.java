package leetcode;

public class LeetCode_98 {
    //动态规划
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length()+s2.length()!=s3.length()) return  false;
       boolean[][]dp=new boolean[s1.length()+1][s2.length()+1];

        for (int i=0;i<s1.length()+1;i++){
            for (int j=0;j<s2.length()+1;j++){
                if (i+j==0) dp[i][j]=true;
                else if (j==0){
                    dp[i][j]=dp[i-1][j] &&  s3.charAt(i+j-1)==s1.charAt(i-1);
                }else if(i==0){
                    dp[i][j]=dp[i][j-1] &&  s3.charAt(i+j-1)==s2.charAt(j-1);
                }
                else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        LeetCode_98 leetCode_98=new LeetCode_98();
        System.out.println(leetCode_98.isInterleave( "aacaac","aacaaeaac", "aacaaeaaeaacaac"));
    }
}
/*
"aacaac"
"aacaaeaac"
"aacaaeaaeaacaac"
 */