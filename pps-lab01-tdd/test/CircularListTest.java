import lab01.tdd.*;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int REPETITIONS = 2;
    private static final int LIST_START = 0;
    private static final int LIST_END = 10;
    private CircularList list;
    private SelectStrategyFactory selectStrategyFactory;

    @BeforeEach
    void createCircularList() {
        list = new CircularListImpl();
        selectStrategyFactory = new SelectStrategyFactoryImpl();
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
        var expected = IntStream.range(LIST_START, REPETITIONS * LIST_END)
                .map(i -> i % LIST_END);
        testIteratorOnList(expected, () -> list.next());
    }

    @Test
    void testNextAndAdd() {
        list.add(0);
        assertEquals(0, list.next().get());
        list.add(1);
        assertEquals(0, list.next().get());
        list.add(2);
        assertEquals(1, list.next().get());
        assertEquals(2, list.next().get());
    }

    @Test
    void testPreviousOnEmptyList() {
        assertFalse(list.previous().isPresent());
    }

    @Test
    void testPrevious() {
        populateList();
        var expected = IntStream.range(LIST_START, REPETITIONS * LIST_END)
                .map(i -> i % LIST_END)
                .map(i -> (-i + LIST_END) % LIST_END);
        testIteratorOnList(expected, () -> list.previous());
    }

    @Test
    void testPreviousAndAdd() {
        list.add(0);
        assertEquals(0, list.previous().get());
        list.add(1);
        assertEquals(0, list.previous().get());
        list.add(2);
        assertEquals(1, list.previous().get());
        assertEquals(0, list.previous().get());
        assertEquals(2, list.previous().get());
    }

    @Test
    void testNextAndPrevious() {
        populateList();
        assertEquals(0, list.next().get());
        assertEquals(1, list.previous().get());
        assertEquals(0, list.previous().get());
        assertEquals(9, list.next().get());
    }

    @Test
    void testReset() {
        populateList();
        assertEquals(LIST_START, list.next().get());
        list.reset();
        assertEquals(LIST_START, list.next().get());
    }

    @Test
    void testNextWithStrategy() {
        testStrategy(i -> i % 2 == 0);
    }

    @Test
    void testNextWithStrategyNotFound() {
        populateList();
        assertFalse(list.next(i -> false).isPresent());
    }

    @Test
    void testNextWithEvenStrategyFactory() {
        testStrategy(selectStrategyFactory.evenStrategy());
    }

    @Test
    void testNextWithMultipleOfStrategyFactory() {
        testStrategy(selectStrategyFactory.multipleOfStrategy(2));
    }

    private void testStrategy(SelectStrategy strategy) {
        populateList();
        var expected = IntStream.range(LIST_START, REPETITIONS * LIST_END)
                .map(i -> i % LIST_END)
                .filter(i -> strategy.apply(i));
        testIteratorOnList(expected, () -> list.next(strategy));
    }

    private void testIteratorOnList(IntStream expected, Supplier<Optional<Integer>> listGetter) {
        testIteratorOnList(expected.mapToObj(i -> i), listGetter);
    }

    private void testIteratorOnList(Stream<Integer> expected, Supplier<Optional<Integer>> listGetter) {
        expected.forEach(i -> {
            Optional<Integer> elem = listGetter.get();
            assertTrue(elem.isPresent());
            assertEquals(i, elem.get());
        });
    }

    private void populateList() {
        IntStream.range(LIST_START, LIST_END).forEach(list::add);
    }
}
