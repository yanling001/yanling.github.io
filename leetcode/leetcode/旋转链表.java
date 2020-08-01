package leetcode;

public class 旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
       //将链表成环
        ListNode temp=head;
        int count=0;
        while (temp.next!=null){
            temp=temp.next;
            count++;
        }
        temp.next=head;
        for (int i=0;i<=count-k%count;i++){
            temp=temp.next;
        }
        ListNode ans=temp.next;
        temp.next=null;
        return ans;
    }
}
