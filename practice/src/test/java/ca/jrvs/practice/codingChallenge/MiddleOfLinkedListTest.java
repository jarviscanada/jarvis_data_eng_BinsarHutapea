package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({MiddleOfLinkedList.class})
public class MiddleOfLinkedListTest {

    @Test
    public void testGetMiddleNode(){

        MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();

        ListNode nodeOne = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));


        ListNode middleNode = middleOfLinkedList.getMiddleNode(nodeOne);

        int[] expectedResult = {3, 4, 5};

        ArrayList<Integer> actualResult = new ArrayList<Integer>();

        ListNode current = middleNode;

        while(current != null){
            actualResult.add((current.val));
            current = current.next;
        }

        assertArrayEquals(expectedResult, actualResult.stream().mapToInt(Integer::intValue).toArray());
    }
}
