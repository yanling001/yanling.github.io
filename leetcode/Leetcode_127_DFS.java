package leetcode;

import java.util.*;

//单词接龙
public class Leetcode_127_DFS {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String,Integer> map=new HashMap<>();
        for(String temp:wordList){
            map.put(temp,0);
        }
        //dfs解法超时了
        return dfs(map,beginWord,endWord,wordList,1);
        //使用
    }
    //dfs
    private static int  dfs(Map<String,Integer> map, String beginWord, String endWord, List<String> wordList, int ans) {
        //   System.out.println(ans);
        //当所有走完 返回零
        //找到了
        if(beginWord.equals(endWord)) {
            return ans;
        }
            Set<String> temp=find(beginWord,wordList);//找到可以为下一步的单词
        int min=0;
        for (String s:temp) {
            if(map.get(s)==1) continue;
            map.put(s,map.get(s)+1);
            int a=dfs(map,s,endWord,wordList,ans+1);
            map.put(s,map.get(s)-1);
            if(a==0) continue;
            if(a!=0){
                if(min==0) min=a;
                else {
                    if(min>a) min=a;
                }
            }
        }
        return min;
    }

    private static Set<String> find(String beginWord, List<String> wordList) {
        Set<String> set=new HashSet<>();
        for (String temp:wordList) {
            if(check(temp,beginWord)){
                set.add(temp);
            }
        }
        return set;
    }

    private  static boolean check(String temp,String word) {
        int k=0;
        for(int i=0;i<temp.length();i++){
            if(k>1) return false;
            if(temp.charAt(i)!=word.charAt(i)) k++;
        }
        if(k==1)
            return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
