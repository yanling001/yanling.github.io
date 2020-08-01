package leetcode;
//长度最小的子数组 双指针
public class LeetCode_209 {
    public int minSubArrayLen(int s, int[] nums) {
        int left,right;
        left=right=0;
        int sum;
        if(nums.length==0) return 0;
        int ans=Integer.MAX_VALUE;
        sum=nums[0];
        while (left<=right){

            if (right<nums.length-1 && sum<s){
                right++;
                sum+=nums[right];
                continue;
            }
            if (sum>=s){
                ans=Math.min(ans,right-left+1);
                sum-=nums[left];
                left++;
                continue;
            }
            if (right>=nums.length-1){
                sum-=nums[left];
                left++;
            }

        }

        if(ans==Integer.MAX_VALUE){return 0;}

        else return ans;
    }
    /*
    public int minSubArrayLen(int s, int[] nums) {
    int n = nums.length;
    if (n == 0) {
        return 0;
    }
    int left = 0;
    int right = 0;
    int sum = 0;
    int min = Integer.MAX_VALUE;
    while (right < n) {
        sum += nums[right];
        right++;
        while (sum >= s) {
            min = Math.min(min, right - left);
            sum -= nums[left];
            left++;
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
     */
}
