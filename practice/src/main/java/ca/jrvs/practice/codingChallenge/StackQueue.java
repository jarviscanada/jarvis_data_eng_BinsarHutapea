package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-3ebc93613e044deda0e4efd86d1b77bf
 */
public class StackQueue {

    Stack<Integer> mainStack;
    Stack<Integer> auxiliaryStack;

    public StackQueue(){
        this.mainStack = new Stack<>();
        this.auxiliaryStack = new Stack<>();
    }

    /**
     * O(n)
     * Justification: transferring existing elements between main queue and auxiliary queue requires iterating through the stacks
     * @param element
     */
    public void push(int element){
        if (this.mainStack.isEmpty())
            this.mainStack.push(element);
        else{
            while(!this.mainStack.isEmpty()){
                int currentElement = this.mainStack.pop();
                this.auxiliaryStack.push(currentElement);
            }
            this.mainStack.push(element);
            while(!this.auxiliaryStack.isEmpty()){
                int currentElement = this.auxiliaryStack.pop();
                this.mainStack.push(currentElement);
            }
        }
    }

    /**
     * O(1)
     * Justification: Only poll method is performed, which means returning and removing the top of the main stack.
     * @return an int
     */
    public int pop(){
        int element = this.mainStack.pop();
        return element;
    }

    /**
     * O(1)
     * Justification: Only poll method is performed, which means returning the top of the main stack.
     * @return an int
     */
    public int peek(){
        int element = this.mainStack.peek();
        return element;
    }

    /**
     * O(1)
     * Justification: Only main stack is checked if it contains any elements or not.
     * @return a boolean value
     */
    public boolean empty(){
        return this.mainStack.isEmpty();
    }

}
