package io.github.rxtcp;

import java.util.NoSuchElementException;

public class MinHeap {

    private int[] arr = new int[10];
    private int size = 0;

    public MinHeap() {
    }

    public MinHeap(int[] inputArr) {
        size = inputArr.length;
        arr = new int[size];
        System.arraycopy(inputArr, 0, arr, 0, size);
        buildHeap();
    }

    private static int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private static int leftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private static int rightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    public static int[] heapSort(int[] inputArr) {
        if (inputArr == null) {
            throw new IllegalArgumentException("Input array is null!");
        }
        MinHeap heap = new MinHeap(inputArr);
        int[] sortedArr = new int[inputArr.length];
        for (int i = 0; i < sortedArr.length; i++) {
            sortedArr[i] = heap.poll();
        }
        return sortedArr;
    }

    public void add(int newElement) {
        if (arr.length == size) {
            ensureCapacity();
        }
        arr[size] = newElement;
        siftUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        return arr[0];
    }

    public int poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        int minElement = arr[0];
        arr[0] = arr[size - 1];
        size--;
        siftDown(0);
        return minElement;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int i) {
        while (i > 0) {
            if (arr[parentIndex(i)] > arr[i]) {
                int parent = arr[parentIndex(i)];
                arr[parentIndex(i)] = arr[i];
                arr[i] = parent;
                i = parentIndex(i);
            } else {
                return;
            }
        }
    }

    private void siftDown(int i) {
        while (leftChildIndex(i) < size) {
            if (rightChildIndex(i) < size
                    && arr[rightChildIndex(i)] < arr[leftChildIndex(i)]
                    && arr[i] > arr[rightChildIndex(i)]) {
                int parent = arr[i];
                arr[i] = arr[rightChildIndex(i)];
                arr[rightChildIndex(i)] = parent;
                i = rightChildIndex(i);
            } else if (arr[i] > arr[leftChildIndex(i)]) {
                int parent = arr[i];
                arr[i] = arr[leftChildIndex(i)];
                arr[leftChildIndex(i)] = parent;
                i = leftChildIndex(i);
            } else {
                return;
            }
        }
    }

    private void ensureCapacity() {
        int[] newArr = new int[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
}
