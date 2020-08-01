package leetcode;

public class LeetCode_162 {
    public static int findPeakElement(int[] nums) {
        if(nums.length==1) return 0;
        int num= find(nums,0,nums.length-1);
        if(num==Integer.MIN_VALUE) return nums.length-1;
        return num;
    }

    private static int find(int[] nums, int start, int end) {
        if (start>end){
            return  Integer.MIN_VALUE;
        }
        if ((start==end)&&(end==nums.length-1))
            return Integer.MIN_VALUE;
        int mid = (start+end)/2;
        int temp=Integer.MIN_VALUE;
        if(mid!=0)
            temp=nums[mid-1];
        if (nums[mid]>nums[mid+1]&&temp<nums[mid]) {
            return mid;
        }
        int left = find(nums,start,mid-1);
        int right = find(nums,mid+1,end);
        if (left!=Integer.MIN_VALUE){
            return left;
        }
        else
            return right;



    }

    public static void main(String[] args) {

        ThreadLocal threadLocal=new ThreadLocal();
        Thread thread = Thread.currentThread();
        System.out.println(findPeakElement(new int[]{1,2}));

    }
}
