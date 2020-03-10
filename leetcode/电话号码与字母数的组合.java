package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 电话号码与字母数的组合 {
    static Map<Integer,String> map=new HashMap();

    public static List<String> letterCombinations(String digits) {
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");

         int n=digits.length();
         List<String> ans=new ArrayList();
         findlist(ans,digits,0,new StringBuilder(),"");
         return ans;
    }

    private static  void findlist(List<String> ans, String digits, int i,StringBuilder stringBuilder,String now) {
        stringBuilder.append(now);
        if (i==digits.length()) {
            ans.add(stringBuilder.toString());
            return;
        }

        String temp=map.get(Integer.parseInt(digits.charAt(i)+""));
        System.out.println(temp);
        int length=temp.length();
        for (int n=0;n<length;n++){
            findlist(ans,digits,i+1,new StringBuilder(stringBuilder),temp.charAt(n)+"");
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }
}
