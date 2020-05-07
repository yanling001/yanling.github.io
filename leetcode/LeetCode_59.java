package leetcode;

public class LeetCode_59 {
    public int[][] generateMatrix(int n) {
        int on,down,left,right;

        on=0; down=n-1; left=0; right=n-1;
        int ans[][]=new int[n][n];
        int start=1;
        while (start<=n*n){
            for (int i=left;i<=right;i++){
                ans[on][i]=start++;
            }
            on++;
            for (int i=on;i<=down;i++){
                ans[i][right]=start++;
            }
            right--;
            for (int i=right;i>=left;i--){
                ans[down][i]=start++;
            }
            down--;
            for (int i=down;i>=on;i--){
                ans[i][left]=start++;
            }
            left++;
        }
        return ans;
    }
}
