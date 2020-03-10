package leetcode;

import java.util.HashMap;
import java.util.Map;

public class 罗马数转整数 {
    public int romanToInt(String s) {
        Map<String,Integer> map=new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        int pre=0;
        int ans=0;
        for (int i=0;i<s.length();i++){
            int temp=map.get(s.charAt(i)+"");
            if (temp>pre&&pre!=0){
                ans=ans-pre;
                System.out.println(ans);
                ans=temp-pre;
            }else
                ans=ans+temp;

        }
      return ans;
    }
}
