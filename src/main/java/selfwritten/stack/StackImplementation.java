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
    private int topIndex = -1;

    public StackImplementation() {
        this.arr = new Character[initialSize];
    }

    public void push(Character c) {
        if(topIndex >= arr.length) {
            arr = Arrays.copyOf(arr, arr.length + initialSize);
        }
        arr[++topIndex] = c;
    }

    public Character peek() {
        if (topIndex == -1) {
            return null;
        }
        return arr[topIndex];
    }

    public Character pop() {
        if (topIndex == -1) {
            return null;
        }
        var topElement = arr[topIndex];
        topIndex--;
        return topElement;
    }
}
