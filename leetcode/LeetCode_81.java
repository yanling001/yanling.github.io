package leetcode;


//  搜索旋转排序数组 II
public class LeetCode_81 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while(start<=end){
            mid= (start+end)/2;
            if (nums[mid]==target) return  true;
            if (nums[start]==nums[mid]) {
                start++ ;
                continue;
            }
            if (nums[mid]>nums[start]){
                if (target>=nums[start]&& target<nums[mid]){
                    end=mid-1;
                }else {
                    start=mid+1;
                }
            }else {
                if (target>nums[mid] && target<=nums[end]){
                    start=mid+1;
                }else end=mid-1;
            }
        }
        return  true;
    }


}
