package pair;

/**
 * Pair interface represents a pair of object.
 */
public interface Pair<T, U> {
  /**
   * Get the first object.
   * 
   * @return first object
   */
  T getFirst();

  /**
   * Get the second object.
   * 
   * @return second object
   */
  U getSecond();

}
