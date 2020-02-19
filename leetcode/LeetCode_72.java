package leetcode;

//编辑距离
public class LeetCode_72 {
    public  static  int anstest(String x,String y){
     int s1=x.length();
     int s2=y.length();
     int dp[][]=new int[s1+1][s2+1];
     for (int i=0;i<s1+1;i++){
         dp[i][0]=i;
     }
     for(int i=0;i<s2+1;i++){
         dp[0][i]=i;
     }
     for(int i=1;i<s1+1;i++){
         for(int j=1;j<s2+1;j++){
             if(x.charAt(i)==y.charAt(j)){
                 dp[i][j]=1+Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]-1);
             }else {
                 dp[i][j]=1+Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
             }

         }
     }
     return dp[s1][s2];
    }
}
