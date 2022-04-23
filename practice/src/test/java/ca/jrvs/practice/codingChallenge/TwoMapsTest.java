package ca.jrvs.practice.codingChallenge;


import org.junit.Test;
import org.junit.runners.Suite;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({TwoMaps.class})
public class TwoMapsTest {

    @Test
    public void testCompareMaps(){
        TwoMaps twoMaps = new TwoMaps();

        HashMap<Integer, Character> m1 = new HashMap<>();
        m1.put(1, 'a');
        m1.put(2, 'b');

        HashMap<Integer, Character> m2 = new HashMap<>();
        m2.put(1, 'a');
        m2.put(2, 'b');

        HashMap<Integer, Character> m3 = new HashMap<>();
        m3.put(3, 'c');

        HashMap<Integer, Character> m4 = new HashMap<>();
        m4.put(1, 'a');
        m4.put(2, 'd');

        boolean caseOneResult = twoMaps.compareMaps(m1, m2);
        boolean caseTwoResult = twoMaps.compareMaps(m1, m3);
        boolean caseThreeResult = twoMaps.compareMaps(m1, m4);

        assertTrue(caseOneResult);
        assertFalse(caseTwoResult);
        assertFalse(caseThreeResult);

    }

}
