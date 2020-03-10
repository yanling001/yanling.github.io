package leetcode;

public class 复制随机指针链表 {

    public Node copyRandomList(Node head) {
        Node temp = head;
        return temp;
    }
}
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}