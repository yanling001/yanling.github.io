package leetcode;

//在排序数组中查找元素的第一个和最后一个位置
public class Leetcode_34 {
    public int[] searchRange(int[] nums, int target) {
        int ans[] = null;
        int index = findindex(nums, target, 0, nums.length - 1);
        if (index==-1)
            return new int []{-1,-1};
        int left,right;
        left=right=index;
        while (left>=0&&nums[left]==target) left--;
        while (right<=nums.length-1&&nums[right]==target) right++;
        return new int []{left+1,right-1};
    }

    private int findindex(int[] nums, int target, int start, int end) {
        int mid = (start+end)/2;
        if (target==nums[mid]){
            return mid;
        }
        if (start<=end)
            return -1;
        if (target>nums[mid])
          return findindex(nums,target,mid+1,end);
        else
            return  findindex(nums,target,start,mid);

    }

}
