package controller;

import dungeon.Dungeon;
import dungeon.DungeonImpl;
import player.Player;
import player.PlayerImpl;
import view.DungeonGUIView;
import location.Direction;
import location.Location;

/**
 * The class implements DungeonGUIController interface.
 */
public class DungeonGUIControllerImpl implements DungeonGUIController {

  private DungeonGUIView view;
  private Dungeon dungeon;
  private int row;
  private int col;
  private Player player;
  private String[][] status;
  private String[][] otyughs;
  private boolean[][] visited;
  private String action;
  private boolean graphicalMode;

  /**
   * Constructs a DungeonGUIController with given view and Dungeon for testing.
   * 
   * @param view    the GUI of the game
   * @param dungeon the model of the game
   * @param player  the player model of the game
   */
  public DungeonGUIControllerImpl(DungeonGUIView view, Dungeon dungeon, Player player) {
    this.view = view;
    this.view.addListener(this);

    this.dungeon = dungeon;
    this.player = player;

    this.status = null;
    this.visited = null;
    this.otyughs = null;

    this.row = 0;
    this.col = 0;

    this.action = "Start a new game!<br/>";
    this.graphicalMode = false;

  }

  /**
   * Constructs a DungeonGUIController.
   * 
   * @param view the GUI of the game
   */
  public DungeonGUIControllerImpl(DungeonGUIView view) {
    this.view = view;
    this.view.addListener(this);

    this.dungeon = null;
    this.player = null;

    this.status = null;
    this.visited = null;
    this.otyughs = null;

    this.row = 0;
    this.col = 0;

    this.action = "Start a new game!<br/>";
    this.graphicalMode = false;

  }

  @Override
  public void newGame() {
    this.view.startOptionPanel();

  }

  @Override
  public void initDungeon(int numberOfRow, int numberOfcolumn, int interconnectivity,
      boolean isWrapped, int percentage, int difficulty, boolean graphicalMode) {

    try {
      this.dungeon = new DungeonImpl(numberOfRow, numberOfcolumn, interconnectivity, isWrapped,
          percentage, difficulty);

    } catch (IllegalArgumentException e) {
      System.out.println(e);
      // do nothing
      return;
    }
    this.player = new PlayerImpl(this.dungeon.getStartLocation());

    // set status
    this.row = numberOfRow;
    this.col = numberOfcolumn;
    this.status = new String[this.row][this.col];
    this.visited = new boolean[this.row][this.col];
    this.otyughs = new String[this.row][this.col];
    this.graphicalMode = graphicalMode;

    updateOtyughs();
    updateStatus(this.player.getLocation());

    if (this.graphicalMode) {
      this.view.startGamePanel();

    } else {
      this.view.startTextPanel();
    }
    System.out.println(format(this.action + player.getDescription()));
    this.view.setGameLog(format(this.action + player.getDescription()));

  }

  private void updateOtyughs() {

    Location[][] locs = this.dungeon.getLocations();

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {

        if (locs[i][j].getOtyugh() != null) {
          this.otyughs[i][j] = "otyugh";

        } else {
          this.otyughs[i][j] = null;
        }

      }
    }

    this.view.setOtyugh(this.otyughs);

  }

  private void detectOtyugh() {
    if (this.player.detectOtyugh() == 1) {

      this.action += "A pungent smell nearby.<br/>";

    } else if (this.player.detectOtyugh() == 2) {
      this.action += "A VERY pungent smell nearby.<br/>";
    }
  }

  private void updateStatus(Location location) {

    int curRow = location.getCoordinate().getFirst();
    int curCol = location.getCoordinate().getSecond();

    if (this.otyughs[curRow][curCol] != null) {
      battle();
      return;

    }

    this.visited[curRow][curCol] = true;

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        if (this.status[i][j] == null && this.visited[i][j]) {
          String label = getLocationLabel(location);
          if (label != null) {
            this.status[i][j] = label;
          }

        }

      }
    }

    detectOtyugh();
    this.view.setStatus(this.status, curRow, curCol);
    this.view.setGameLog(format(this.action + player.getDescription()));
    if (this.otyughs[curRow][curCol] == null && location.isEnd()) {
      won();
    }
  }

  private String getLocationLabel(Location location) {

    // one neighbor
    if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) == null) {

      return "W";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) == null) {

      return "N";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) == null) {

      return "E";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) != null) {

      return "S";

    }

    // two neighbor
    if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) == null) {

      return "WN";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) == null) {

      return "NE";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) != null) {

      return "ES";

    } else if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) != null) {

      return "SW";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) != null) {

      return "NS";

    } else if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) == null) {

      return "EW";

    }

    // three neighbor
    if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) == null) {

      return "NEW";

    } else if (location.getLocation(Direction.WEST) == null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) != null) {

      return "NES";

    } else if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) == null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) != null) {

      return "ESW";

    } else if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) == null
        && location.getLocation(Direction.SOUTH) != null) {

      return "SWN";
    }

    // for neighbor
    if (location.getLocation(Direction.WEST) != null
        && location.getLocation(Direction.NORTH) != null
        && location.getLocation(Direction.EAST) != null
        && location.getLocation(Direction.SOUTH) != null) {

      return "NESW";

    }

    return null;
  }

  @Override
  public void move(Direction dir) {

    action = "You moved " + dir.toString() + ".<br/>";

    try {
      this.player.move(dir);
      
    } catch (IllegalStateException e) {
      System.out.println(e);
      // do nothing
      return;
    }

    updateStatus(this.player.getLocation());

  }

  @Override
  public void pick() {
    System.out.println("Pick");
    action = "You choose to pick.<br/>";
    this.player.pickTreasure();
    this.view.setGameLog(format(this.action + player.getDescription()));

  }

  @Override
  public void shoot(Direction dir, String dist) {

    this.action = "You choose to shoot.<br/>";
    if (dir == null || dist == null) {
      this.action += "Specify direction (←|→|↑|↓).<br/>Specify distance (1-3).<br/>";
      this.view.setGameLog(format(this.action + player.getDescription()));
      return;

    }
    int num = -1;

    try {
      num = Integer.parseInt(dist);

    } catch (NumberFormatException e) {

      return;
    }
    this.action = "shoot " + dir.toString() + " " + dist + "<br/>";

    boolean shot = false;
    try {
      shot = this.player.shoot(dir, num);

    } catch (IllegalStateException e) {
      System.out.println(e);
      return;
    }

    if (shot) {
      this.action += "You shot an otyugh!<br/>";
    } else {
      this.action += "You missed an otyugh!<br/>";
    }
    this.updateOtyughs();
    this.updateStatus(this.player.getLocation());

  }

  private void won() {
    this.view.gameOver(true);
  }

  private void lose() {
    this.view.gameOver(false);

  }

  private void battle() {
    this.view.startBattlePanle();

  }

  private String format(String content) {
    return "<html>" + content + "</html>";

  }

  @Override
  public void showHint() {
    this.view.showHint();
    this.updateStatus(this.player.getLocation());

  }

  @Override
  public void attack() {

    String turn = "You were faster!<br/>";
    int prop = randomNumber(0, 1);
    if (prop == 0) {
      this.player.getLocation().getOtyugh().reduceHealth();
    } else if (prop == 1) {
      this.player.reduceHealth();
      turn = "Otyugh were faster!<br/>";
    }
    this.action = format(
        turn + "Your health: " + this.player.getHealth() + "/2 <br/> Otyugh's health: "
            + this.player.getLocation().getOtyugh().getHealth() + "/2<br/>");
    this.view.setGameLog(this.action);

    if (this.player.getHealth() == 0) {
      lose();
      this.view.finishBattle();
      return;
    }
    if (this.player.getLocation().getOtyugh().getHealth() == 0) {
      this.player.getLocation().removeOtyugh();
      this.updateOtyughs();
      updateStatus(this.player.getLocation());
      finishBattle();
      return;
    }

  }

  private void finishBattle() {
    this.view.finishBattle();

  }

  private int randomNumber(int min, int max) {
    return min + (int) (Math.random() * ((max - min) + 1));
  }

}
