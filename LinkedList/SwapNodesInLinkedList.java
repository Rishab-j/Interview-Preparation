public class SwapNodesInLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapNodes(ListNode head, int k) {
        
        ListNode first = head;
        ListNode second = head;
        
        while(k-- > 1){
            first = first.next;
        }
        
        ListNode p = first;
        
        while(p.next != null){
            p = p.next;
            second = second.next;
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        
        return head;
    }
}
