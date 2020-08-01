package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_216 {
    int a[]=new int[]{1,2,3,4,5,6,7,8,9};
    public List<List<Integer>> combinationSum3(int k, int n) {
       List<List<Integer>> ans = new ArrayList<>();
       dfs(k,n,0,0,0,ans,new ArrayList<Integer>());
       return ans;
    }

    private void dfs(int k, int n, int sum, int count, int i, List<List<Integer>> ans, ArrayList<Integer> list) {
      if (count>k||sum>n){
          return;
      }
        if (count==k&&sum==n)
          ans.add(new ArrayList<>(list));
      for (int v=i;v<9;v++){
          list.add(a[v]);
          sum+=a[v];
          count++;
          dfs(k,n,sum,count,v+1,ans,list);
          sum-=a[v];
          count--;
          list.remove(list.size()-1);
      }
    }

    public static void main(String[] args) {
        LeetCode_216 leetCode_216=new LeetCode_216();
        List<List<Integer>> lists = leetCode_216.combinationSum3(3,9);
        for (List<Integer> list:lists){
            for (Integer integer:list){
                System.out.print(integer);
            }
            System.out.println();
        }
    }


}
