import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int REPETITIONS = 2;
    private static final int LIST_START = 0;
    private static final int LIST_END = 3;
    private CircularList list;

    @BeforeEach
    void createCircularList() {
        list = new CircularListImpl();
    }

    @Test
    void testInitiallyEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testAddIncreasesSize() {
        list.add(1);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testNextOnEmptyList() {
        assertFalse(list.next().isPresent());
    }

    @Test
    void testNext() {
        populateList();
        List<Integer> expected = IntStream.range(LIST_START, REPETITIONS * LIST_END)
                .mapToObj(i -> i % LIST_END)
                .collect(Collectors.toList());
        testIteratorOnList(expected, () -> list.next());
    }

    @Test
    void testPreviousOnEmptyList() {
        assertFalse(list.previous().isPresent());
    }

    @Test
    void testPrevious() {
        populateList();
        List<Integer> expected = IntStream.range(LIST_START, REPETITIONS * LIST_END)
                .map(i -> i % LIST_END)
                .mapToObj(i -> (-i + LIST_END) % LIST_END)
                .collect(Collectors.toList());
        testIteratorOnList(expected,() -> list.previous());
    }

    private void testIteratorOnList(List<Integer> expected, Supplier<Optional<Integer>> listGetter) {
        for (int i : expected) {
            Optional<Integer> elem = listGetter.get();
            assertTrue(elem.isPresent());
            assertEquals(i, elem.get());
        }
    }

    private void populateList() {
        IntStream.range(LIST_START, LIST_END).forEach(list::add);
    }
}
