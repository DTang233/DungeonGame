package dungeon;

import location.Location;

/**
 * Dungeon interface represents each dungeon in the game.
 */
public interface Dungeon {
  /**
   * Add treasure to the dungeon.
   * 
   * @param percentage of the treasures
   */
  void addTreasure(int percentage);

  /**
   * Get the start location.
   * 
   * @return start location
   */
  Location getStartLocation();

  /**
   * Get the end location.
   * 
   * @return end location
   */
  Location getEndLocation();

  /**
   * Get a copy of all locations.
   * 
   * @return all locations
   */
  Location[][] getLocations();

}
