package leetcode.程序员面试金典;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 有重复串的排列组合 {
    public String[] permutation(String S) {
        List<String> ans=new ArrayList<>();
        dfs(ans,S,0,new StringBuilder());
        for (String string:ans)
        {
            System.out.println(string);
        }
        return null;
    }

    private void dfs(List<String> ans, String s, int i, StringBuilder stringBuilder) {
          if (stringBuilder.length()==s.length()){
              ans.add(stringBuilder.toString());
              return;
          }
          for (int j=0;j<s.length();j++){
              stringBuilder.append(s.charAt(j));
              dfs(ans,s,j+1,new StringBuilder(stringBuilder));
              stringBuilder.deleteCharAt(stringBuilder.length()-1);
          }
    }

    public static void main(String[] args) {
        有重复串的排列组合 ms=new 有重复串的排列组合();
        ms.permutation(new String("adc"));
    }
}
