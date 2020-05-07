package leetcode;

import java.util.ArrayList;
import java.util.List;


public class LeetCode_89 {
    public static List<String> grayCode(int n) {
      List<String> ans = new ArrayList<>();
      StringBuilder stringBuilder=new StringBuilder();

      dfs(ans,n,0,stringBuilder);
      return  ans;
    }

    private static void dfs(List<String> ans, int n,int s,StringBuilder temp) {
        if (s==n){
            ans.add(temp.toString());
            return;
        }
        if (s>n) return;
        temp.append("0");
          dfs(ans,n,s+1,temp);
          temp.deleteCharAt(temp.length()-1);
        temp.append("1");
        dfs(ans,n,s+1,temp);
        temp.deleteCharAt(temp.length()-1);
    }

    public static void main(String[] args) {
        List<String> list = grayCode(2);
        for (String s:list){
            System.out.println(s);
        }
    }

}
