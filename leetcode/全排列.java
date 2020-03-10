package leetcode;

import com.sun.corba.se.spi.ior.IdentifiableContainerBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 全排列 {
   static List<List<Integer>> ans=new ArrayList<List<Integer>>();
    static Stack<Integer> stack=new Stack<>();
    static boolean [] booleans;
    public static void main(String[] args) {
        int test[]=new int[]{1,1,3};
        booleans=new boolean[test.length];

        quansort(test,0);


        for(List<Integer> list:ans){
            System.out.println(list);
        }
    }

    private static void quansort(int[] ints,int detp) {
        if(detp==ints.length){
            ans.add(new ArrayList<>(stack));
            return;
        }
        for(int i=0;i<ints.length;i++){
            if(i>0&&ints[i]==ints[i-1]&&!booleans[i-1]) continue;//相同元素只允许前面的放了后面的才会放 这样就去重了

            if(!booleans[i]) {
                stack.push(ints[i]);
                booleans[i]=true;
                quansort(ints, detp + 1);
                stack.pop();
                booleans[i]=false;
            }
        }
        return  ;
    }
}
