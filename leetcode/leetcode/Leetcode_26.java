package leetcode;
//删除排序数组中的重复项
public class Leetcode_26 {
    public int removeDuplicates(int[] nums) {
            int first=0; int botten=0;
            for (int i=0;i<nums.length;i++){
                if (nums[first]==nums[botten]){
                    first++;
                }else
                {
                    botten++;
                    nums[botten]=nums[first];
                }
            }
            return botten+1;
    }
}
