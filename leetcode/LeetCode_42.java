package leetcode;

public class LeetCode_42 {
    public static int trap(int[] height) {
      int left,right,ans;
      ans=0;
      left=0;    right=height.length-1;
      int leftmax,rightmax;
      leftmax=height[left]; rightmax=height[right];
      while (left<right){
          if (leftmax<=rightmax) {
              if (leftmax>height[left]){
                  ans=ans+(leftmax-height[left]);
              }
              left++;
              leftmax=Math.max(leftmax,height[left]);
          }
          if (rightmax<leftmax) {
              if (rightmax>height[right]){
                  ans=ans+(rightmax-height[right]);
              }
              right--;
              rightmax=Math.max(rightmax,height[right]);
          }
          }

      return ans;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
