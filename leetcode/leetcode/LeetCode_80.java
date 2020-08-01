package leetcode;

//80. 删除排序数组中的重复项 II
public class LeetCode_80 {
    public int removeDuplicates(int[] nums) {
       int j=1;
       int count=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]==nums[i-1]){
                count++;
            }
            else {
                count=1;
            }
            if (count<=2){
                nums[j++]=nums[i];
            }
        }
        return j;
    }
}

