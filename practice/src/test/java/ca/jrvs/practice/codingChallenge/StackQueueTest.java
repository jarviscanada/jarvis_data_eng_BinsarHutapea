package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({StackQueue.class})
public class StackQueueTest {

    @Test
    public void testQueueOperationsWithStack(){


        StackQueue stackQueue = new StackQueue();

        stackQueue.push(4);

        assertEquals(false, stackQueue.empty());


        int firstPoppedElement = stackQueue.pop();
        int expectedFirstPoppedElement = 4;

        assertEquals(expectedFirstPoppedElement, firstPoppedElement);

        assertEquals(true, stackQueue.empty());

        for (int i = 0; i <= 4; i++){
            stackQueue.push(i);
        }

        for (int i = 0; i <= 4; i++){
            int poppedElement = stackQueue.pop();
            int expectedPoppedElement = i;
            assertEquals(expectedPoppedElement, poppedElement);
        }

        assertEquals(true, stackQueue.empty());

    }

}
