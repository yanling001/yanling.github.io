package leetcode;

public class 合并K个排序链表_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0)
            return null;
        if (lists.length==1)
            return lists[0];
        if (lists.length==2)
            return mergeTow(lists[0],lists[1]);
        //二分它
        int mid =lists.length/2;
        ListNode[] left=new ListNode[mid];
        for (int i=0;i<mid;i++){
            left[i]=lists[i];
        }
        ListNode[] right=new ListNode[lists.length-mid];
        for (int i=mid,j=0;i<lists.length;i++,j++){
            right[j]=lists[i];
        }
        return mergeTow(mergeKLists(left),mergeKLists(right));
    }

    private ListNode mergeTow(ListNode listNode, ListNode listNode1) {
        if (listNode==null){
            return listNode1;
        }
        if (listNode1==null){
            return listNode;
        }
        ListNode head=null;
        if (listNode.val <= listNode1.val){
            head = listNode;
            head.next = mergeTow(listNode.next, listNode1);
        } else {
            head = listNode1;
            head.next = mergeTow(listNode, listNode1.next);
        }
        return head;
    }
}
