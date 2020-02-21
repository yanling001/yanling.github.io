package leetcode;

//反转链表 2
public class LeetCode_92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n) return head;
        ListNode left,right,temp;
        ListNode pre,last;
        pre=null;
        left=right=temp=head;

        int count=1;
        //找到第m,n个节点
        while(temp!=null){

            if(count==m-1) pre= temp;
            if(count==m) left=temp;
            if(count==n) {
                right=temp;
                break;
            }
            temp=temp.next;
            count++;
        }
        if(m==1){
            last=right.next;
            right.next=null;
            ListNode re=   resvers(right,left);
            left.next=last;
            return re;
        }
        last=right.next;
        right.next=null;
        pre.next=null;
        ListNode re=   resvers(right,left);
        left.next=last;
        pre.next=right;
        return head;
    }

    private ListNode resvers(ListNode right, ListNode left) {
        if(left==null) return left;
        if(right==left) return left;
        ListNode temp=resvers(right,left.next);
        left.next.next=left;
        left.next=null;
        return  temp;
    }

}
