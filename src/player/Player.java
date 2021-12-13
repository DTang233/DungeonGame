package player;

import location.Location;
import location.Direction;

/**
 * Player interface represents each player in the game.
 */
public interface Player {
  /**
   * Pick up the treasures.
   */
  void pickTreasure();

  /**
   * Get the description of current status.
   * 
   * @return current status
   */
  String getDescription();

  /**
   * Get the current location.
   * 
   * @return current location
   */
  Location getLocation();

  /**
   * Check if current location is the end.
   * 
   * @return if current location is the end
   */
  boolean isGameOver();

  /**
   * Shoot at given direction and distance.
   * 
   * @param dir      the direction
   * @param distance the distance
   */
  boolean shoot(Direction dir, int distance);

  /**
   * Detect otyugh at this location.
   * 
   * @return 0 means no otyugh around
   */
  int detectOtyugh();

  /**
   * Get health of the Player.
   * 
   * @return health of Player
   */
  int getHealth();

  /**
   * Reduce the health of the Player.
   */
  void reduceHealth();

  /**
   * Move to goven direction.
   * 
   * @param dir the direction
   */
  void move(Direction dir);

}
