package leetcode;

import java.util.Arrays;

public class 最近的三个数 {
    public int threeSumClosest(int[] nums, int target) {
        if(nums==null)
            return 0;
        if(nums.length==1)
            return nums[0];
        if (nums.length==2)
            return nums[0]+nums[1];
          //对数组排序
        Arrays.sort(nums);
        int ans=nums[0]+nums[1]+nums[2];
        for (int i=0;i<nums.length;i++){
            int l,r;
            l=i+1; r=nums.length-1;
            while (l<r){
                int sum=nums[i]+nums[l]+nums[r];
                if (Math.abs(sum-target)<Math.abs(ans-target)) {
                    ans = sum;
                }
                if (sum>target){
                    r--;
                }
                if (sum<target){
                    l++;
                }
                if (sum==target)
                    return sum;
            }
            }
            return ans;
        }


    }

