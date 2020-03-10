package leetcode;

import javafx.util.Pair;

import java.util.*;

//单词接龙
public class Leetcode_127_BFS {
    HashMap<String,ArrayList<String>> allmap;
    //单词接龙
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //双向广度优先搜索
        //预处理
        this.allmap=new HashMap<>();
        for(String temp:wordList){
            for(int i=0;i<temp.length();i++){
                String index=temp.substring(0,i)+"*"+temp.substring(i+1,temp.length());
                ArrayList<String> list=this.allmap.getOrDefault(index,new ArrayList<>());
                list.add(temp);
                allmap.put(index,list);
            }
        }
        //双向广度优先搜索
        //queue 表示队列：从头搜索 从尾搜索
        //Pair<String,Integer> key 当前拜访的单词 和 value 深度 level
        Queue<Pair<String,Integer>> queuestart=new LinkedList<>();
        Queue<Pair<String,Integer>> queueend=new LinkedList<>();
        //初始化队列
        queuestart.add(new Pair<String, Integer>(beginWord,1));
        queueend.add(new Pair<String,Integer>(endWord,1));

        //标记已经走过的 单词
        HashMap<String, Integer> visitbegin=new HashMap();
        HashMap<String, Integer> visitend=new HashMap();
        visitbegin.put(beginWord,1);
        visitend.put(endWord,1);

        //广度优先搜索
        while (!queueend.isEmpty() && !queuestart.isEmpty()){
            //从beginWord 开始搜索
            int temp=  VistWord(queuestart,visitbegin,visitend);
            if(temp>0){
                return temp;
            }

            //从endWord 开始搜索
            temp=  VistWord(queueend,visitend,visitbegin);
            if(temp>0){
                return temp;
            }
        }

        return 0;
    }

    private int VistWord(Queue<Pair<String, Integer>> queuestart, HashMap<String, Integer> visitbegin, HashMap<String, Integer> visitend) {
        //广度 走下一跳
        Pair<String,Integer> pair=queuestart.remove();//拜访当前节点
        String key=pair.getKey();
        Integer value=pair.getValue();
        for(int i=0;i<key.length();i++){
            String k=key.substring(0,i)+"*"+key.substring(i+1,key.length());
            for(String temp:allmap.getOrDefault(k,new ArrayList<>())){

                if (visitend.containsKey(temp)){//表示已经找到了路径
                    return value+visitend.get(temp);
                }
                if (!visitbegin.containsKey(temp)){//当前单词还没被走过
                    visitbegin.put(temp,value+1);
                    queuestart.add(new Pair<>(temp,value+1));
                }
            }
        }

        return -1;
    }


}


