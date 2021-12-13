package location;

import java.util.List;
import edge.Edge;
import otyugh.Otyugh;
import pair.Pair;

/**
 * Location interface represents each location in the dungeon.
 */
public interface Location {
  /**
   * Get the type of the location.
   * 
   * @return type of the location
   */
  LocationType getType();

  /**
   * Get the treasures in the location.
   * 
   * @return treasures in the location
   */
  List<TreasureType> getTreasures();

  /**
   * Add treasure to the location.
   * 
   * @param ts the treasure to add
   */
  void addTreasures(TreasureType ts);

  /**
   * Remove all treasures.
   */
  void removeTreasures();

  /**
   * Get all neighbors of the location.
   * 
   * @return all neighbors
   */
  List<Location> getAllNeighbors();

  /**
   * Get coordinate of the location.
   * 
   * @return coordinate
   */
  Pair<Integer, Integer> getCoordinate();

  /**
   * Get index of the location.
   * 
   * @return index
   */
  int getIndex();

  /**
   * Check is the location is the end location.
   * 
   * @return if is the end location
   */
  boolean isEnd();

  /**
   * Check is the location is the start location.
   * 
   * @return if is the start location
   */
  boolean isStart();

  /**
   * Set the location to be the end location.
   */
  void setEnd();

  /**
   * Set the location to be the start location.
   */
  void setStart();

  /**
   * Set type to the location.
   * 
   * @param type to set
   */
  void setType(LocationType type);

  /**
   * Get otyugh of this location.
   * 
   * @return otyugh of the location
   */
  Otyugh getOtyugh();

  /**
   * Add otyugh to this location.
   */
  void addOtyugh();

  /**
   * Remove otyugh of this location.
   */
  void removeOtyugh();

  /**
   * Shoot otyugh in this location.
   */
  void shootOtyugh();

  /**
   * Get probalility to escape.
   * 
   * @return probalility to escape
   */
  double probalilityToEscape();

  /**
   * Get neighbor location.
   * 
   * @param dir direction of neighbor
   * @return neighbor location
   */
  Location getLocation(Direction dir);

  /**
   * Set edge to the location.
   * 
   * @param dir direction of edge
   * @param e   edge to set
   */
  void setEdge(Direction dir, Edge e);

}
