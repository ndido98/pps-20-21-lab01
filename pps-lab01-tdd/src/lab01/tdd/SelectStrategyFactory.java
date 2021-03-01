package lab01.tdd;

public interface SelectStrategyFactory {

    /**
     * Get the next even element.
     * @return the strategy that gets the next even element
     */
    SelectStrategy evenStrategy();

    /**
     * Get the next element that is a multiple of the given number.
     * @param num the number whose multiples will be selected
     * @return the strategy that gets the next element that is a multiple of the given number
     */
    SelectStrategy multipleOfStrategy(final int num);

    /**
     * Get the next element that equals the given number.
     * @param num the number whose element will be equal to
     * @return the strategy that gets the next element that equals the given number
     */
    SelectStrategy equalsStrategy(final int num);
}
