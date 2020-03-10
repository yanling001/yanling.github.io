package leetcode;

import java.io.Serializable;

public class 分隔链表   {
    public ListNode partition(ListNode head, int x) {
           ListNode before=new ListNode(0);
           ListNode after=new ListNode(0);
           ListNode beforelast=null;
           while (head!=null) {
               if (head.val < x) {
                   before.next = head;
                   beforelast=before.next;
               } else
                   after.next = head;
               head = head.next;
           }
           beforelast.next=after.next;
           return before.next;
    }
}
