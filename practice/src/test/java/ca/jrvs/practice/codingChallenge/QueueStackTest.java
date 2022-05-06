package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({QueueStack.class})
public class QueueStackTest {

    @Test
    public void testStackOperationsWithQueue(){

        QueueStack queueStack = new QueueStack();

        queueStack.push(4);

        assertEquals(false, queueStack.empty());


        int firstPoppedElement = queueStack.pop();
        int expectedFirstPoppedElement = 4;

        assertEquals(expectedFirstPoppedElement, firstPoppedElement);

        assertEquals(true, queueStack.empty());

        for (int i = 0; i <= 4; i++){
            queueStack.push(i);
        }

        for (int i = 4; i >= 0; i--){
            int poppedElement = queueStack.pop();
            int expectedPoppedElement = i;
            assertEquals(expectedPoppedElement, poppedElement);
        }

        assertEquals(true, queueStack.empty());

    }

}
