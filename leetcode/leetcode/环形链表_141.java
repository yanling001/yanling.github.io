package leetcode;

public class 环形链表_141 {
    public boolean hasCycle(ListNode head) {
      ListNode listNode=head;
      ListNode listNode1=head;
      while(listNode!=null||listNode1!=null){
          listNode=listNode1.next.next;
          listNode1=listNode1.next;
          if (listNode==listNode1)
              return true;
      }
      return false;
    }

}
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }