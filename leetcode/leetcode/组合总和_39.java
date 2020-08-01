package leetcode;

import java.util.*;

public class 组合总和_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans=new ArrayList<>();
        dfs(candidates,target,ans,new ArrayList<Integer>(),0);
         return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans,List<Integer> temp,int start) {
        if (target==0) {
            ans.add(temp);
            return;
        }
        if (target<0)
            return;
        for (int i=start;i<candidates.length;i++){
            if (target>candidates[i]){
                temp.add(candidates[i]);
                dfs(candidates,target-candidates[i],ans,new ArrayList<Integer>(temp),i);
                temp.remove(temp.size()-1);
            }
        }
    }
}
