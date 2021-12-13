package view;

import controller.DungeonGUIController;

/**
 * ActionPanel interface represents an ActionPanel.
 */
public interface ActionPanel {
  /**
   * Add listeners.
   * 
   * @param c the GUI controller
   */
  void addListener(DungeonGUIController c);

}
