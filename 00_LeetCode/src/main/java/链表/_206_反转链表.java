package 链表;

import common.ListNode;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
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


        ListNode.printNode(node1);

        _206_反转链表 demo = new _206_反转链表();

        ListNode node = demo.reverseList(node1);

        ListNode.printNode(node);
    }

}

//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
//
//输入：head = [1,2]
//输出：[2,1]