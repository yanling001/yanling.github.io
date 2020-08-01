package leetcode;

import java.util.*;

public class HUISU_784 {
    public static  void main(String args[]){
      int [] a={1,2,3,4};

           System.out.println(  permute(a));

    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
           dfs(nums,0,list);
           return list;
    }

    private  static void dfs(int [] s, int i, List<List<Integer>> list) {

        if(i==s.length-1) {
            List<Integer> list1=new ArrayList<Integer>();
          for (int t:s){
              list1.add(t);
          }
          list.add(list1);
        }
        for (int j=i;j<s.length;j++){
           //交换
            int  temp=s[i]; s[i]=s[j]; s[j]=temp;
            dfs(s,i+1,list);
            //回溯
            temp=s[i]; s[i]=s[j]; s[j]=temp;
        }
    }
}
