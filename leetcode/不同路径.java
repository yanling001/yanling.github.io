package leetcode;


import java.util.Scanner;

/*
  递归解法
class Solution {
    int ans=0;
    public int uniquePaths(int m, int n) {

        findways(m,n,0,0);
        return ans;
    }

    private void findways(int m, int n, int r, int l) {
        if (r>=m||l>=n)
            return;
        if (r==m-1&&l==n-1){
            ans++;
            return ;
        }
        if (r<m)
            findways(m,n,r+1,l);
        if (l<n)
            findways(m,n,r,l+1);
    }
}
*/
public class 不同路径 {
        public int uniquePaths(int m, int n) {
            int dp[][]=new int[m][n];

            for (int i=0;i<m;i++){
                for (int j=0;j<n;j++){
                    if(i==0&&j==0)
                        dp[0][0]=1;
                    else if (i-1>=0&&j-1>=0)
                        dp[i][j]=dp[i-1][j]+dp[i][j-1];
                    else   if (j==0)
                        dp[i][j]=dp[i-1][j];
                    else if (i==0)
                        dp[i][j]=dp[i][j-1];

                }
            }
            return dp[m-1][n-1];
        }
Scanner scanner=new Scanner("d");
}
