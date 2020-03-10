package leetcode;

import java.util.ArrayList;
import java.util.List;

//分割回文串
public class LeetCode_131 {
    public List<List<String>> partition(String s) {
      List<List<String>> lists=new ArrayList<>();
        dfs(0,s,lists,new ArrayList<String>());

      return  lists;
    }

    private void dfs(int i, String s, List<List<String>> lists, ArrayList<String> strings) {
        //找出口
        if (i==s.length()-1){
            lists.add(new ArrayList<>(strings));
            return;
        }
        for(int j=i;j<s.length();j++){
            String temp=s.substring(i,j+1);
            if (check(temp)){
                strings.add(temp);
                dfs(j,s,lists,strings);
            }
                continue;
        }//回溯

            strings.remove(strings.size()-1);
    }

    private boolean check(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }
}
