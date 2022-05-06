package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-3b0db3e6676a4a19bb5a8fc5efc90286
 */
public class QueueStack {

    Queue<Integer> mainQueue;
    Queue<Integer> auxiliaryQueue;

    public QueueStack(){
        mainQueue = new LinkedList<>();
        auxiliaryQueue = new LinkedList<>();
    }

    /**
     * O(n)
     * Justification: transferring existing elements between main queue and auxiliary queue requires iterating through the queues
     * @param element
     */
    public void push(int element){
        if (this.mainQueue.isEmpty())
            this.mainQueue.add(element);
        else{
            while(!this.mainQueue.isEmpty()) {
                int auxElement = this.mainQueue.poll();
                this.auxiliaryQueue.add(auxElement);
            }
            this.mainQueue.add(element);
            while(!this.auxiliaryQueue.isEmpty()){
                int auxElement = this.auxiliaryQueue.poll();
                this.mainQueue.add(auxElement);
            }
        }
    }

    /**
     * O(1)
     * Justification: Only poll method is performed, which means returning and removing the head of the main queue.
     * @return
     */
    public int pop(){
        int element = this.mainQueue.poll();
        return element;
    }

    /**
     * O(1)
     * Justification: Only peek method is performed, which means returning the head of the main queue.
     * @return
     */
    public int top(){
        int element = this.mainQueue.peek();
        return element;
    }

    /**
     * O(1)
     * Justification: Only main queue is checked if it contains any elements or not.
     * @return
     */
    public boolean empty(){
        return this.mainQueue.isEmpty();
    }

}
