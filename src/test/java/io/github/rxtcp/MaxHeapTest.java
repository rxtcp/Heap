package io.github.rxtcp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    @Test
    void heapSort_sortsArrayInAscendingOrder() {
        // given
        int[] input = {3, 1, 4, 2};

        // when
        MaxHeap.heapSort(input);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4}, input);
    }

    @Test
    void addAndPoll_returnElementsFromLargestToSmallest() {
        // given
        MaxHeap heap = new MaxHeap();

        // when
        heap.add(3);
        heap.add(1);
        heap.add(4);
        heap.add(2);

        // then
        assertEquals(4, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(1, heap.poll());
        assertTrue(heap.isEmpty());
    }

    @Test
    void peek_onEmptyHeap_throwsException() {
        // given
        MaxHeap heap = new MaxHeap();

        // when + then
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void poll_onEmptyHeap_throwsException() {
        // given
        MaxHeap heap = new MaxHeap();

        // when + then
        assertThrows(NoSuchElementException.class, heap::poll);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, -3, 10})
    void addOneElement_peekReturnSame(int value) {
        // given
        MaxHeap heap = new MaxHeap();

        // when
        heap.add(value);

        // then
        assertEquals(value, heap.peek());
    }

    @ParameterizedTest
    @MethodSource("arraysForHeapSort")
    void createMaxHeap_sortsArraysCorrectly(int[] input, int[] excepted) {
        // when
        MaxHeap heap = new MaxHeap(input);
        int[] sortedArr = new int[heap.size()];
        for (int i = sortedArr.length - 1; i >= 0; i--) {
            sortedArr[i] = heap.poll();
        }

        // then
        assertArrayEquals(excepted, sortedArr);
        assertEquals(0, heap.size());
        assertTrue(heap::isEmpty);
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @ParameterizedTest
    @MethodSource("arraysForHeapSort")
    void heapSort_sortsArraysCorrectly(int[] input, int[] excepted) {
        // when
        MaxHeap.heapSort(input);

        // then
        assertArrayEquals(excepted, input);
    }

    static Stream<Arguments> arraysForHeapSort() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 4, 2}, new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{}, new int[]{}),
                Arguments.of(new int[]{5}, new int[]{5})
        );
    }

}
