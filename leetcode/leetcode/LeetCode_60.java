package leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LeetCode_60 {
   static String ans;
   static  int count;
    static int a[]=new int[]{1,2,3,4,5,6,7,8,9};
    public static  String getPermutation(int n, int k) {
        dfs(n,k,new ArrayList<Integer>());
        return ans;
    }

    private static void dfs(int n, int k, List<Integer> stringBuilder) {
       // System.out.println(stringBuilder.toString());
        if (stringBuilder.size()==n) {
            count++;
        }
        if (count==k){
            StringBuilder stringBuilder1=new StringBuilder();
            for (Integer integer:stringBuilder){
                stringBuilder1.append(integer);
            }
            ans=new String(stringBuilder1.toString());
            count++;
            return;
        }


        for (int s=0;s<n;s++){
            if (!stringBuilder.contains(a[s])){
                stringBuilder.add(a[s]);
                dfs(n,k,stringBuilder);
                stringBuilder.remove(stringBuilder.size()-1);

            }

        }
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3,1));
    }
}
