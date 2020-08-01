package leetcode;

import java.util.WeakHashMap;

public class 删除有序链表的重复元素2 {
    public ListNode deleteDuplicates(ListNode head) {
         //哑节点+快慢指针
        ListNode dumy=new ListNode(-1);
        dumy.next=head;
        ListNode fast=head;
        ListNode slow=dumy;
        // 3.1 fast 遍历链表
        // 3.2 slow 变更：head 到 slow的链表,已经符合题目要求
        // 3.3 slow.next 变更：删除了重复元素
        while (fast.next!=null){
            if (fast.val!=fast.next.val){//如果当前节点与下一个节点之不同两种可能
                if (slow.next==fast)//1-2的这种那么fast可以接入slow链中即从head到slow满足要求
                    slow=fast;
                else {//否则1-1-1-2-2这种迭代slow此节点舍去
                    slow.next=fast.next;
                }
            }
            fast=fast.next;
        }
      return dumy.next;
    }
}
