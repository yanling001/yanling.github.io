package leetcode;

import java.util.Stack;

public class 简化路径 {
    public String simplifyPath(String path) {
        Stack<String> stack=new Stack<>();
        String[] paths=path.split("/");
        for (String temp:paths){
            if (temp.equals("..")){
                if (!stack.isEmpty())
                    stack.pop();
            }
            if (!temp.equals(".")&&!temp.equals("")&&!temp.equals("..")){
                stack.push(temp);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
            StringBuilder stringBuilder=new StringBuilder();
            while (!stack.isEmpty()){
                stringBuilder.append("/"+stack.pop(),0,("/"+stack.pop()).length());
            }
        return stringBuilder.toString();

    }
}
