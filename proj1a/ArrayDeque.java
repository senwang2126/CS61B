public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int capacity;
    private int prev;
    private int next;
    private boolean isFull;

    public ArrayDeque() {
        capacity = 2;
        items = (T[]) new Object[capacity];
        size = 0;
        prev = 1;
        next = 0;
        isFull = false;
    }

    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        int i = 0;
        while ((prev % cap) != next) {
            temp[i] = items[(prev + 1) % capacity];
            prev++;
            i++;
        }
        isFull = false;
        prev = cap - 1;
        next = size;
        items = temp;
        capacity = cap;
    }

    public void addFirst(T i) {
        if (isFull) {
            resize(capacity * 2);
        }
        if (prev == next) {
            isFull = true;
        }
        size++;
        items[prev] = i;
        prev--;
    }

    public void addLast(T i) {
        if (isFull) {
            resize(capacity * 2);
        }
        if (prev == next) {
            isFull = false;
        }
        size++;
        items[next] = i;
        next = (next + 1) % capacity;
    }

    public T removeFirst() {
        size--;
        prev++;
        T first = items[prev];
        items[prev] = null;
        if (size / capacity < 0.2) {
            resize(capacity / 4 + 2);
        }
        return first;
    }

    public T removeLast() {
        size--;
        next--;
        T last = items[next];
        items[next] = null;
        if (size / capacity < 0.2) {
            resize(capacity / 4 + 2);
        }
        return last;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T get(int index) {
        return items[(prev + index) % capacity];
    }
}
