package leetcode;

import java.util.ArrayList;
import java.util.List;

// 汇总区间
public class LeetCode_228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans =new ArrayList<>();
        if(nums.length==0) return ans;
        if(nums.length==1) {
            ans.add(new String(Integer.toString(nums[0])));
            return ans;
        }
        List<String> temp =new ArrayList<>();
        temp.add(Integer.toString(nums[0]));
        for (int i=1;i<nums.length;i++){
            if (nums[i]==nums[i-1]+1){

                temp.add("->");
                temp.add(Integer.toString(nums[i]));
                // stringBuilder.append(nums[i]);
            }

            else{
                StringBuilder stringBuilder=new StringBuilder();
                if (temp.size()>2){
                    stringBuilder.append(temp.get(0));
                    stringBuilder.append("->");
                    stringBuilder.append(temp.get(temp.size()-1));
                    ans.add(stringBuilder.toString());
                }else {
                    stringBuilder.append(temp.get(0));
                    ans.add(stringBuilder.toString());
                }

                temp=new ArrayList();
                temp.add(Integer.toString(nums[i]));
            }

        }
        StringBuilder stringBuilder=new StringBuilder();
        if (temp.size()>2){
            stringBuilder.append(temp.get(0));
            stringBuilder.append("->");
            stringBuilder.append(temp.get(temp.size()-1));
            ans.add(stringBuilder.toString());
        }else {
            stringBuilder.append(temp.get(0));
            ans.add(stringBuilder.toString());
        }

        return ans;
    }
}
