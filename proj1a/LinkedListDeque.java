public class LinkedListDeque<T> {

    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;


        public StuffNode(StuffNode p, T i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private StuffNode sentinel;

    private int size;

    /* Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        StuffNode first = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size = size + 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        StuffNode last = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size = size + 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
    separated by a space. */
    public void printDeque() {
        StuffNode ptr = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
    }

    /** Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size = size - 1;
            return first;
        }
    }

    /** Removes and returns the item at the back of the deque.
     If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size = size - 1;
            return last;
        }
    }

    /** Gets the item at the given index.
    If no such item exists, returns null. */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            StuffNode p = sentinel;
            for (int i = index; i > 0; i--) {
                p = p.next;
            }
            return p.next.item;
        }
    }

    /* Same as get, but uses recursion. */
    private T getRecursiveHelper(StuffNode node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(node.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        } else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }
}
