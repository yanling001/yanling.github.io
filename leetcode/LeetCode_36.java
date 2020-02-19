package leetcode;

import java.util.HashMap;
//有效的数独
public class LeetCode_36 {
 public  static Boolean anstest(char [][]a){
     //创建HashMap集合的数组
     HashMap<Integer,Integer> rows []=new HashMap[9];
     HashMap<Integer,Integer> cloms []=new HashMap[9];
     HashMap<Integer,Integer> brocks []=new HashMap[9];
     for(int i=0;i<9;i++){
         rows[i]=new HashMap<Integer, Integer>();
         cloms[i]=new HashMap<Integer, Integer>();
         brocks[i]=new HashMap<Integer, Integer>();
     }
     for(int i=0;i<9;i++){
         for(int j=0;j<9;j++){
             if(a[i][j]!='.'){
                 int brock=(i/3)*3+j/3;
                 int n= (int)a[i][j];
                 rows[i].put(n,rows[i].getOrDefault(n,0)+1);
                 cloms[j].put(n,cloms[i].getOrDefault(n,0)+1);
                 brocks[brock].put(n,brocks[brock].getOrDefault(n,0)+1);
                 if(rows[i].get(n)>1||cloms[j].get(n)>1||brocks[brock].get(n)>1) return false;
             }

         }
     }
     return true;
 }
}
