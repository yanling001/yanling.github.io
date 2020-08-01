package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
         List<List<Integer>> ans=new ArrayList<>();
         dfs(ans,0,candidates,target,new ArrayList<Integer>(),0);
         return ans;
    }

    private void dfs(List<List<Integer>> ans, int i, int[] candidates, int target, ArrayList<Integer> list,int s) {
        if (i>target) return;
        if (i==target){
         ans.add(new ArrayList<>(list));
     }

     for (int k=s;k<candidates.length;k++){
             i+=candidates[k];
             list.add(candidates[k]);
             dfs(ans,i,candidates,target,list,k);
             i-=candidates[k];
             list.remove(list.size()-1);
         }

    }

    public static void main(String[] args) {
        //candidates = [2,3,6,7], target = 7,
        LeetCode_39 leetCode_39=new LeetCode_39();
        List<List<Integer>> lists = leetCode_39.combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list:lists){
            for (Integer integer:list){
                System.out.print(integer);
            }
            System.out.println();
        }
    }

}
