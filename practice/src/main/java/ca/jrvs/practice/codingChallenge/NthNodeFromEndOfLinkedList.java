package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-5ca9ee867e7d457eabd2ee099fc72490
 */
public class NthNodeFromEndOfLinkedList {

    /**
     * O(m)
     * Justification: m is the length of the linked list. This program runs a while loop that iterates through the linked list.
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n){

        int length = 1;

        ListNode current = head;

        while (current.next != null){
            current = current.next;
            length++;
        }

        if (length == 1){
            head = null;
            return head;
        }

        int targetIndex = length - n;

        int index = 0;

        current = head;
        ListNode prev = null;

        while (index < targetIndex){
            prev = current;
            current = current.next;
            index++;
        }

        if (targetIndex == 0){
            head = head.next;
            return head;
        }

        if (current.next == null){
            prev.next = null;
            current = null;
        }
        else{
            prev.next = current.next;
            current = null;
        }

        return head;
    }

}