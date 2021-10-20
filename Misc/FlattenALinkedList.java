public class FlattenALinkedList {

    /** Leetcode 430 Flatten a Multilevel Doubly Linked List */

    class Node{
        Node child;
        Node next;
        Node prev;
    }

    public Node flatten(Node head) {
        if( head == null) return head;
        Node p = head; 
        while( p!= null) {
            if(p.child != null){
                Node temp = p.child;
                // Find the tail of the child
                while( temp.next != null ) 
                    temp = temp.next;
                // Connect tail with p.next, if it is not null
                temp.next = p.next;  
                if( p.next != null )  p.next.prev = temp;
                // Connect p with p.child, and remove p.child
                p.next = p.child; 
                p.child.prev = p;
                p.child = null;
            }
            p = p.next;
        }
        return head;
    }
    
}
