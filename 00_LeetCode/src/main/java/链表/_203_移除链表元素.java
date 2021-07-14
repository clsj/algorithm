package 链表;

import common.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {

        ListNode pre = new ListNode(0);
        ListNode h = pre;
        pre.next = head;

        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                if (next == null) {
                    pre.next = null;
                    cur = null;
                }else {
                    cur.val = next.val;
                    pre.next = next;
                    cur = next;
                }
            }else {
                pre = cur;
                cur = next;
            }
        }

        return h.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(4);
        node4.next = node5;
        ListNode node6 = new ListNode(4);
        node5.next = node6;

        ListNode.printNode(node1);

        _203_移除链表元素 v = new _203_移除链表元素();
        ListNode listNode = v.removeElements(node1, 4);
        ListNode.printNode(listNode);


    }

}
