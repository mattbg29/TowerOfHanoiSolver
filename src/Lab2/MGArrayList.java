/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  My version of an ArrayList, which includes the method removeLast
 * that functions similar to a stack's pop method, removing and outputting
 * the final element in the array in O(1) time complexity.
 **************************************************************************** */

package Lab2;

public class MGArrayList<E> {
    private static final int STARTING_SIZE = 10;
    private int maxSize = STARTING_SIZE;
    private int size = 0;
    @SuppressWarnings("unchecked")
    private E[] array = (E[]) new Object[STARTING_SIZE];

    // Adds an item to the ArrayList
    void add(E item) {
        checkFull();
        array[size++] = item;
    }

    // Removes the last item from the ArrayList
    E removeLast() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        checkMostlyEmpty();
        return array[--size];
    }

    // Returns the size of the ArrayList
    int size() {
        return size;
    }

    // Returns the item in the ArrayList at the given index
    E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return array[index];
    }

    // Checks if the ArrayList is full
    private void checkFull() {
        if (maxSize == size) {
            maxSize *= 2;
            reallocate("double");
        }
    }

    // Checks if the ArrayList is less than 25% full
    private void checkMostlyEmpty() {
        if (size <= (maxSize / 4) && maxSize > STARTING_SIZE) {
            maxSize /= 2;
            reallocate("halve");
        }
    }

    // Checks if the ArrayList is empty
    boolean isEmpty() {
        return size == 0;
    }

    // Doubles or halves the array size if it becomes full, or if after doubling at least once,
    // it becomes less than a quarter full
    private void reallocate(String input) {
        @SuppressWarnings("unchecked")
        E[] arrayNew = (E[]) new Object[maxSize];
        switch (input) {
            case "double":
                for (int i = 0; i < size; i++) {
                    arrayNew[i] = array[i];
                }
                break;
            case "halve":
                for (int i = 0; i < maxSize; i++) {
                    arrayNew[i] = array[i];
                }
                break;
            default:
                throw new IllegalStateException("Unexpected reallocation case: " + input);
        }
        array = arrayNew;
    }
}
