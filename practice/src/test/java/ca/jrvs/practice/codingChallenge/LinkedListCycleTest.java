package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({LinkedListCycle.class})
public class LinkedListCycleTest {

    @Test
    public void testCheckCycle(){

        LinkedListCycle linkedListCycle = new LinkedListCycle();

        ListNode nodeOne = new ListNode(1);
        ListNode nodeTwo = new ListNode(2);
        ListNode nodeThree = new ListNode(3);
        ListNode nodeFour = new ListNode(4);

        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;
        nodeFour.next = nodeTwo;

        boolean caseOneActualValue = linkedListCycle.hasCycle(nodeOne);

        assertTrue(caseOneActualValue);

        nodeFour.next = null;

        boolean caseTwoActualValue = linkedListCycle.hasCycle(nodeOne);

        assertFalse(caseTwoActualValue);


    }
}
