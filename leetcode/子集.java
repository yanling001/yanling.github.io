package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class 子集 {
    public List<List<Integer>> subsets(int[] nums) {
                List<List<Integer>> ans=new ArrayList<>();
                dfs(0,ans,nums,new ArrayList<Integer>());
                return ans;
    }

    private void dfs(int i, List<List<Integer>> ans, int[] nums, ArrayList<Integer> temp) {
        ans.add(new ArrayList<>(temp));
        for (int j=i;i<nums.length;i++){
            temp.add(nums[i]);
            dfs(i+1,ans,nums,temp);
            temp.remove(temp.size()-1);
        }
    }

}
