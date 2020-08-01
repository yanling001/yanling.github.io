package leetcode;
//重排链表
public class LeetCode_143 {
    public void reorderList(ListNode head) {
        if(head == null){
            return ;
        }
        //1. 使用快慢指针,找出链表的中心节点。
        // 1->2->3->4->5,中心节点为3
        ListNode middle = middleNode(head);

        //2. 将原始链表按照中心链表分割为两个链表，并将右链表反转
        //2.1 原始链表：1->2->3->4->5 左链表：1->2->3 右链表：4->5
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;

        //2.2 反转右链表
        //原始右链表：4->5 反转后：5->4
        right = reverse(right);

        //3. 合并两个链表，将右链表插入到左链表
        //左链表：1->2->3 右链表：4->5 合并后：1->5->2->4->3
        merge(left,right);
    }

    //1. 使用快慢指针,找出链表的中心节点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //2. 通过递归反转链表
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    //3. 合并两个链表，将右链表插入到左链表
    public void merge(ListNode left, ListNode right){
        ListNode leftTemp;
        ListNode rightTemp;
        while (left.next != null && right!= null) {
            //1. 保存next节点
            leftTemp = left.next;
            rightTemp = right.next;

            //2. 将右链表的第一个节点插入到左链表中
            // 左链表：1->2->3 右链表：5->4
            // 合并后的左链表：1->5->2->3
            left.next = right;
            right.next = leftTemp;

            //3. 移动left和right指针
            //左链表变为：2->3 右链表变为：4
            left = leftTemp;
            right = rightTemp;
        }
    }
}
