package leetcode;

//旋转图像
public class LeetCode_48 {
    public void rotate(int[][] matrix) {
     //转置
     for(int i=0;i<matrix.length;i++) {
         for (int j= i;j<matrix[0].length;j++){
             int temp = matrix[i][j];
             matrix[i][j] = matrix[j][i];
             matrix[j][i]=temp;
         }
     }
     //每行以中间元素位轴旋转
        int n=matrix[0].length;
        for(int i=0;i<matrix.length;i++) {
            for (int j=0;j<n/2;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[i][n-1-j];
                matrix[i][n-1-j]=temp;
            }
        }
    }
}
