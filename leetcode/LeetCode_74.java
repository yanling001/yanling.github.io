package leetcode;

//74. 搜索二维矩阵
public class LeetCode_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0|| matrix[0].length==0) return false;
        //二分查找 先找到行再找到列
        int row=find(matrix,0,matrix.length-1,target);
        System.out.println(row);
        int clo=findclo(matrix,0,matrix[0].length-1,target,row);
        System.out.println(clo);
        if (clo>=0) return true;
        else  return  false;
    }

    private int findclo(int[][] matrix, int left, int right, int target,int row) {
        if (left>right) return -1;
        int mid = (left+right)/2;
        if (matrix[row][mid]==target){
            return mid;
        }
        if (matrix[row][mid]<target){
            return findclo(matrix,mid+1,right,target,row);
        }
        else{
            return findclo(matrix,left,mid-1,target,row);
        }
    }

    private int find(int[][] matrix, int left, int right, int target) {

        if (left>right) return  matrix.length-1;
        int mid = (left+right)/2;
        if(mid==matrix.length-1) return mid;
        if (matrix[mid][0]<=target&&matrix[mid+1][0]>target){
            return mid;
        }
        if (matrix[mid][0]<target){
            return find(matrix,mid+1,right,target);
        }
        else{
            return find(matrix,left,mid-1,target);
        }
    }


    public static void main(String[] args) {
        System.out.println();
    }
}
