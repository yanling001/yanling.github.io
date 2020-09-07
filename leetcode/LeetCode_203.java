package leetcode;

import java.io.File;

public class LeetCode_203 {
    public ListNode removeElements(ListNode head, int val) {

          ListNode temp=new ListNode(-1);
          while (temp.next!=null){
              if (temp.next.val==val){
                  temp.next=temp.next.next;
                  break;
              }
              temp=temp.next;
          }
          return head;
    }
}
