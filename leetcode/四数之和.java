package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists=new ArrayList();
        if (nums==null||nums.length<4){
            return lists;
        }
        //先确定两个数在双指针扫描
        Arrays.sort(nums);
        for (int i=0;i<nums.length-3;i++){
            //去重
            if (i>0&&nums[i]==nums[i-1])
                continue;
            for (int j=i+1;j<nums.length-2;j++){
                //去重
                if (j>i+1&&nums[j]==nums[j-1])
                    continue;
                int r,l;
                r=j+1;
                l=nums.length-1;
                while (r<l){
                    int sum=nums[i]+nums[j]+nums[r]+nums[l];
                    if (sum==target){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[r]);
                        list.add(nums[l]);
                        lists.add(list);
                        //去重
                        while (r<l && nums[r] == nums[r+1]) r++; // 去重
                        while (r<l && nums[l] == nums[l-1]) l--; // 去重
                        r++;
                        l--;
                    }else if (sum>target){
                        l--;
                    }
                    else {
                        r++;
                    }
                }
            }
        }
        return lists;
    }
}
