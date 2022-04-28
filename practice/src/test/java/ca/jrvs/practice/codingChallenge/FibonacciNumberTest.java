package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({FibonacciNumber.class})
public class FibonacciNumberTest {

    @Test
    public void testFibonacciNumberRetrieval(){

        FibonacciNumber fibonacciNumber = new FibonacciNumber();

        int caseOneNum = fibonacciNumber.getFibonacciNumberWithRecursion(1);
        int expectedCaseOneResult = 1;

        assertEquals(expectedCaseOneResult, caseOneNum);

        int caseTwoNum = fibonacciNumber.getFibonacciNumberWithRecursion(6);
        int expectedCaseTwoResult = 8;

        assertEquals(expectedCaseTwoResult, caseTwoNum);

        int caseOneDPNum = fibonacciNumber.getFibonacciNumberWithDP(1);

        assertEquals(expectedCaseOneResult, caseOneDPNum);

        int caseTwoDPNum = fibonacciNumber.getFibonacciNumberWithDP(6);

        assertEquals(expectedCaseTwoResult, caseTwoDPNum);


    }

}
