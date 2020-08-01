package leetcode.程序员面试金典;

import java.util.HashMap;

public class 最短包含串 {
    public static int[] shortestSeq(int[] big, int[] small) {
        if (big.length == 0 || small.length == 0) {
            return new int[0];
        }
        HashMap<Integer,Integer> bigmap=new HashMap<>();
        HashMap<Integer,Integer> smallmap =new HashMap<>();
      for (int i=0;i<small.length;i++){
          smallmap.put(small[i],smallmap.getOrDefault(small[i],0)+1);
      }
      int hope=smallmap.size();
      int real=0;
      int left,right;
      int ans[]=new int[]{-1,-1};
      right=left=0;
      while (right<big.length){
          int temp=big[right];
          bigmap.put(temp,bigmap.getOrDefault(temp,0)+1);
          if (bigmap.get(temp)==smallmap.get(temp)){
              real++;
          }
          while (real == hope){
              if (ans[0]==-1||ans[1]-ans[0]>right-left){
                  ans[0]=left;
                  ans[1]=right;
              }
              if (smallmap.containsKey(big[left]) &&
                      bigmap.get(big[left])== smallmap.get(big[left])){
                  real--;
              }
              bigmap.put(big[left],bigmap.get(big[left])-1);
              left++;
          }
          right++;
      }
      return ans[0] == -1 ? new int[0]:ans;
    }

    public static void main(String[] args) {
        int[] ints = shortestSeq(new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}, new int[]{1, 5, 9});
        for (int a:ints){
            System.out.println(a);
        }
    }
}
