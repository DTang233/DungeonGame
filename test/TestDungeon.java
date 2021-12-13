
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import dungeon.Dungeon;
import dungeon.DungeonImpl;
import location.Location;

/**
 * The class tests Dungeon class.
 */
public class TestDungeon {


  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new DungeonImpl(-1, 0, 0, false, 20, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new DungeonImpl(0, -1, 0, false, 20, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new DungeonImpl(0, -1, -1, false, 20, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new DungeonImpl(0, -1, -1, false, 10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    new DungeonImpl(0, -1, -1, false, 101, 1);
  }

  @Test
  public void testUnWrapped() {

    Dungeon d1 = new DungeonImpl(2, 10, 0, false, 20, 1);
    // Test if the graph is connected
    assertEquals(20, dfs(d1.getStartLocation()).size());

  }

  @Test
  public void testWrapped() {

    Dungeon d1 = new DungeonImpl(3, 6, 0, true, 20, 1);
    // Test if the graph is connected
    assertEquals(18, dfs(d1.getStartLocation()).size());
  }

  @Test
  public void testAddedTreasure() {

    Dungeon d1 = new DungeonImpl(3, 6, 0, true, 20, 1);

    // Test if added treasure
    assertTrue(dfsTreasureNums(d1.getStartLocation()) >= 2);
  }

  @Test
  public void testConnectivity() {
    Dungeon d1 = new DungeonImpl(3, 6, 0, true, 20, 1);
    assertEquals(18, dfs(d1.getStartLocation()).size());

    Dungeon d2 = new DungeonImpl(3, 6, 1, true, 20, 1);
    assertEquals(18, dfs(d2.getStartLocation()).size());

    Dungeon d3 = new DungeonImpl(2, 10, 0, false, 20, 1);
    assertEquals(20, dfs(d3.getStartLocation()).size());

    Dungeon d4 = new DungeonImpl(2, 10, 1, false, 20, 1);
    assertEquals(20, dfs(d4.getStartLocation()).size());

  }

  @Test
  public void testInterConnectivity() {
    Dungeon d1 = new DungeonImpl(3, 6, 0, true, 20, 1);

    // Test if the path == 1
    assertEquals(1, dfsNumOfPath(d1.getStartLocation(), new HashSet<Location>()));

    Dungeon d2 = new DungeonImpl(3, 6, 1, true, 20, 1);
    // Test if the path == 2
    assertEquals(2, dfsNumOfPath(d2.getStartLocation(), new HashSet<Location>()));

    Dungeon d3 = new DungeonImpl(2, 10, 0, false, 20, 1);
    // Test if the path == 1
    assertEquals(1, dfsNumOfPath(d3.getStartLocation(), new HashSet<Location>()));

    Dungeon d4 = new DungeonImpl(2, 10, 1, false, 20, 1);
    // Test if the path == 2
    assertEquals(2, dfsNumOfPath(d4.getStartLocation(), new HashSet<Location>()));

  }

  private Set<Location> dfs(Location start) {

    Stack<Location> stk = new Stack<>();
    Set<Location> visited = new HashSet<Location>();
    stk.push(start);
    visited.add(start);

    while (!stk.empty()) {
      Location loc = stk.pop();
      for (Location n : loc.getAllNeighbors()) {
        if (!visited.contains(n) && !stk.contains(n)) {
          stk.push(n);
          visited.add(n);
        }
      }

    }
    return visited;

  }

  private int dfsTreasureNums(Location start) {

    Stack<Location> stk = new Stack<>();
    Set<Location> visited = new HashSet<Location>();
    int res = 0;

    stk.push(start);
    visited.add(start);

    while (!stk.empty()) {
      Location loc = stk.pop();
      res += loc.getTreasures().size();
      for (Location n : loc.getAllNeighbors()) {
        if (!visited.contains(n) && !stk.contains(n)) {
          stk.push(n);
          visited.add(n);
        }
      }

    }
    return res;

  }

  private int dfsNumOfPath(Location loc, Set<Location> visited) {
    if (loc.isEnd()) {
      return 1;
    }
    int ways = 0;
    visited.add(loc);
    for (Location n : loc.getAllNeighbors()) {
      if (!visited.contains(n)) {
        ways += dfsNumOfPath(n, visited);
      }
    }
    visited.remove(loc);

    return ways;
  }
}
