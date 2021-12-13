package view;


/**
 * GamePanel interface represents a GamePanel.
 */
public interface GamePanel {

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
}
