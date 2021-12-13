package driver;

import view.DungeonGUIView;
import view.DungeonGUIViewImpl;

import controller.DungeonGUIController;
import controller.DungeonGUIControllerImpl;

/**
 * The class runs the game.
 */
public class Driver {
  /**
   * Run the game.
   */
  public static void main(String[] args) {

    DungeonGUIView view = new DungeonGUIViewImpl("Dungeon Game");

    DungeonGUIController controller = new DungeonGUIControllerImpl(view);
    controller.newGame();

  }

}
