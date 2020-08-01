package leetcode;

import java.util.LinkedList;
import java.util.Queue;

//117. 填充每个节点的下一个右侧节点指针 II
 public   class LeetCode_117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
        public Node connect(Node root) {
            //相当于树的逐层遍历
            Queue<Node> queue=new LinkedList<>();
            queue.add(root);
            //逐层遍历
            Node pre,last;
            pre=last=root;
            while (queue.isEmpty()){
                Node temp=queue.poll();
                if(temp!=null){
                    if(temp.left!=null){
                        queue.add(temp.left);
                        last=temp.left;
                    }
                    if(temp.right!=null)
                    {
                        queue.add(temp.right);
                        last=temp.right;
                    }
                    if (getpreLast(pre)==last){
                         pre=last;
                         queue.add(null);
                    }
                   temp.next=queue.peek();
                }
            }
            return  root;
    }

    private Node getpreLast(Node pre) {
            if (pre.right!=null) return  pre.right;
            return  pre.left;
    }
}
