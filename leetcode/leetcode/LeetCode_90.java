package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//子集
public class LeetCode_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
           List<List<Integer>> lists = new ArrayList<>();
           Arrays.sort(nums);
           dfs(nums,0,new ArrayList<Integer>(),lists);
           return  lists;
    }

    private void dfs(int[] nums, int i, ArrayList<Integer> list, List<List<Integer>> lists) {
       lists.add(new ArrayList<>(list));
       for (int k=i;k<nums.length;k++){
           if (k>i&& nums[k]==nums[k-1]) continue;
           list.add(nums[k]);
           dfs(nums,k+1,list,lists);
           list.remove(list.size()-1);
       }
    }

    public static void main(String[] args) {
        LeetCode_90 leetCode_90 =new LeetCode_90();
        List<List<Integer>> lists = leetCode_90.subsetsWithDup(new int[]{1, 1, 3});
        for (List<Integer> list :lists){
            for (Integer integer:list){
                System.out.print(integer);
            }
            System.out.println();
        }

    }
}
