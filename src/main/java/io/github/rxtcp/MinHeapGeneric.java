package io.github.rxtcp;

import java.util.*;
import java.util.stream.Collectors;

public class MinHeapGeneric<T extends Comparable<? super T>> {

    private final List<T> elements;

    public MinHeapGeneric() {
        elements = new ArrayList<>();
    }

    public MinHeapGeneric(T[] inputArr) {
        if (inputArr == null) {
            throw new IllegalArgumentException("Input array is null!");
        }
        elements = Arrays.stream(inputArr)
                .collect(Collectors.toList());
        buildHeap();
    }

    public static <T extends Comparable<? super T>> List<T> heapSort(T[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input array is NULL!");
        }
        if (input.length == 0) {
            return new ArrayList<>();
        }
        if (input.length == 1) {
            return new ArrayList<>(Arrays.asList(input));
        }
        MinHeapGeneric<T> heap = new MinHeapGeneric<>(input);
        List<T> list = new ArrayList<>(input.length);
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }
        return list;
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

    public void add(T newElement) {
        elements.add(newElement);
        siftUp(elements.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        return elements.getFirst();
    }

    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        T minElement = elements.getFirst();
        elements.set(0, elements.getLast());
        elements.removeLast();
        siftDown(0);
        return minElement;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    private void buildHeap() {
        for (int i = elements.size() / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int childIdx) {
        while (childIdx > 0) {

            int parentIdx = parentIndex(childIdx);

            T parent = elements.get(parentIdx);
            T child = elements.get(childIdx);

            if (parent.compareTo(child) > 0) {
                swap(childIdx, parentIdx);
                childIdx = parentIdx;
            } else {
                return;
            }
        }
    }

    private void siftDown(int parentIdx) {
        while (leftChildIndex(parentIdx) < elements.size()) {

            int leftIdx = leftChildIndex(parentIdx);
            int rightIdx = rightChildIndex(parentIdx);

            T parentElement = elements.get(parentIdx);
            T leftElement = elements.get(leftIdx);

            if (rightIdx < elements.size()
                    && leftElement.compareTo(elements.get(rightIdx)) > 0
                    && parentElement.compareTo(elements.get(rightIdx)) > 0) {
                swap(parentIdx, rightIdx);
                parentIdx = rightIdx;
            } else if (parentElement.compareTo(leftElement) > 0) {
                swap(parentIdx, leftIdx);
                parentIdx = leftIdx;
            } else {
                return;
            }
        }
    }

    private void swap(int i, int j) {
        T iElement = elements.get(i);
        T jElement = elements.get(j);

        elements.set(i, jElement);
        elements.set(j, iElement);
    }
}
