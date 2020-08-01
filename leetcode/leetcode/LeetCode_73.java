package leetcode;

public class LeetCode_73 {
    public void setZeroes(int[][] matrix) {
        boolean flag=false;//默认第一列不为零
        int r,c;
        r=matrix.length;
        c=matrix[0].length;
        for (int i=0;i<r;i++){
            if (matrix[i][0]==0){
                flag=true;
            }
            for (int j=1;j<c;j++){
                if (matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for (int i=1;i<r;i++){
            if (matrix[i][0]==0){
                for (int j=1;j<c;j++){
                    matrix[i][j]=0;
                }
            }
        }
        for (int i=1;i<c;i++){
            if (matrix[0][i]==0){
                for (int j=1;j<r;j++){
                    matrix[j][i]=0;
                }
            }
        }
        if (matrix[0][0]==0){
            for (int i=0;i<c;i++){
                matrix[0][i]=0;
            }
        }
        if (flag){
            for (int i=0;i<r;i++){
                matrix[i][0]=0;
            }
        }
    }
}
