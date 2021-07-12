package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {

        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = cur.next;
        pre.next = null;
        while (true) {
            if (next == null) {
                cur.next = pre;
                break;
            }
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        return cur;
    }
}

//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
//
//输入：head = [1,2]
//输出：[2,1]