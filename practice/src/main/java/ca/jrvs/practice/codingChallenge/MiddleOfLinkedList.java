package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-91c7e64d5923431389a14f4086ed265b
 */
public class MiddleOfLinkedList {

    /**
     * O(n)
     * Justification: Two pointers iterate through the linked list once
     * @param head
     * @return
     */
    public ListNode getMiddleNode(ListNode head){

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;

            //safeguard if the linked list contains a loop
            if (slow == fast)
                return null;

        }

        return slow;
    }

}