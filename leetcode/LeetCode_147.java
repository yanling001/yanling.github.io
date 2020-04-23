package leetcode;
//链表的插入排序
public class LeetCode_147 {
    public ListNode insertionSortList(ListNode head) {
        // 创建带头结点赋初值为         最小值
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        ListNode cur = head;
        while (cur != null) {
            // 如果是升序，则遍历下一个
            if (end.val < cur.val) {
                end.next = cur;
                end = cur;
                cur = cur.next;
            } else {
                // 先定义一个 tmp 指向 cur 下一个结点防止丢链
                ListNode tmp = cur.next;
                // 断开要排的元素
                end.next = tmp;
                // 从头判断找出合适的插入位置
                while ( pre.next.val < cur.val) {
                    pre = pre.next;
                }
                // 找到插入位置后直接将元素放进来
                cur.next = pre.next;
                pre.next = cur;
                // 随后将定位指针归为
                pre = dummy;
                cur = tmp;
            }
        }
        return dummy.next;
    }
}
