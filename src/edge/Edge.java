package edge;

import location.Location;

/**
 * Edge interface represents each edge in the dungeon.
 */
public interface Edge {
  /**
   * Get the start location.
   * 
   * @return start location
   */
  Location getStart();

  /**
   * Get the end location.
   * 
   * @return end location
   */
  Location getEnd();

  /**
   * Set the start location.
   * 
   * @param location to set the start
   */
  void setStart(Location location);

  /**
   * Set the end location.
   * 
   * @param location to set the end
   */
  void setEnd(Location location);

}
