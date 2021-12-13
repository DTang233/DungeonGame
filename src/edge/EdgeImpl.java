package edge;

import java.util.Objects;

import location.Location;

/**
 * The class implements Edge interface.
 */
public class EdgeImpl implements Edge {
  private Location start;
  private Location end;

  /**
   * Constructs an Edge with start location and end location.
   * 
   * @param start the start location
   * @param end   the end location
   * @throws IllegalArgumentException if either argument is null
   */
  public EdgeImpl(Location start, Location end) throws IllegalArgumentException {
    if (Objects.isNull(start) || Objects.isNull(end)) {
      throw new IllegalArgumentException("Start and end location cannot be null!");
    }

    this.start = start;
    this.end = end;

  }

  @Override
  public Location getStart() {

    return this.start;
  }

  @Override
  public Location getEnd() {
    return this.end;
  }

  @Override
  public void setStart(Location location) throws IllegalArgumentException {
    if (Objects.isNull(location)) {
      throw new IllegalArgumentException("Start location cannot be null!");
    }
    this.start = location;

  }

  @Override
  public void setEnd(Location location) throws IllegalArgumentException {
    if (Objects.isNull(location)) {
      throw new IllegalArgumentException("End location cannot be null!");
    }
    this.end = location;

  }

}
