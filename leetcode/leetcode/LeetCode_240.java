package leetcode;
//二分查找
public class LeetCode_240 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
      int a[][]=  new int[][]{{1,   4,  7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24},{18, 21, 23, 26, 30}};
        System.out.println(searchMatrix(a,5));
    }
}