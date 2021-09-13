public class AddTwoNumbers2 {

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

    private ListNode reverse(ListNode head){
        
        ListNode curr = head;
        ListNode prev = null;
        
        while(curr != null){
            
            ListNode forw = curr.next;
            
            curr.next = prev;
            
            prev = curr;
            
            curr = forw;
        }
        
        return prev;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        if(l1 == null && l2 == null) return null;
        
        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        
        ListNode dummy = new ListNode(-1);
        ListNode d = dummy;
        
        int carry = 0;
        
        while(r1 != null || r2 != null){
            
            int sum = 0;
            
            if(r1 != null) sum += r1.val;
            if(r2 != null) sum += r2.val;
            
            sum += carry;
            
            ListNode node = new ListNode(sum % 10);
            d.next = node;
     
            carry = sum / 10;
            
            d = d.next;
            
            if(r1 != null) r1 = r1.next;
            if(r2 != null) r2 = r2.next;   
        }
        
        if(carry != 0){
            ListNode node = new ListNode(carry);
            d.next = node;
        }
        
        return reverse(dummy.next);
    }
}
