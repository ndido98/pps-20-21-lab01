package lab01.tdd;

public class SelectStrategyFactoryImpl implements SelectStrategyFactory {
    @Override
    public SelectStrategy evenStrategy() {
        return multipleOfStrategy(2);
    }

    @Override
    public SelectStrategy multipleOfStrategy(int num) {
        return i -> i % num == 0;
    }

    @Override
    public SelectStrategy equalsStrategy(int num) {
        return i -> i == num;
    }
}
