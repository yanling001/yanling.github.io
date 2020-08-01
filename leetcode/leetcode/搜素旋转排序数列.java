package leetcode;

public class 搜素旋转排序数列 {
    public static int search(int[] nums, int target) {
           //第一步查找交换下标
        int index =findindex(nums);
        if (target<nums[index]||target>nums[index-1])
            return -1;
        int left,right;
        if (target>nums[nums.length-1]) {
            left=0; right=index-1;
            //二分查找
            return findnum(left,right,nums,target);
        }else {
            left=index; right=nums.length-1;
            //二分查找
            return findnum(left,right,nums,target);
        }

    }

    private static int findnum(int left, int right, int[] nums, int target) {
        int mid=(left+right)/2;
        if (target==nums[mid]){
          return mid;
        }
        if (left==right)
            return -1;
        if (target>nums[mid]){
           return findnum(mid,right,nums,target);
        }
        else
            return findnum(left,mid,nums,target);
    }

    private static int findindex(int[] nums) {
        int min=0;
        for (int i=1;i<nums.length;i++){
            if (nums[i]<nums[min])
                min=i;

        }
        return min;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{ 4,5,6,7,0,1,2};
       System.out.println( search(nums,0));
    }
}
