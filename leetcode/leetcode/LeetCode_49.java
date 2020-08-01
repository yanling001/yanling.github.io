package leetcode;

import java.awt.font.FontRenderContext;
import java.util.*;

//字母异位词分组
public class LeetCode_49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans=new ArrayList<>();
        HashMap<HashSet<Character>,List<String>> list=new HashMap<>();
        for (int i=0;i<strs.length;i++){
            //    System.out.println(i);
            List<String> temp=iscontent(strs[i],list);
            // System.out.println(temp);
            if (temp==null){
                HashSet<Character> set=new HashSet<>();
                for (int  te=0;te<strs[i].length();te++){
                    set.add(strs[i].charAt(te));
                }
                List<String> list1=new ArrayList();
                list1.add(strs[i]);
                list.put(set,list1);
            }else{
                temp.add(strs[i]);
            }
        }
        for (Map.Entry<HashSet<Character>, List<String>> entry:list.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
    }

    private List<String> iscontent(String str, HashMap<HashSet<Character>, List<String>> list) {
        for (Map.Entry<HashSet<Character>, List<String>> entry:list.entrySet()){
            HashSet<Character> key = entry.getKey();
            for (int t=0;t<str.length();t++){
                if (key.contains(str.charAt(t))){
                    if (t==str.length()-1) return entry.getValue();
                    continue;
                }else break;
            }
        }
        return  null;
    }
}
