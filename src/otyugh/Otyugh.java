package otyugh;

/**
 * Dungeon interface represents an Otyugh.
 */
public interface Otyugh {
  /**
   * Get health of the Otyugh.
   * 
   * @return health of Otyugh
   */
  int getHealth();

  /**
   * Reduce the health of the Otyugh.
   */
  void reduceHealth();

  /**
   * Get probalility to escape.
   * 
   * @return probalility to escape
   */
  double getProbalilityToEscape();
}
