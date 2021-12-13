import location.Direction;
import location.Location;
import player.Player;

/**
 * The class that implements Player, for testing purpose.
 */
public class MockPlayer implements Player {
  private StringBuilder log;

  /**
   * Construct a MockPlayer with given StringBuilder.
   *
   * @param log the log to output the result
   */
  public MockPlayer(StringBuilder log) {
    this.log = log;

  }

  @Override
  public void pickTreasure() {
    log.append("Picked treasures.");

  }


  @Override
  public String getDescription() {
    log.append("Get desciption.");
    return null;
  }

  @Override
  public Location getLocation() {
    log.append("Get location.");
    return null;
  }

  @Override
  public boolean isGameOver() {
    log.append("Game over.");
    return false;
  }

  @Override
  public boolean shoot(Direction dir, int distance) {
    log.append("Shoot " + dir.toString() + " " + distance);
    return false;
  }

  @Override
  public int detectOtyugh() {
    log.append("Detect otyugh.");
    return 0;
  }

  @Override
  public int getHealth() {
    log.append("Get health.");
    return 0;
  }

  @Override
  public void reduceHealth() {
    log.append("Get health.");

  }

  @Override
  public void move(Direction dir) {
    log.append("Move " + dir.toString());
    throw new IllegalStateException("Move west exception.");
  }

}
