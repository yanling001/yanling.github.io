package leetcode;

//27. 移除元素
public class LeetCode_27 {
    public int removeElement(int[] nums, int val) {
            int left,right;
            left=0;
             right=nums.length-1;
            while (right>left){
              if (nums[left]==val){
                  nums[left]=nums[right];
                  right--;
              }   else left++;
            }
            return right;
    }
}
