package leetcode;
import java.util.*;

public class 三数之和 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists=new ArrayList<List<Integer>>();
        if (nums.length<3||nums==null)
            return lists;
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            if (i > 0 && nums[i] == nums[i-1]){
                //如果有重复数字跳过
                continue;
            }
            if (nums[i]>0){
                break;
            }

            int l,r;
            l=i+1;
            r=nums.length-1;
            while (l<r){
                int sum;
                sum=nums[l]+nums[r]+nums[i];
                if (sum==0){
                    List<Integer> list=new ArrayList<Integer>();
                    list.add(nums[l]);
                    list.add(nums[r]);
                    list.add(nums[i]);
                    lists.add(list);
                    while (l<r && nums[l] == nums[l+1]) l++; // 去重
                    while (l<r && nums[r] == nums[r-1]) r--; // 去重
                    r--;
                    l++;
                }
                else if(sum>0) {
                    r--;
                }else l++;
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{0,0,-2,2,2}));
    }
}
