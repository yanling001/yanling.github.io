package leetcode;

import java.util.HashMap;
import java.util.Map;
//只出现一次的数字
public class LeetCode_136 {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int ans=0;
        for(int i=0;i<nums.length;i++){
            if (map.get(nums[i])==1){
                ans=nums[i];
            }
                break;
        }
        return ans;
    }
}
