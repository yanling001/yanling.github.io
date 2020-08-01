package leetcode;

public class 搜素插入位置 {
    public int searchInsert(int[] nums, int target) {
        int ans=0;
        if (target>nums[nums.length-1])
            return nums.length-1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target)
                continue;
            if (nums[i] == target) {
                ans = i;
                break;
            }
            else
            {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
