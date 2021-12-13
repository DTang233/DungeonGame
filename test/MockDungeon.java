import dungeon.Dungeon;
import location.Location;

/**
 * The class that implements Dungeon, for testing purpose.
 */
public class MockDungeon implements Dungeon {

  private StringBuilder log;

  /**
   * Construct a MockDungeon with given StringBuilder.
   *
   * @param log the log to output the result
   */
  public MockDungeon(StringBuilder log) {
    this.log = log;
    log.append("Dungeon Initialzed.");

  }

  @Override
  public void addTreasure(int percentage) {

    log.append("Added treasure." + " " + percentage);

  }

  @Override
  public Location getStartLocation() {
    log.append("Get satrt location.");
    

    return null;
  }

  @Override
  public Location getEndLocation() {
    log.append("Get end location.");

    return null;
  }

  @Override
  public Location[][] getLocations() {
    log.append("Get locations.");
    return null;
  }

}
