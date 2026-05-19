package selfwritten.stack;

import java.util.Arrays;

/**
 * Implement a Stack (LIFO) data structure using an Array.
 * The operations to implement:
 *  - push - insert an element into the stack
 *  - pop - remove and return the top element from the stack
 *  - peek - return the top element from the stack without removing it
 */
public class StackImplementation {
    private Character[] arr;
    private final int initialSize = 10;
    private int topElementIndex = -1;

    public StackImplementation() {
        this.arr = new Character[initialSize];
    }

    public void push(char c) {
        if (topElementIndex >= arr.length - 1){
            arr = Arrays.copyOf(arr, arr.length + initialSize);
        }
        arr[++topElementIndex] = c;
    }

    public Character peek() {
        if(topElementIndex==-1) {
            return null;
        }
        return arr[topElementIndex];
    }

    public Character pop() {
        if(topElementIndex==-1) {
            return null;
        }
        var topElement = arr[topElementIndex];
        arr[topElementIndex] = null;
        topElementIndex--;
        return topElement;
    }
}
