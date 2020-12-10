package MyUtils;


import java.util.Arrays;

public class MyArrayList<T> {
    private T[] array;
    private int size;

    public MyArrayList(T[] elements) {
        array = elements;
        size = elements.length;
    }

    public MyArrayList() {
        array = (T[]) new Object[0];
        size = 0;
    }

    public void ensureCapacity(int minCapacity) {
        if(minCapacity > array.length) {
            grow(minCapacity);
        }
    }
    private void grow() {
        grow(size + 1);
    }

    private void grow(int minCapacity) {
        T[] newArray = (T[]) new Object[minCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public T get(int index) {
        return array[index];
    }

    public int size() {
        return size;
    }

    public boolean add(T element) {
        if(size == array.length)
            grow();
        array[size] = element;
        size++;
        return true;
    }

    public void remove(int index) {
        final int newSize = size - 1;
        if(newSize > index)
            System.arraycopy(array, index + 1, array, index, newSize - index);
        array[size = newSize] = null;
    }

    public T[] toArray(T[] newArray) {
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    public T[] toArray() {
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray()) +
                ", size=" + size;
    }
}
