package location;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import pair.Pair;
import pair.PairImpl;
import edge.Edge;
import otyugh.Otyugh;
import otyugh.OtyughImpl;

/**
 * The class implements Location interface.
 */
public class LocationImpl implements Location {

  private final Pair<Integer, Integer> coordinate;
  private final int index;
  private boolean isEnd;
  private boolean isStart;
  private LocationType type;
  private List<TreasureType> treasures;
  private Edge northEdge;
  private Edge southEdge;
  private Edge eastEdge;
  private Edge westEdge;

  private Otyugh otyughs;

  /**
   * Constructs a Location with given index and coordinate.
   * 
   * @param index      the index of the location
   * @param coordinate the coordinate of the location
   * @throws IllegalArgumentException when index < 0 or coordinate is null
   */
  public LocationImpl(int index, Pair<Integer, Integer> coordinate)
      throws IllegalArgumentException {

    if (Objects.isNull(coordinate)) {
      throw new IllegalArgumentException("Arguments cannot be null!");
    }
    if (index < 0) {
      throw new IllegalArgumentException("Index cannot be negative!");
    }

    this.index = index;
    this.coordinate = coordinate;
    this.type = LocationType.DEFAULT;
    this.isEnd = false;
    this.isStart = false;
    this.treasures = new ArrayList<>();
    this.eastEdge = null;
    this.southEdge = null;
    this.eastEdge = null;
    this.westEdge = null;

    this.otyughs = null;

  }

  @Override
  public LocationType getType() {
    return this.type;
  }

  @Override
  public List<TreasureType> getTreasures() {
    List<TreasureType> result = new ArrayList<>();
    result.addAll(this.treasures);
    return result;
  }

  @Override
  public void addTreasures(TreasureType ts) throws IllegalArgumentException {
    if (Objects.isNull(ts)) {
      throw new IllegalArgumentException("Type cannot be null!");
    }
    this.treasures.add(ts);
  }

  @Override
  public void removeTreasures() {
    this.treasures = new ArrayList<>();
  }

  @Override
  public Pair<Integer, Integer> getCoordinate() {
    Pair<Integer, Integer> copy = new PairImpl<>(this.coordinate.getFirst(),
        this.coordinate.getSecond());

    return copy;
  }

  @Override
  public int getIndex() {

    return this.index;
  }

  @Override
  public void setType(LocationType type) throws IllegalArgumentException {
    if (Objects.isNull(type)) {
      throw new IllegalArgumentException("Type cannot be null!");
    }
    this.type = type;

  }

  @Override
  public List<Location> getAllNeighbors() {

    List<Location> locations = new ArrayList<>();
    if (this.northEdge != null) {
      locations.add(this.northEdge.getEnd());
    }
    if (this.southEdge != null) {
      locations.add(this.southEdge.getEnd());
    }
    if (this.eastEdge != null) {
      locations.add(this.eastEdge.getEnd());
    }
    if (this.westEdge != null) {
      locations.add(this.westEdge.getEnd());
    }
    return locations;
  }

  @Override
  public boolean isEnd() {
    return this.isEnd;
  }

  @Override
  public boolean isStart() {

    return this.isStart;
  }

  @Override
  public void setEnd() {
    this.isEnd = true;

  }

  @Override
  public void setStart() {
    this.isStart = true;

  }

  @Override
  public Otyugh getOtyugh() {

    return this.otyughs;

  }

  @Override
  public void addOtyugh() {
    Otyugh o = new OtyughImpl();
    this.otyughs = o;
  }

  @Override
  public void shootOtyugh() {
    if (this.otyughs == null) {
      return;
    }
    this.otyughs.reduceHealth();

    if (this.otyughs.getHealth() == 0) {
      this.otyughs = null;

    }

  }

  @Override
  public double probalilityToEscape() {
    if (this.otyughs == null) {
      return 1;
    }

    return this.otyughs.getProbalilityToEscape();
  }

  @Override
  public void removeOtyugh() {
    this.otyughs = null;

  }

  @Override
  public Location getLocation(Direction dir) {
    if (dir == Direction.NORTH && this.northEdge != null) {
      return this.northEdge.getEnd();
    }
    if (dir == Direction.SOUTH && this.southEdge != null) {
      return this.southEdge.getEnd();

    }
    if (dir == Direction.EAST && this.eastEdge != null) {
      return this.eastEdge.getEnd();

    }
    if (dir == Direction.WEST && this.westEdge != null) {
      return this.westEdge.getEnd();

    }

    return null;
  }

  @Override
  public void setEdge(Direction dir, Edge e) {
    if (Objects.isNull(e)) {
      throw new IllegalArgumentException("Edge cannot be null!");
    }
    if (dir == Direction.WEST) {
      this.westEdge = e;
    } else if (dir == Direction.EAST) {
      this.eastEdge = e;
    } else if (dir == Direction.NORTH) {
      this.northEdge = e;

    } else if (dir == Direction.SOUTH) {
      this.southEdge = e;
    }

  }

}
