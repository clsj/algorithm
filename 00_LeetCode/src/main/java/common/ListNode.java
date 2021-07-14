package common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    public ListNode() {}
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void printNode(ListNode header) {

        while (header != null) {
            System.out.print(header.val + "   ");
            header = header.next;
        }
        System.out.println("");
    }

}
