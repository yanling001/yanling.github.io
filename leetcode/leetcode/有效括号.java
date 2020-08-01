package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 有效括号 {
    static Map<Character,Character> map=new HashMap();
    public static boolean isValid(String s) {
        Stack<Character> stack=new Stack<Character>();
        for (int i=0;i<s.length();i++){
            if (stack.empty()){
                stack.push(s.charAt(i));
                continue;
            }
            if (map.get(stack.peek()).equals(s.charAt(i))){
                     stack.pop();
            }else stack.push(s.charAt(i));
        }
        if (stack.empty())
            return true;
        else return false;
    }





    public static void main(String[] args) {
        map.put('{','}');
        map.put('[',']');
        map.put('(',')');
        System.out.println(isValid("{{{}}}[[]]"));
    }
}
