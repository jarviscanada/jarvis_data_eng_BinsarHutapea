package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({NthNodeFromEndOfLinkedList.class})
public class NthNodeFormEndOfLinkedListTest {

    @Test
    public void testRemoveNthFromEnd(){

        NthNodeFromEndOfLinkedList nthNodeFromEndOfLinkedList = new NthNodeFromEndOfLinkedList();

        ListNode head = new ListNode(1);

        ListNode caseOneResult = nthNodeFromEndOfLinkedList.removeNthFromEnd(head, 1);

        assertEquals(null, caseOneResult);

        head = new ListNode(1);
        ListNode nodeTwo = new ListNode(2);
        ListNode nodeThree = new ListNode(3);

        head.next = nodeTwo;
        nodeTwo.next = nodeThree;

        ListNode caseTwoResultHead = nthNodeFromEndOfLinkedList.removeNthFromEnd(head, 3);
        int[] expectedCaseTwoResult = {2, 3};

        ArrayList<Integer> actualCaseTwoResult = new ArrayList<Integer>();


        ListNode current = caseTwoResultHead;

        while (current != null){
            actualCaseTwoResult.add(current.val);
            current = current.next;
        }

        assertArrayEquals(expectedCaseTwoResult, actualCaseTwoResult.stream().mapToInt(i->i).toArray());

        ListNode nodeFour = new ListNode(4);
        nodeThree.next = nodeFour;

        ListNode caseThreeResultHead = nthNodeFromEndOfLinkedList.removeNthFromEnd(caseTwoResultHead, 2);
        int[] expectedCaseThreeResult = {2, 4};
        ArrayList<Integer> actualCaseThreeResult = new ArrayList<Integer>();

        current = caseThreeResultHead;

        while (current != null){
            actualCaseThreeResult.add(current.val);
            current = current.next;
        }

        assertArrayEquals(expectedCaseThreeResult, actualCaseThreeResult.stream().mapToInt(i->i).toArray());

        ListNode nodeFive = new ListNode(5);
        nodeFour.next = nodeFive;

        ListNode caseFourResultHead = nthNodeFromEndOfLinkedList.removeNthFromEnd(caseThreeResultHead, 1);
        int[] expectedCaseFourResult = {2, 4};
        ArrayList<Integer> actualCaseFourResult = new ArrayList<Integer>();

        current = caseFourResultHead;

        while (current != null){
            actualCaseFourResult.add(current.val);
            current = current.next;
        }

        assertArrayEquals(expectedCaseFourResult, actualCaseFourResult.stream().mapToInt(i->i).toArray());
    }
}
