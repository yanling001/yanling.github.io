package leetcode;

public class LeetCode_153 {
    public static int findMin(int[] nums) {
        int index=dfs(nums,0,nums.length-1);
        if(index==-1) return nums[0];
        else
            return  nums[index];
    }

    private static int dfs(int[] nums, int i, int length) {
        if (i>length) return -1;
        int mid = (i+length+1)/2;
        if (mid<=0) return -1;
        if (nums[mid]<nums[mid-1]) return mid;

        int left,right;
        left=dfs(nums,i,mid-1);
        right=dfs(nums,mid+1,length);
        if (left!=-1)
            return left;
        else  return  right;
    }
    public static void main(String[] args) {
        //[4,5,6,7,0,1,2] )
        int[] a=new int[]{4,5,6,7,0,1,2};
        System.out.println(findMin(a));
    }
}
