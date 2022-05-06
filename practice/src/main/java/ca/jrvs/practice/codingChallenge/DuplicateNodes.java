package ca.jrvs.practice.codingChallenge;

import java.util.*;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-dfcf1232fbeb4132ac1649da5131c3e6
 */
public class DuplicateNodes {

    /**
     * O(n)
     * Justification: The program iterates through the input linked list, with n is the size of the list.
     * @param integerList
     */
    public void removeDuplicateNodes(LinkedList<Integer> integerList){

        Set<Integer> set = new LinkedHashSet<Integer>();

        integerList.stream().forEach( n -> set.add(n));

        while(!integerList.isEmpty()){
            integerList.remove();
        };

        set.stream().forEach(n -> integerList.add(n));
    }
}