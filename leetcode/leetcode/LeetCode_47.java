package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//全排列二
public class LeetCode_47 {
    //排重思路：对nums排序 如果元素
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        Arrays.sort(nums);
        boolean[] flog=new boolean[nums.length];
        for (int i=0;i<nums.length;i++) flog[i]=false;
        dfs(nums,0,lists,new ArrayList<Integer>(),flog);
        return lists;
    }
   private static void dfs(int[] nums, int i, List<List<Integer>> lists, ArrayList<Integer> list, boolean[] flog) {
        if (i==nums.length){
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int k=0;k<nums.length;k++){
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (flog[k]||k!=0&&nums[k]==nums[k-1]&&!flog[k - 1]){
                continue;
           }
            list.add(nums[k]);
            flog[k]=true;
            dfs(nums,i+1,lists,new ArrayList<>(list),flog);
            flog[k]=false;
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = permuteUnique(new int[]{1, 2, 3});
        for (List<Integer> list:lists){
            for (Integer integer:list){
                System.out.print(integer);
            }
            System.out.println();
        }
    }

}


