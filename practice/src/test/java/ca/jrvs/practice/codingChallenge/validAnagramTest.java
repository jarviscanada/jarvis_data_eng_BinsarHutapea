package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({ValidAnagram.class})
public class validAnagramTest {

    @Test
    public void testAnagramValidation(){

        ValidAnagram validAnagram = new ValidAnagram();

        String stringOne = "abba";
        String stringTwo = "abab";

        assertTrue(validAnagram.validateAnagram(stringOne, stringTwo));

        stringTwo = "abbab";
        assertFalse(validAnagram.validateAnagram(stringOne, stringTwo));

        stringTwo = "aba";
        assertFalse(validAnagram.validateAnagram(stringOne, stringTwo));

        stringTwo = "abbac";
        assertFalse(validAnagram.validateAnagram(stringOne, stringTwo));

    }
}
