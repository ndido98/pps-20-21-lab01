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
            currentIndex = (currentIndex + size()) % size();
            int elem = list.get(currentIndex);
            currentIndex--;
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
        int elem = 0;
        do {
            currentIndex = currentIndex % size();
            elem = list.get(currentIndex);
            currentIndex++;
        } while (!strategy.apply(elem));
        return Optional.of(elem);
    }
}
