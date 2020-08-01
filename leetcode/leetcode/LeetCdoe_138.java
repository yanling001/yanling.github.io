package leetcode;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.HashSet;

//class Node {
//    public int val;
//    public Node next;
//    public Node random;
//
//    public Node() {
//    }
//
//    public Node(int _val, Node _next, Node _random) {
//        val = _val;
//        next = _next;
//        random = _random;
//    }
//}
public class LeetCdoe_138 {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        HashMap<Node,Node> map=new HashMap<>();
        Node pre=new Node(head.val,null,null);
        map.put(null,null);
        map.put(head,pre);
        Node temp=pre;
        while (head!=null){
            if (map.containsKey(head.next)){
                temp.next=map.get(head.next);
            }
            else {
                Node  node=new Node(head.next.val,null,null);
                map.put(head.next,node);
                temp.next=node;
            }
            if (map.containsKey(head.random)){
                temp.random=map.get(head.random);
            }
            else {
                Node  node=new Node(head.random.val,null,null);
                map.put(head.random,node);
                temp.random=node;
            }
            head=head.next;
            temp=temp.next;
        }
        return pre;
    }
}
