package leetcode;

public class 删除链表的倒数第N个节点_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode botten = head;
        while(n>0){
            first=first.next;
            n--;
        }
        while (first!=null){
            first=first.next;
            botten=botten.next;
        }
        if (botten.next.next!=null)
        botten.next=botten.next.next;
        else
            botten.next=null;
        return head;
    }
}
