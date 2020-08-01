package leetcode.程序员面试金典;

import java.util.Stack;

public class 栈排序 {
    Stack<Integer> stack;
    Stack<Integer> stack1;
    public 栈排序() {
      stack=new Stack<>();
      stack1=new Stack<>();
    }

    public void push(int val) {
     if (stack.isEmpty()){
         stack.push(val);
     }else {
         if (stack.peek()<val){
             while (!stack.isEmpty()&& stack.peek()<val){
                 stack1.push(stack.pop());
             }
             stack.push(val);
             while (!stack1.isEmpty()){
                 stack.push(stack1.pop());
             }
         }
     }

    }

    public void pop() {
      stack.pop();
    }

    public int peek() {
      return stack.peek();
    }

    public boolean isEmpty() {
      return stack.isEmpty();
    }
}

