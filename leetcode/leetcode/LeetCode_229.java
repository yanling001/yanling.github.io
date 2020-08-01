package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res=new ArrayList<>();
        //选举法
        int can1;
        int can2;
        int count1,count2;
        can2=can1=nums[0];
        count1=count2=0;
        for (int n:nums){
            if (n==can1){
                count1++;
                continue;
            }
            if (n==can2){
                count2++;
                continue;
            }
            if (count1==0){
                can1=n;
                count1++;
                continue;
            }
            if (count2==0){
                can2=n;
                count2++;
                continue;
            }
            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (can1 == num) count1++;
            else if (can2 == num) count2++;
        }
      //加入答案
        if (count1 > nums.length / 3) res.add(can1);
        if (count2 > nums.length / 3) res.add(can2);

        return res;


    }
}
