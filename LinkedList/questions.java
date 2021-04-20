import java.util.*;

class questions {


    // CLONE A LINKED LIST

    class Node {
        int data;// Node data
        Node next, random;// Next and random reference

        // Node constructor
        public Node(int data) {
            this.data = data;
            this.next = this.random = null;
        }
    }

    public Node clone(Node head) {    // Time : O(2N)  and  Space : O(N)
        // Initialize two references, one with original
        // list's head.
        Node first = head;
        Node origCurr = head, cloneCurr = null;

        // Hash map which contains node to node mapping of
        // original and clone linked list.
        Map<Node, Node> map = new HashMap<Node, Node>();

        // Traverse the original list and make a copy of that
        // in the clone linked list.
        while (origCurr != null) {
            cloneCurr = new Node(origCurr.data);
            map.put(origCurr, cloneCurr);
            origCurr = origCurr.next;
        }

        // Adjusting the original list reference again.
        origCurr = first;

        // Traversal of original list again to adjust the next
        // and random references of clone list using hash map.
        while (origCurr != null) {
            cloneCurr = map.get(origCurr);
            cloneCurr.next = map.get(origCurr.next);
            cloneCurr.random = map.get(origCurr.random);
            origCurr = origCurr.next;
        }

        // return the head reference of the clone list.
        return (map.get(first));
    }


    public Node clone_2(Node start){         // Time: O(3N) and Space : O(1)
        Node curr = start, temp = null;

        // insert additional node after
        // every node of original list
        while (curr != null) {
            temp = curr.next;

            // Inserting node
            curr.next = new Node(curr.data);
            curr.next.next = temp;
            curr = temp;
        }
        curr = start;

        // adjust the random pointers of the
        // newly added nodes
        while (curr != null) {
            if (curr.next != null)
                curr.next.random = (curr.random != null) ? curr.random.next : curr.random;

            // move to the next newly added node by
            // skipping an original node
            curr = (curr.next != null) ? curr.next.next : curr.next;
        }

        Node original = start, copy = start.next;

        // save the start of copied linked list
        temp = copy;

        // now separate the original list and copied list
        while (original != null && copy != null) {
            original.next = (original.next != null) ? original.next.next : original.next;

            copy.next = (copy.next != null) ? copy.next.next : copy.next;
            original = original.next;
            copy = copy.next;
        }
        return temp;
    }

}