import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;

import org.junit.jupiter.api.*;

import java.util.Optional;
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
        for (int r = 0; r < REPETITIONS; r++) {
            for (int i = LIST_START; i < LIST_END; i++) {
                Optional<Integer> next = list.next();
                assertTrue(next.isPresent());
                assertEquals(i, next.get());
            }
        }
    }

    private void populateList() {
        IntStream.range(LIST_START, LIST_END).forEach(list::add);
    }
}
