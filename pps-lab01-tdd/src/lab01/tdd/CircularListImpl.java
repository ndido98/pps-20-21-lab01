package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> list;
    private int currentIndex;

    public CircularListImpl() {
        list = new ArrayList<>();
        currentIndex = 0;
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        return next(i -> true);
    }

    @Override
    public Optional<Integer> previous() {
        if (isEmpty()) {
            return Optional.empty();
        }
        int elem = list.get(currentIndex);
        currentIndex = getPreviousIndex();
        return Optional.of(elem);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    @Override
    public Optional<Integer> next(final SelectStrategy strategy) {
        if (isEmpty()) {
            return Optional.empty();
        }
        final int startingIndex = currentIndex;
        int elem;
        do {
            elem = list.get(currentIndex);
            currentIndex = getNextIndex();
            if (strategy.apply(elem)) {
                return Optional.of(elem);
            }
        } while (currentIndex != startingIndex);
        return Optional.empty();
    }

    private int getNextIndex() {
        return (currentIndex + 1) % size();
    }

    private int getPreviousIndex() {
        return (currentIndex - 1 + size()) % size();
    }
}
