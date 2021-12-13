package controller;

import location.Direction;

/**
 * DungeonGUIController interface represents a dungeon controller.
 */
public interface DungeonGUIController {
  /**
   * Initialize the Dungeon.
   * 
   * @param numberOfRow       the number of rows
   * @param numberOfcolumn    the number of columns
   * @param interconnectivity the number of interconnectivity
   * @param isWrapped         if the dungeon is wrapped
   * @param percentage        percentage of treasures
   * @param difficulty        difficulty level of the game
   * @param graphicalMode     true to play graphical mode false to play text mode
   */
  void initDungeon(int numberOfRow, int numberOfcolumn, int interconnectivity, boolean isWrapped,
      int percentage, int difficulty, boolean graphicalMode);

  /**
   * Starts a new game.
   */
  void newGame();

  /**
   * Move the player.
   * 
   * @param dir the moving direction
   */
  void move(Direction dir);

  /**
   * Pick treasures in the location.
   */
  void pick();

  /**
   * Shoot the otyugh.
   * 
   * @param dir  the shooting direction
   * @param dist the shooting distance
   */
  void shoot(Direction dir, String dist);

  /**
   * Display the hints on the map.
   */
  void showHint();

  /**
   * Attck in a battle.
   */
  void attack();

}
