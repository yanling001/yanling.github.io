package leetcode;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

public class LeetCode_71 {
    public static String simplifyPath(String path) {
            Stack<String> stack=new Stack<>();
        String[] split = path.split("/");
        for (String te:split){
            System.out.println(te);
        }
        for (int i=0;i<split.length;i++){

            if (split[i]==".")continue;
            else if (split[i]=="..") {
                stack.pop();
                continue;
            }else if(split[i].length()==0){continue;}
            else {
                stack.push(split[i]);
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        List<String> list=new ArrayList<>();
      while (!stack.isEmpty()){
           list.add(stack.pop());
      }
      for (int j=list.size()-1;j>=0;j--){
          stringBuilder.append("/");
          stringBuilder.append(list.get(j));
      }
      return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath(new String("/home//foo/")));
    }
}
