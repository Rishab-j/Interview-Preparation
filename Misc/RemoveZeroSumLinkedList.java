import java.util.*;

public class RemoveZeroSumLinkedList {
    class ListNode {
        ListNode next;
        int val;

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

    class Solution {


        // Method 1

        public ListNode removeZeroSumSublists_1(ListNode head) {

            ListNode dummy = new ListNode(0), cur = dummy;
            dummy.next = head;
            int prefix = 0;
            Map<Integer, ListNode> m = new HashMap<>();
            while (cur != null) {
                prefix += cur.val;
                if (m.containsKey(prefix)) {
                    cur = m.get(prefix).next;
                    int p = prefix + cur.val;
                    while (p != prefix) {
                        m.remove(p);
                        cur = cur.next;
                        p += cur.val;
                    }
                    m.get(prefix).next = cur.next;
                } else {
                    m.put(prefix, cur);
                }
                cur = cur.next;
            }
            return dummy.next;
        }

        // Method 2

        public ListNode removeZeroSumSublists_2(ListNode head) {
            int prefix = 0;
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            Map<Integer, ListNode> seen = new HashMap<>();
            seen.put(0, dummy);
            for (ListNode i = dummy; i != null; i = i.next) {
                prefix += i.val;
                seen.put(prefix, i);
            }
            prefix = 0;
            for (ListNode i = dummy; i != null; i = i.next) {
                prefix += i.val;
                i.next = seen.get(prefix).next;
            }
            return dummy.next;
        }
    }
}
