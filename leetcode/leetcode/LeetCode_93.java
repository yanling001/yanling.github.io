package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_93 {
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans=new ArrayList<>();
        dfs(ans,s,0,new ArrayList<String>());
        return  ans;
    }

    private  static  void dfs(List<String> ans, String s, int i, List<String> list) {
      if (i==s.length()&&list.size()==4){

          StringBuilder stringBuilder=new StringBuilder();
          for (String s1:list){
              stringBuilder.append(s1);
              stringBuilder.append(".");
          }
          stringBuilder.deleteCharAt(stringBuilder.length()-1);
          ans.add(new String(stringBuilder));
          return;
      }
      if (list.size()>3) return;
         int min= Math.min(i+3,s.length());
         for (int m=i+1;m<=min;m++){
             int right=i; int left=m;
             String temp=s.substring(right, left);
              if (temp.length()>=2&& temp.charAt(0)=='0') return;
             if (Integer.parseInt(temp)>255) return;

             else{
                 list.add(temp);
                 dfs(ans,s,left,list);
                 list.remove(list.size()-1);
             }
         }
     }



    public static void main(String[] args) {
        List<String> list = restoreIpAddresses("010010");
       for (String s:list){
           System.out.println(s);
       }
    }
}
