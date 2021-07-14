package 链表;

import common.ListNode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {

        ListNode slowNode = head.next;
        if (slowNode == null) {
            return false;
        }
        ListNode fastNode = slowNode.next;

        while (true) {
            if (fastNode == slowNode) {
                return true;
            }
            slowNode = slowNode.next;

            if (slowNode == null) {
                return false;
            }

            if (fastNode.next == null || fastNode.next.next == null) {
                return false;
            }else {
                fastNode = fastNode.next.next;
            }
        }

    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;

//        node6.next = node4;

        _141_环形链表 v = new _141_环形链表();
        System.out.println(v.hasCycle(node1));

    }

}
