package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-e3c1bfbc80f64dc78464d6491edfcf1f
 */
public class TwoMaps {

    /**
     * O(n)
     * Justification: equals method in AbstractMap class iterates through the entry set of the first map
     * @param m1 first map
     * @param m2 second map
     * @param <K> type of key
     * @param <V> type of value
     * @return boolean value if two maps are equals or not
     */
    public <K, V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){

        if (m1.size() != m2.size())
            return false;

        boolean result = m1.equals(m2);

        return result;

    }

}
