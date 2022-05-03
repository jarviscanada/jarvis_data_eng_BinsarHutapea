package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/LinkedList-Cycle-4409f46394ca4f45a9f2a1dbfe622746
 */
public class LinkedListCycle {

    /**
     * O(n)
     * Justification: the slow pointer iterates through the linked list once and overlaps with the fast pointer before it reaches the end of the loop
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head){

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }

        return false;
    }

}
