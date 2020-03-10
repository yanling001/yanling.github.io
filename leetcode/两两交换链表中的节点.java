
package leetcode;
import java.util.List;

public class 两两交换链表中的节点 {
    public ListNode swapPairs(ListNode head) {
        //链表两两交换   给定 1->2->3->4, 你应该返回 2->1->4->3.
        //递归
        /*
           当前节点的next当做当前面节点
           next的next指向当前节点。
         */
        if (head==null||head.next==null)   //终止条件
            return head;
        ListNode next=head.next;
        head.next=swapPairs(next.next);
        next.next=head;
        return next;
    }


    public ListNode swap(ListNode head) {
        //链表两两交换   给定 1->2->3->4, 你应该返回 2->1->4->3.
        //非递归
        if (head==null||head.next==null)   //终止条件
            return head;
       ListNode pre=new ListNode(0);
       pre.next=head;
       ListNode temp=pre;
       while (temp.next!=null&&temp.next.next!=null){
           ListNode start=temp.next;
           ListNode end = temp.next.next;
       }
       return null;
    }
}
