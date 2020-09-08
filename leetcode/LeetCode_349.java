package leetcode;

import org.omg.PortableInterceptor.ServerRequestInfo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class LeetCode_349 {
    public String decodeString(String s) {
        LinkedList<String> stack=new LinkedList<>();
        Stack<Integer> number=new Stack<>();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<s.length();i++){
           if ( s.charAt(i)>='0'&&s.charAt(i)<='9'){
                StringBuilder numb=new StringBuilder();
                for (int  j=i;j<s.length();j++){
                    if (s.charAt(j)>='0'&&s.charAt(j)<='9'){
                        numb.append(s.charAt(j));
                    }else {
                       i=j-1;
                       break;
                    }
                }
                number.add(Integer.parseInt(numb.toString()));
            }else if (s.charAt(i)=='['||Character.isLetter(s.charAt(i))){
              stack.add(s.charAt(i)+"");

        }else if (s.charAt(i)==']'){
               StringBuilder stringBuilder1=new StringBuilder();
           while (!stack.get(stack.size()-1).equals("[")){
               System.out.println(stack.get(stack.size()-1));
               stringBuilder1.append(stack.get(stack.size()-1));
               stack.remove(stack.size()-1);

           }
               stack.remove(stack.size()-1);
           stringBuilder1.reverse();
           int temp=number.pop();
               System.out.println(temp);
           for (int j=0;j<temp;j++){
               for (int w=0;w<stringBuilder1.length();w++)
               stack.add(stringBuilder1.charAt(w)+"");
           }
           }
        }
        for (String t:stack){
            stringBuilder.append(t);
        }
        return stringBuilder.toString();


    }

    public static void main(String[] args) {
        LeetCode_349 leetCode_349=new LeetCode_349();
        System.out.println(leetCode_349.decodeString("3[a2[c]]"));
    }
}
