package io.github.rxtcp;

import java.util.NoSuchElementException;

public class MaxHeap {

    private int[] array;
    private int size;

    public MaxHeap() {
        array = new int[10];
        size = 0;
    }

    public MaxHeap(int[] inputArray) {
        if (inputArray == null) {
            throw new IllegalArgumentException("Input array is null!");
        }
        size = inputArray.length;
        array = new int[size];
        System.arraycopy(inputArray, 0, array, 0, size);
        buildHeap(array, size);
    }

    private static int parentIndex(int childIdx) {
        return (childIdx - 1) / 2;
    }

    private static int leftChildIndex(int parentIdx) {
        return parentIdx * 2 + 1;
    }

    private static int rightChildIndex(int parentIdx) {
        return parentIdx * 2 + 2;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void siftUp(int[] array, int childIdx) {
        while (childIdx > 0) {

            int parentIdx = parentIndex(childIdx);

            int parent = array[parentIdx];
            int child = array[childIdx];

            if (child > parent) {
                swap(array, parentIdx, childIdx);
                childIdx = parentIdx;
            } else {
                return;
            }
        }
    }

    private static void siftDown(int[] array, int size, int parentIndex) {
        while (leftChildIndex(parentIndex) < size) {

            int leftIdx = leftChildIndex(parentIndex);
            int rightIdx = rightChildIndex(parentIndex);

            if (rightIdx < size
                    && array[leftIdx] < array[rightIdx]
                    && array[parentIndex] < array[rightIdx]) {
                swap(array, parentIndex, rightIdx);
                parentIndex = rightIdx;
            } else if (array[parentIndex] < array[leftIdx]) {
                swap(array, parentIndex, leftIdx);
                parentIndex = leftIdx;
            } else {
                return;
            }
        }
    }

    private static void buildHeap(int[] array, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(array, size, i);
        }
    }

    public static void heapSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array is null!");
        }
        if (array.length == 0) {
            return;
        }
        buildHeap(array, array.length);
        for (int heapSize = array.length; heapSize > 1; heapSize--) {
            swap(array, 0, heapSize - 1);
            siftDown(array, heapSize - 1, 0);
        }
    }

    public void add(int element) {
        if (array.length == size) {
            ensureCapacity();
        }
        array[size] = element;
        siftUp(array, size);
        size++;
    }

    public int poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        int maxElement = array[0];
        array[0] = array[size - 1];
        size--;
        siftDown(array, size, 0);
        return maxElement;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        return array[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}