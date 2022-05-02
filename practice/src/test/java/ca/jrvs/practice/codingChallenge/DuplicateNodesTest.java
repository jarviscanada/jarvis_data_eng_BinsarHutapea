package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({DuplicateNodes.class})
public class DuplicateNodesTest {

    @Test
    public void testRemoveDuplicateNodes(){

        DuplicateNodes duplicateNodes = new DuplicateNodes();

        LinkedList<Integer> caseOneList = new LinkedList<Integer>();
        caseOneList.add(8);
        caseOneList.add(4);
        caseOneList.add(4);
        caseOneList.add(9);
        caseOneList.add(2);
        caseOneList.add(2);
        caseOneList.add(2);

        int[] expectedResult = {8, 4, 9, 2};

        duplicateNodes.removeDuplicateNodes(caseOneList);

        int[] actualResultArr = caseOneList.stream().mapToInt(Integer::intValue).toArray();

        assertArrayEquals(expectedResult, actualResultArr);

    }
}
