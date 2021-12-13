import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.DungeonImpl;
import location.Direction;
import player.Player;
import player.PlayerImpl;

/**
 * The class tests Player class.
 */
public class TestPlayer {
  private Dungeon d1;
  private Player p;

  @Before
  public void setUp() {
    d1 = new DungeonImpl(1, 6, 0, true, 20, 1);
    p = new PlayerImpl(d1.getStartLocation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Constructor2() {
    new PlayerImpl(null);
  }

  @Test
  public void testStart() {
    assertEquals((int) d1.getStartLocation().getCoordinate().getFirst(),
        (int) p.getLocation().getCoordinate().getFirst());
    assertEquals((int) d1.getStartLocation().getCoordinate().getSecond(),
        (int) p.getLocation().getCoordinate().getSecond());
  }

  @Test(expected = IllegalStateException.class)
  public void testMove1() {
    p.move(Direction.NORTH);
  }

  @Test(expected = IllegalStateException.class)
  public void testMove2() {
    p.move(Direction.SOUTH);

  }

  @Test(expected = IllegalStateException.class)
  public void testMove3() {
    p.move(Direction.WEST);
  }

  @Test
  public void testMove4() {

    p.move(Direction.EAST);
    assertEquals(0, (int) p.getLocation().getCoordinate().getFirst());
    assertEquals(1, (int) p.getLocation().getCoordinate().getSecond());

    p.move(Direction.EAST);
    assertEquals(0, (int) p.getLocation().getCoordinate().getFirst());
    assertEquals(2, (int) p.getLocation().getCoordinate().getSecond());

    p.move(Direction.EAST);
    assertEquals(0, (int) p.getLocation().getCoordinate().getFirst());
    assertEquals(3, (int) p.getLocation().getCoordinate().getSecond());

  }

  @Test(expected = IllegalStateException.class)
  public void testMove5() {

    p.move(Direction.EAST);
    p.move(Direction.EAST);
    p.move(Direction.EAST);
    p.move(Direction.EAST);
    p.move(Direction.EAST);
    assertTrue(p.getLocation().isEnd());
    assertTrue(p.isGameOver());
    p.move(Direction.EAST);

  }

  @Test
  public void testUnwrappedMove() {
    Dungeon d = new DungeonImpl(false);
    Player p1 = new PlayerImpl(d.getStartLocation());
    p1.move(Direction.EAST);
    p1.move(Direction.EAST);
    p1.move(Direction.EAST);
    assertEquals(0, (int) p1.getLocation().getCoordinate().getFirst());
    assertEquals(0, (int) p1.getLocation().getCoordinate().getSecond());
  }

  @Test
  public void pickTreasure() {
    p.pickTreasure();
    assertEquals(
        "Current Location: \n" + "( 0, 0 )\n" + "My Treasure: \n" + "RUBIE \n"
            + "Treasures in this location: \n" + "\n" + "Possible moves: \n" + "East \n",
        p.getDescription());
  }

  @Test
  public void testDescription() {
    p.move(Direction.EAST);
    assertEquals(
        "Current Location: \n" + "( 0, 1 )\n" + "My Treasure: \n" + "\n"
            + "Treasures in this location: \n" + "\n" + "Possible moves: \n" + "East West \n",
        p.getDescription());

  }

}
