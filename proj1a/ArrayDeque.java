/** Array based list.
 *  @author Chenguang Dai
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = item;
        size = size + 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size = size + 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
      * separated by a space. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    /** Removes and returns the item at the front of the deque.
      * If no such T exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T first = items[0];
            System.arraycopy(items, 1, items, 0, size - 1);
            items[size - 1] = null;
            size = size - 1;
            float UsageRatio = (float) size / items.length;
            if (items.length >= 16 && UsageRatio < 0.25) {
                resize(items.length / 2);
            }
            return first;
        }
    }

    /** Removes and returns the item at the back of the deque.
      * If no such T exists, returns null.*/
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T last = items[size - 1];
            items[size - 1] = null;
            size = size - 1;
            float UsageRatio = (float) size / items.length;
            if (items.length >= 16 && UsageRatio < 0.25) {
                resize(items.length / 2);
            }
            return last;
        }
    }

    /** Gets the item at the given index.
      * If no such T exists, returns null. */
    public T get(int index) {
        if (index <0 || index > size - 1) {
            return null;
        } else {
            return items[index];
        }
    }
}