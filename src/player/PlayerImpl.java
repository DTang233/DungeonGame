package player;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import location.Direction;
import location.Location;
import location.LocationType;
import location.TreasureType;

/**
 * The class implements Player interface.
 */
public class PlayerImpl implements Player {

  private Location location;
  private List<TreasureType> treasures;
  private int arrowsNum;
  private int health;

  /**
   * Constructs a Player with given start location.
   * 
   * @param startLocation start location of the game
   * @throws IllegalArgumentException when argument is null
   */
  public PlayerImpl(Location startLocation) throws IllegalArgumentException {

    if (Objects.isNull(startLocation)) {
      throw new IllegalArgumentException("Arguments cannot be null!");
    }

    this.location = startLocation;
    this.treasures = new ArrayList<>();
    this.arrowsNum = 3;
    this.health = 2;
  }

  @Override
  public void pickTreasure() {

    List<TreasureType> ts = this.location.getTreasures();

    for (TreasureType type : ts) {
      if (type == TreasureType.ARROW) {
        this.arrowsNum += 1;
      }
      this.treasures.add(type);
    }
    this.location.removeTreasures();

  }

  @Override
  public String getDescription() {
    StringBuilder sb = new StringBuilder();

    sb.append("You are in a " + this.location.getType().toString() + "<br/>");
    sb.append("My arrow number: <br/>" + this.arrowsNum + "<br/>");
    sb.append("My treasures: ");
    sb.append("<br/>");
    for (TreasureType t : this.treasures) {
      if (t != TreasureType.ARROW) {
        sb.append(t.toString() + " ");
      }

    }
    sb.append("<br/>");

    sb.append("Treasures in the location: ");
    sb.append("<br/>");
    for (TreasureType t : this.location.getTreasures()) {
      sb.append(t.toString() + " ");
    }
    sb.append("<br/>");

    sb.append("Possible moves: ");
    sb.append("<br/>");
    if (this.location.getLocation(Direction.NORTH) != null) {
      sb.append("North ");
    }
    if (this.location.getLocation(Direction.SOUTH) != null) {
      sb.append("South ");
    }
    if (this.location.getLocation(Direction.EAST) != null) {
      sb.append("East ");
    }
    if (this.location.getLocation(Direction.WEST) != null) {
      sb.append("West ");
    }

    return sb.toString();
  }

  @Override
  public Location getLocation() {

    return this.location;
  }

  @Override
  public boolean isGameOver() {

    return this.location.isEnd();
  }

  @Override
  public boolean shoot(Direction dir, int distance) {
    if (Objects.isNull(dir) || distance < 0) {
      throw new IllegalArgumentException("Arguments cannot be null, distance cannot be negative!");
    }
    if (this.arrowsNum == 0) {
      throw new IllegalStateException("No arrows!");
    }
    this.arrowsNum -= 1;

    Location loc = this.location;
    Direction d = dir;
    int dist = distance;
    while (dist > 0) {

      if (loc.getType() == LocationType.CAVE) {
        dist -= 1;
      }
      if (loc.getOtyugh() != null && dist == 0) {
        loc.shootOtyugh();
        return true;
      }
      Location nextPos = getNextShootPosition(loc, dir);
      if (nextPos == null) {
        System.out.println("test");
        return false;
      }
      if (nextPos.getType() == LocationType.TUNNEL) {
        d = getNextTunnleShootPosition(nextPos, d);
      }

      loc = nextPos;
    }

    return false;

  }

  private Direction getNextTunnleShootPosition(Location loc, Direction in) {

    if (in != Direction.EAST && loc.getLocation(Direction.WEST) != null) {
      return Direction.WEST;

    } else if (in != Direction.WEST && loc.getLocation(Direction.EAST) != null) {
      return Direction.EAST;
    } else if (in != Direction.NORTH && loc.getLocation(Direction.SOUTH) != null) {
      return Direction.SOUTH;

    } else {
      return Direction.NORTH;
    }

  }

  private Location getNextShootPosition(Location loc, Direction dir) {
    if (dir == Direction.EAST) {
      return loc.getLocation(Direction.EAST);

    } else if (dir == Direction.WEST) {
      return loc.getLocation(Direction.WEST);

    } else if (dir == Direction.NORTH) {
      return loc.getLocation(Direction.NORTH);

    } else {
      return loc.getLocation(Direction.SOUTH);
    }

  }

  @Override
  public int detectOtyugh() {

    List<Location> distOnePositions = new ArrayList<>();
    int numOfOtyugh = 0;
    for (Location n : this.location.getAllNeighbors()) {
      numOfOtyugh += n.getOtyugh() == null ? 0 : 1;
      distOnePositions.add(n);
    }
    // More pungent smell
    if (numOfOtyugh >= 1) {
      return 2;
    }
    numOfOtyugh = 0;
    for (Location loc : distOnePositions) {
      for (Location n : loc.getAllNeighbors()) {
        numOfOtyugh += n.getOtyugh() == null ? 0 : 1;
      }
    }
    // More pungent smell
    if (numOfOtyugh > 1) {
      return 2;
    }
    // Less pungent smell
    if (numOfOtyugh == 1) {
      return 1;
    }

    return 0;
  }

  @Override
  public int getHealth() {

    return this.health;
  }

  @Override
  public void reduceHealth() {
    if (this.health > 0) {
      this.health -= 1;
    }

  }

  @Override
  public void move(Direction dir) {
    Location loc = this.location.getLocation(dir);
    if (this.location.isEnd()) {

      throw new IllegalStateException("Already at the end location!");
    }
    if (loc == null) {
      throw new IllegalStateException("Cannot go " + dir.toString() + "!");
    }
    this.location = loc;

  }

}
