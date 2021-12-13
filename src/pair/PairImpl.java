package pair;

import java.util.Objects;

/**
 * The class implements Pair interface.
 */
public class PairImpl<T, U> implements Pair<T, U> {

  public final T t;
  public final U u;

  /**
   * Constructs a Pair with given objects.
   * 
   * @param t the first object
   * @param u the second object
   * @throws IllegalArgumentException when arguments are null
   */
  public PairImpl(T t, U u) throws IllegalArgumentException {
    if (Objects.isNull(t) || Objects.isNull(u)) {
      throw new IllegalArgumentException("Arguments cannot be null!");
    }
    this.t = t;
    this.u = u;
  }

  @Override
  public T getFirst() {

    return this.t;
  }

  @Override
  public U getSecond() {

    return this.u;
  }

}
