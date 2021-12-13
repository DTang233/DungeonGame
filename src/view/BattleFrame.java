package view;

import controller.DungeonGUIController;

/**
 * BattleFrame interface represents a BattleFrame.
 */
public interface BattleFrame {
  /**
   * Add listeners.
   * 
   * @param c the GUI controller
   */
  void addListener(DungeonGUIController c);

  /**
   * Set the log to display.
   * 
   * @param log the log to display
   */
  void setGameLog(String log);

  /**
   * Show the frame.
   */
  void start();

  /**
   * Close the frame.
   */
  void close();
}
