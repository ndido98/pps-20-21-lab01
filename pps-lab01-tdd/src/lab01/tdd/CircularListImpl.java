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
        return list.size() == 0;
    }

    @Override
    public Optional<Integer> next() {
        return next(i -> true);
    }

    @Override
    public Optional<Integer> previous() {
        if (!isEmpty()) {
            int elem = list.get(currentIndex);
            currentIndex = (currentIndex - 1 + size()) % size();
            return Optional.of(elem);
        } else {
            return Optional.empty();
        }
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
        int startingIndex = currentIndex;
        int elem = 0;
        do {
            elem = list.get(currentIndex);
            currentIndex = (currentIndex + 1) % size();
            if (strategy.apply(elem)) {
                return Optional.of(elem);
            }
        } while (currentIndex != startingIndex);
        return Optional.empty();
    }
}
