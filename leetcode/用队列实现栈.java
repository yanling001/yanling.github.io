package leetcode;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int top;
    /** Initialize your data structure here. */
    public MyStack() {
        queue1=new LinkedList();
        queue2=new LinkedList();

    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
        top=x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
         while (queue1.size()>1){
             int temp=queue1.poll();
             queue2.add(temp);
         }

         Queue<Integer> temp=queue1;
         queue1=queue2;
         queue2=temp;
         return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
       return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
         return queue1.isEmpty();
    }
}
