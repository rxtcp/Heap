package io.github.rxtcp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapGenericTest {

    @Test
    void heapSort_sortsArrayInAscendingOrder() {
        // given
        Integer[] input = {3, 1, 4, 2};

        // when
        List<Integer> sortedArr = MinHeapGeneric.heapSort(input);

        // then
        assertIterableEquals(List.of(1, 2, 3, 4), sortedArr);
    }

    @Test
    void addAndPoll_returnElementsFromSmallestToLargest() {
        // given
        MinHeapGeneric<Integer> heap = new MinHeapGeneric<>();

        // when
        heap.add(3);
        heap.add(1);
        heap.add(4);
        heap.add(2);

        // then
        assertEquals(1, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(4, heap.poll());
        assertTrue(heap.isEmpty());
    }

    @Test
    void peek_onEmptyHeap_throwsException() {
        // given
        MinHeapGeneric<Integer> heap = new MinHeapGeneric<>();

        // when + then
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void poll_onEmptyHeap_throwsException() {
        // given
        MinHeapGeneric<Integer> heap = new MinHeapGeneric<>();

        // when + then
        assertThrows(NoSuchElementException.class, heap::poll);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, -3, 10})
    void addOneElement_peekReturnSame(int value) {
        // given
        MinHeapGeneric<Integer> heap = new MinHeapGeneric<>();

        // when
        heap.add(value);

        // then
        assertEquals(value, heap.peek());
    }

    @ParameterizedTest
    @MethodSource("arraysForHeapSort")
    void heapSort_sortsArraysCorrectly(Integer[] input, List<Integer> excepted) {
        // when
        List<Integer> sortedArr = MinHeapGeneric.heapSort(input);

        // then
        assertIterableEquals(excepted, sortedArr);
    }

    static Stream<Arguments> arraysForHeapSort() {
        return Stream.of(
                Arguments.of(new Integer[]{3, 1, 4, 2}, new ArrayList<>(List.of(1, 2, 3, 4))),
                Arguments.of(new Integer[]{}, new ArrayList<Integer>()),
                Arguments.of(new Integer[]{5}, new ArrayList<>(List.of(5)))
        );
    }

}