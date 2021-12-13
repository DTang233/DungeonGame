package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import location.Direction;
import location.Location;
import location.LocationImpl;
import location.LocationType;
import location.TreasureType;
import edge.Edge;
import edge.EdgeImpl;
import pair.Pair;
import pair.PairImpl;

/**
 * The class implements Dungeon interface.
 */
public class DungeonImpl implements Dungeon {

  private final boolean isWrapped;
  private final int interconnectivity;
  private final List<Set<Integer>> edgeList;
  private final Location[][] locations;
  private final List<Location> caves;
  private Map<Integer, List<Integer>> possibleNeighbors;
  private int percentage;
  private Location start;
  private Location end;
  private final int numberOfRow;
  private final int numberOfcolumn;
  private final int numberOfOtyugh;

  /**
   * Constructs a Dungeon only for driver class.
   * 
   * @param isWrapped if the dungeon id wrapped
   * @throws IllegalArgumentException if the argument is null
   */
  public DungeonImpl(boolean isWrapped) throws IllegalArgumentException {
    if (Objects.isNull(isWrapped)) {
      throw new IllegalArgumentException("Argument cannot be null");
    }
    if (isWrapped) {
      this.numberOfcolumn = 6;
      this.numberOfRow = 2;
    } else {
      this.numberOfcolumn = 10;
      this.numberOfRow = 2;
    }

    this.isWrapped = isWrapped;
    this.interconnectivity = 0;
    this.edgeList = new ArrayList<Set<Integer>>();
    this.locations = new Location[numberOfRow][numberOfcolumn];
    this.caves = new ArrayList<>();
    this.possibleNeighbors = new HashMap<Integer, List<Integer>>();

    this.percentage = 50;
    createLocations();
    if (isWrapped) {
      this.start = this.locations[1][0];
      this.end = this.locations[1][5];
      this.locations[1][0].setStart();

      this.locations[1][5].setEnd();
      this.locations[1][0].setType(LocationType.CAVE);
      this.locations[1][5].setType(LocationType.CAVE);

      for (int i = 0; i < 5; i++) {
        addEdge(this.locations[0][i], this.locations[0][i + 1]);
        addEdge(this.locations[0][i], this.locations[1][i]);
        this.locations[0][i].addTreasures(TreasureType.RUBIE);
        this.locations[0][i].addTreasures(TreasureType.ARROW);
      }
      addEdge(this.locations[0][5], this.locations[1][5]);

    } else {
      this.start = this.locations[0][7];
      this.end = this.locations[0][2];
      this.locations[0][7].setStart();
      this.locations[0][2].setEnd();

      for (int i = 1; i < 9; i++) {
        addEdge(this.locations[0][i], this.locations[0][i + 1]);
        addEdge(this.locations[0][i], this.locations[1][i]);
        this.locations[0][i].addTreasures(TreasureType.RUBIE);
        this.locations[0][i].addTreasures(TreasureType.ARROW);
      }
      addEdge(this.locations[0][0], this.locations[1][0]);
      addEdge(this.locations[0][9], this.locations[1][9]);
      addEdge(this.locations[0][9], this.locations[0][0]);
    }
    createCaves();
    this.numberOfOtyugh = 2;
    this.locations[0][2].addOtyugh();
    this.end.addOtyugh();

  }

  /**
   * Constructs a Dungeon with given row, column numbers, interconnectivity add if
   * a boolean that determines if it's wrapped.
   * 
   * @param numberOfRow       the number of row
   * @param numberOfcolumn    the number of column
   * @param interconnectivity the interconnectivity
   * @param isWrapped         if the dungeon is wrapped
   * @param percentage        the percentage of the treasures and arrows
   * @param difficulty        the number of Otyugh besides the one at the end
   * @throws IllegalArgumentException number of row and column is negative or
   *                                  smaller that 6 when wrapped or smaller than
   *                                  10 when unwrapped, or percentage is < 20 or
   *                                  > 100
   */
  public DungeonImpl(int numberOfRow, int numberOfcolumn, int interconnectivity, boolean isWrapped,
      int percentage, int difficulty) throws IllegalArgumentException {

    if (numberOfRow < 1 || numberOfcolumn < 1 || interconnectivity < 0 || difficulty < 0
        || Objects.isNull(isWrapped)) {
      throw new IllegalArgumentException("Arguments must larger than 0, and cannot be null!");

    }
    if (isWrapped && numberOfRow < 6 && numberOfcolumn < 6) {
      throw new IllegalArgumentException(
          "Row and colunmn cannot be both smaller than 6 when it's wrapped!");

    }
    // If row or column is 1, it will ended up the same as wrapped dungeon
    if (!isWrapped && numberOfRow < 10 && numberOfcolumn < 10) {
      throw new IllegalArgumentException(
          "Row and colunmn cannot be both smaller than 10 when it's unwrapped!");
    }

    if (percentage < 20 || percentage > 100) {
      throw new IllegalArgumentException("Illegal percentage of treasure!");
    }
    this.isWrapped = isWrapped;
    this.interconnectivity = interconnectivity;
    this.edgeList = new ArrayList<Set<Integer>>();
    this.locations = new Location[numberOfRow][numberOfcolumn];
    this.caves = new ArrayList<>();
    this.possibleNeighbors = new HashMap<Integer, List<Integer>>();
    this.numberOfcolumn = numberOfcolumn;
    this.numberOfRow = numberOfRow;
    this.percentage = percentage;

    this.start = null;
    this.end = null;

    this.numberOfOtyugh = difficulty;

    createLocations();
    createEdgeMapAndSet();
    createStartAndEnd();
    createEdges();
    createCaves();
    addTreasure(this.percentage);
    this.end.addOtyugh();
    addOtyughs();

  }

  private void createLocations() {

    int index = 0;
    for (int row = 0; row < this.numberOfRow; row++) {
      for (int col = 0; col < this.numberOfcolumn; col++) {

        Pair<Integer, Integer> xy = new PairImpl<>(row, col);
        Location location = new LocationImpl(index, xy);
        this.locations[row][col] = location;
        index++;

      }
    }

  }

  private void createEdgeMapAndSet() {

    List<int[]> directions = Arrays.asList(new int[] { 1, 0 }, new int[] { -1, 0 },
        new int[] { 0, 1 }, new int[] { 0, -1 });

    for (int r = 0; r < this.numberOfRow; r++) {
      for (int c = 0; c < this.numberOfcolumn; c++) {

        for (int[] direction : directions) {
          int row = r + direction[0];
          int col = c + direction[1];
          if (row < 0 || col < 0 || row >= this.numberOfRow || col >= this.numberOfcolumn) {
            continue;
          }

          int nIndx = row * this.numberOfcolumn + col;
          int indx = this.locations[r][c].getIndex();

          if (!this.possibleNeighbors.containsKey(indx)) {
            this.possibleNeighbors.put(indx, new ArrayList<Integer>());
          }
          this.possibleNeighbors.get(indx).add(nIndx);

          Set<Integer> s = new HashSet<>();
          s.add(nIndx);
          s.add(indx);

          if (!this.edgeList.contains(s)) {

            this.edgeList.add(s);

          }

        }
      }

    }

    // If is unwraped , add edge from 4 boarders
    if (!this.isWrapped) {
      for (int r = 0; r < this.numberOfRow; r++) {
        int nIndx = r * this.numberOfcolumn + this.numberOfcolumn - 1;
        int indx = r * this.numberOfcolumn + 0;

        if (!this.possibleNeighbors.get(indx).contains(nIndx)) {
          this.possibleNeighbors.get(indx).add(nIndx);
        }
        if (!this.possibleNeighbors.get(nIndx).contains(indx)) {
          this.possibleNeighbors.get(nIndx).add(indx);
        }

        Set<Integer> s = new HashSet<>();
        s.add(nIndx);
        s.add(indx);
        if (!this.edgeList.contains(s)) {

          this.edgeList.add(s);

        }

      }

      for (int c = 0; c < this.numberOfcolumn; c++) {
        int nIndx = (this.numberOfRow - 1) * this.numberOfcolumn + c;
        int indx = 0 * this.numberOfcolumn + c;

        if (!this.possibleNeighbors.get(indx).contains(nIndx)) {
          this.possibleNeighbors.get(indx).add(nIndx);
        }
        if (!this.possibleNeighbors.get(nIndx).contains(indx)) {
          this.possibleNeighbors.get(nIndx).add(indx);
        }

        Set<Integer> s = new HashSet<>();
        s.add(nIndx);
        s.add(indx);
        if (!this.edgeList.contains(s)) {

          this.edgeList.add(s);

        }

      }

    }

  }

  private List<Set<Location>> buildLocationSetHelper() {

    List<Set<Location>> locationSet = new ArrayList<>();
    List<Location> copy = new ArrayList<>();

    for (Location[] l : this.locations) {
      Collections.addAll(copy, l);
    }

    Location[] startAndEnd = { this.start, this.end };

    for (Location location : startAndEnd) {
      Set<Location> set = new HashSet<Location>();
      for (Location n : location.getAllNeighbors()) {

        set.add(n);
        copy.remove(n);

      }

      set.add(location);
      copy.remove(location);
      locationSet.add(set);
    }

    for (Location loc : copy) {

      Set<Location> set = new HashSet<Location>();
      set.add(loc);
      locationSet.add(set);

    }

    return locationSet;
  }

  private void createEdges() {

    // Create sets
    List<Set<Location>> locationSet = buildLocationSetHelper();

    // Apply modified Kruskal's algorithm
    List<Set<Integer>> leftOver = new ArrayList<>();
    List<Set<Integer>> edges = this.edgeList;

    while (locationSet.size() > 1) {

      Set<Integer> s = edges.get(randomNumber(0, edges.size() - 1));
      Iterator<Integer> iter = s.iterator();

      int first = iter.next();
      int second = iter.next();

      Set<Location> set1 = new HashSet<Location>();
      Set<Location> set2 = new HashSet<Location>();

      Location firstLoc = null;
      Location secondLoc = null;
      for (Set<Location> lset : locationSet) {

        for (Location loc : lset) {

          if (first == loc.getIndex()) {
            set1 = lset;
            firstLoc = loc;
          }
          if (second == loc.getIndex()) {
            set2 = lset;
            secondLoc = loc;
          }

        }

      }
      if (set1.equals(set2)) {
        leftOver.add(s);
        edges.remove(s);
      } else {

        addEdge(firstLoc, secondLoc);
        locationSet.remove(set1);
        locationSet.remove(set2);
        set1.addAll(set2);
        locationSet.add(set1);
        edges.remove(s);
      }

    }
    leftOver.addAll(edges);
    addInterconnectivity(leftOver);
  }

  private void addInterconnectivity(List<Set<Integer>> leftOver) throws IllegalStateException {
    if (this.interconnectivity > leftOver.size()) {
      throw new IllegalStateException("Interconnectivity is too larger, try smaller one.");
    }

    for (int i = 0; i < this.interconnectivity; i++) {
      Set<Integer> s = leftOver.get(randomNumber(0, leftOver.size() - 1));

      Iterator<Integer> iter = s.iterator();
      int first = iter.next();
      int second = iter.next();

      leftOver.remove(s);

      int row1 = first / this.numberOfcolumn;
      int col1 = first % this.numberOfcolumn;
      int row2 = second / this.numberOfcolumn;
      int col2 = second % this.numberOfcolumn;

      addEdge(this.locations[row1][col1], this.locations[row2][col2]);

    }

  }

  private void addEdge(Location l1, Location l2) {
    Edge e1 = new EdgeImpl(l1, l2);
    Edge e2 = new EdgeImpl(l2, l1);

    int row1 = l1.getCoordinate().getFirst();
    int col1 = l1.getCoordinate().getSecond();

    int row2 = l2.getCoordinate().getFirst();
    int col2 = l2.getCoordinate().getSecond();

    if (row1 - 1 == row2) {
      l1.setEdge(Direction.NORTH, e1);
      l2.setEdge(Direction.SOUTH, e2);
    } else if (row1 + 1 == row2) {
      l1.setEdge(Direction.SOUTH, e1);
      l2.setEdge(Direction.NORTH, e2);

    } else if (col1 - 1 == col2) {
      l1.setEdge(Direction.WEST, e1);
      l2.setEdge(Direction.EAST, e2);

    } else if (col1 + 1 == col2
        || (!this.isWrapped && col1 == this.numberOfcolumn - 1 && col2 == 0)) {
      l1.setEdge(Direction.EAST, e1);
      l2.setEdge(Direction.WEST, e2);

    } else if (!this.isWrapped && row1 == 0 && row2 == this.numberOfRow - 1) {
      l1.setEdge(Direction.NORTH, e1);
      l2.setEdge(Direction.SOUTH, e2);

    } else if (!this.isWrapped && row1 == this.numberOfRow - 1 && row2 == 0) {
      l1.setEdge(Direction.SOUTH, e1);
      l2.setEdge(Direction.NORTH, e2);

    } else if (!this.isWrapped && col1 == 0 && col2 == this.numberOfcolumn - 1) {
      l1.setEdge(Direction.WEST, e1);
      l2.setEdge(Direction.EAST, e2);

    } else if (!this.isWrapped && col1 == this.numberOfcolumn - 1 && col2 == 0) {
      l1.setEdge(Direction.EAST, e1);
      l2.setEdge(Direction.WEST, e2);
    }

  }

  private void createStartAndEnd() {
    if (this.isWrapped) {
      if (numberOfRow == 1) {
        this.start = this.locations[0][0];
        this.end = this.locations[0][numberOfcolumn - 1];
      } else if (numberOfcolumn == 1) {
        this.start = this.locations[0][0];
        this.end = this.locations[numberOfRow - 1][0];

      } else {
        // Create start location and end location first
        if (this.numberOfcolumn >= 6) {
          // When column >= 5, the column of start is between [0, length of column - 6]
          int rowS = randomNumber(0, this.numberOfRow - 1);
          int colS = randomNumber(0, this.numberOfcolumn - 6);
          this.start = this.locations[rowS][colS];

          // The column number of end is between [colS + 5, column length - 1]
          int rowE = randomNumber(0, this.numberOfRow - 1);
          int colE = randomNumber(colS + 5, this.numberOfcolumn - 1);
          this.end = this.locations[rowE][colE];

        } else {
          int rowS = randomNumber(0, this.numberOfRow - 6);
          int colS = randomNumber(0, this.numberOfcolumn - 1);
          this.start = this.locations[rowS][colS];

          int rowE = randomNumber(rowS + 5, this.numberOfRow - 1);
          int colE = randomNumber(0, this.numberOfcolumn - 1);
          this.end = this.locations[rowE][colE];

        }
      }
    } else {

      if (numberOfRow == 1) {
        this.start = this.locations[0][2];
        this.end = this.locations[0][numberOfcolumn - 3];
      } else if (numberOfcolumn == 1) {
        this.start = this.locations[2][0];
        this.end = this.locations[numberOfRow - 3][0];

      } else {
        if (this.numberOfcolumn >= 10) {
          // When column >= 10, the column of start is between [2, column length - 8]
          int rowS = randomNumber(0, this.numberOfRow - 1);
          int colS = randomNumber(2, this.numberOfcolumn - 8);
          this.start = this.locations[rowS][colS];

          // The column number of end is between [colS + 5, column length - 3]
          int rowE = randomNumber(0, this.numberOfRow - 1);
          int colE = randomNumber(colS + 5, this.numberOfcolumn - 3);
          this.end = this.locations[rowE][colE];

        } else {
          // When row >= 10, the row of start is between [2, row length - 8]
          int rowS = randomNumber(2, this.numberOfRow - 8);
          int colS = randomNumber(0, this.numberOfcolumn - 1);
          this.start = this.locations[rowS][colS];

          // The row number of end is between [rowS + 5, row length - 3]
          int rowE = randomNumber(rowS + 5, this.numberOfRow - 3);
          int colE = randomNumber(0, this.numberOfcolumn - 1);
          this.end = this.locations[rowE][colE];

        }

      }

    }
    this.start.setStart();
    this.end.setEnd();

    // Create neighbors for start and end
    List<Location> startAndEnd = Arrays.asList(this.start, this.end);

    for (Location location : startAndEnd) {
      // Start and end cannot have 2 neighbors
      List<Integer> neighbors = this.possibleNeighbors.get(location.getIndex());
      if (this.isWrapped && neighbors.size() == 2) {
        neighbors.remove(0);
      }

      // Add all neighbors to start and end
      for (int i : neighbors) {
        int row = i / this.numberOfcolumn;
        int col = i % this.numberOfcolumn;

        addEdge(location, this.locations[row][col]);
      }

    }

  }

  private void createCaves() {
    for (int r = 0; r < this.numberOfRow; r++) {
      for (int c = 0; c < this.numberOfcolumn; c++) {
        if (this.locations[r][c].getAllNeighbors().size() != 2) {
          this.locations[r][c].setType(LocationType.CAVE);
          this.caves.add(this.locations[r][c]);
        } else {
          this.locations[r][c].setType(LocationType.TUNNEL);
        }
      }
    }
  }

  private int randomNumber(int min, int max) {
    return min + (int) (Math.random() * ((max - min) + 1));
  }

  private void addOtyughs() {
    if (this.numberOfOtyugh > this.caves.size() - 1) {
      throw new IllegalArgumentException(
          "Number of Otyugh cannot be larger than number of canves!");
    }

    int cnt = 0;
    while (cnt < this.numberOfOtyugh) {
      int indx = randomNumber(0, this.caves.size() - 1);
      if (this.caves.get(indx).isStart() || this.caves.get(indx).getOtyugh() != null) {
        continue;
      }
      this.caves.get(indx).addOtyugh();
      cnt += 1;
    }

  }

  @Override
  public void addTreasure(int percentage) throws IllegalArgumentException {
    if (percentage < 20 || percentage > 100) {
      throw new IllegalArgumentException("Illegal percentage of treasure!");
    }
    int limit = (int) Math.ceil(((((float) this.caves.size()) / 100) * percentage));
    TreasureType[] types = { TreasureType.DIAMOND, TreasureType.RUBIE, TreasureType.SAPPHIRE };
    // Add treasure
    for (int i = 0; i < limit; i++) {
      int indx = randomNumber(0, this.caves.size() - 1);
      this.caves.get(indx).addTreasures(types[randomNumber(0, 2)]);
    }
    // Add arrows
    for (int i = 0; i < limit; i++) {
      int row = randomNumber(0, this.numberOfRow - 1);
      int col = randomNumber(0, this.numberOfcolumn - 1);
      this.locations[row][col].addTreasures(TreasureType.ARROW);

    }

  }

  @Override
  public Location getStartLocation() {
    return this.start;
  }

  @Override
  public Location getEndLocation() {
    return this.end;
  }

  @Override
  public Location[][] getLocations() {
    Location[][] res = new Location[this.numberOfRow][this.numberOfcolumn];

    for (int r = 0; r < this.numberOfRow; r++) {

      res[r] = this.locations[r].clone();
    }

    return res;
  }

}
