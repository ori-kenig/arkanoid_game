// Ori Kenigsbuch 206594590

package GameElement;

/**
 * The Counter class represents a simple counter that can be incremented and queried for its current value.
 * The counter is initialized with a given value.
 */
public class Counter {
    private int value;
    /**
     * Constructs a new Counter with the specified initial value.
     *
     * @param value The initial value of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Adds a specified number to the current count of the counter.
     *
     * @param number The number to add to the current count.
     *
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Adds a subtract number from the current count of the counter.
     *
     * @param number The number to subtract from the current count.
     *
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Gets the current value of the counter.
     *
     * @return The current value of the counter.
     */
    int getValue() {
        return this.value;
    }
}
