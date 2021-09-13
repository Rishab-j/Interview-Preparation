public class SwapNodesInpair {
    
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

    ListNode dummy = new ListNode(-1);
    ListNode p = dummy;
    
    private void addLast(ListNode a){
        a.next = null;
        p.next = a;
        
        p = p.next;
    }
    
    public ListNode swapPairs(ListNode head) {
        
        if(head == null || head.next == null) return head;
        
        ListNode c1 = head;
        
        while(c1 != null && c1.next != null){
            ListNode f = c1.next.next;
            
            addLast(c1.next);
            addLast(c1);
            
            c1 = f;
        }
        
        if(c1 != null){
            addLast(c1);
        }
        
        return dummy.next;
    }
}
