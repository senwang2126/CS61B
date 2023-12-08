public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int capacity;
    private int prev;
    private int next;

    public ArrayDeque() {
        capacity = 2;
        items = (T[]) new Object[capacity];
        size = 0;
        prev = 1;
        next = 0;
    }

    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            temp[i] = this.get(i);
        }
        prev = cap - 1;
        next = size;
        items = temp;
        capacity = cap;
    }

    public void addFirst(T i) {
        if (isFull()) {
            resize(size * 2);
        }
        size++;
        items[prev] = i;
        prev = (prev - 1 + capacity) % capacity;
    }

    public void addLast(T i) {
        if (isFull()) {
            resize(size * 2);
        }
        size++;
        items[next] = i;
        next = (next + 1 + capacity) % capacity;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        prev = (prev + 1 + capacity) % capacity;
        T first = items[prev];
        items[prev] = null;
        if ((double) size / capacity < 0.2) {
            resize(size * 2);
        }
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        next = (next - 1 + capacity) % capacity;
        T last = items[next];
        items[next] = null;
        if ((double) size / capacity < 0.2) {
            resize(size * 2);
        }
        return last;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
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
        if (isEmpty() || index >= size || index < 0) {
            return null;
        }
        return items[(prev + 1 + index + capacity) % capacity];
    }
}
