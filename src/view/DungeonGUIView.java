package view;

import controller.DungeonGUIController;

/**
 * DungeonGUIView interface represents a Dungeon GUI view.
 */
public interface DungeonGUIView {

  /**
   * Add listeners.
   * 
   * @param c the GUI controller
   */
  void addListener(DungeonGUIController c);

  /**
   * Start the option panel.
   */

  void startOptionPanel();

  /**
   * Start the game panel.
   */
  void startGamePanel();

  /**
   * Start the text panel.
   */
  void startTextPanel();

  /**
   * Start the battle panel.
   */
  void startBattlePanle();

  /**
   * Set the current game status.
   * 
   * @param status the status of the game
   * @param curRow the row number of the dungeon
   * @param curCol the column number of the dungeon
   */
  void setStatus(String[][] status, int curRow, int curCol);

  /**
   * Set the log to display.
   * 
   * @param log the log to display
   */
  void setGameLog(String log);

  /**
   * Set the status of otyughs.
   * 
   * @param otyughs the status of otyughs
   */
  void setOtyugh(String[][] otyughs);

  /**
   * Display hints to the map.
   */
  void showHint();

  /**
   * Called when game is over.
   * 
   * @param won if the player won
   */
  void gameOver(boolean won);

  /**
   * Called when battle is finished.
   */
  void finishBattle();

}
