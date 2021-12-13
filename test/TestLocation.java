
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edge.Edge;
import edge.EdgeImpl;
import location.Direction;
import location.Location;
import location.LocationImpl;
import location.TreasureType;
import pair.Pair;
import pair.PairImpl;

/**
 * The class tests Location class.
 */
public class TestLocation {
  private Location location;
  Pair<Integer, Integer> coordinate;

  @Before
  public void setUp() {
    coordinate = new PairImpl<Integer, Integer>(0, 0);
    location = new LocationImpl(0, coordinate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Constructor1() {
    new LocationImpl(-1, new PairImpl<Integer, Integer>(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Constructor2() {
    new LocationImpl(0, null);
  }

  @Test
  public void testTreasures() {
    assertEquals(0, location.getTreasures().size());
    location.addTreasures(TreasureType.DIAMOND);
    assertEquals(1, location.getTreasures().size());

  }

  @Test
  public void testNeighbors() {
    assertEquals(null, location.getLocation(Direction.NORTH));
    assertEquals(null, location.getLocation(Direction.EAST));
    assertEquals(null, location.getLocation(Direction.SOUTH));
    assertEquals(null, location.getLocation(Direction.WEST));

    coordinate = new PairImpl<Integer, Integer>(0, 1);
    Location location2 = new LocationImpl(1, coordinate);
    Edge e = new EdgeImpl(location, location2);

    location.setEdge(Direction.EAST, e);
    assertEquals(location2, location.getLocation(Direction.EAST));
    assertEquals(1, location.getAllNeighbors().size());

  }

}
