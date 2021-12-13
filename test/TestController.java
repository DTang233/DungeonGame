import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.DungeonGUIController;
import controller.DungeonGUIControllerImpl;
import dungeon.Dungeon;
import location.Direction;
import player.Player;
import view.DungeonGUIView;

/**
 * The class tests TicTacToeController.
 */
public class TestController {

  @Test
  public void testInit() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    Player player = new MockPlayer(log3);
    DungeonGUIView view = new MockView(log2);
    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.newGame();

    assertEquals("Dungeon Initialzed.", log1.toString());
    // test add linstener and start optional panel
    assertEquals("Added listeners.Optional panel satrted.", log2.toString());

  }

  @Test
  public void testMoveSouth() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.move(Direction.SOUTH);

    assertEquals("Move SOUTH", log3.toString());

  }

  @Test
  public void testMoveEast() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.move(Direction.EAST);

    assertEquals("Move EAST", log3.toString());

  }

  @Test
  public void testMoveWest() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.move(Direction.WEST);
    assertEquals("Move WEST", log3.toString());

  }

  @Test
  public void testMoveNorth() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.move(Direction.NORTH);

    assertEquals("Move NORTH", log3.toString());

  }

  @Test
  public void testShoot() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.shoot(Direction.EAST, "2");

    assertEquals("Shoot East 2", log3.toString());
  }

  @Test
  public void testPick() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.pick();

    assertEquals("Picked treasures.", log3.toString());
  }

  @Test
  public void testHint() {

    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();

    Dungeon model = new MockDungeon(log1);
    DungeonGUIView view = new MockView(log2);
    Player player = new MockPlayer(log3);

    DungeonGUIController controller = new DungeonGUIControllerImpl(view, model, player);
    controller.showHint();

    assertEquals("Show hints.", log1.toString());
  }
}
