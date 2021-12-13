import controller.DungeonGUIController;
import view.DungeonGUIView;

/**
 * The class that implements DungeonGUIView, for testing purpose.
 */
public class MockView implements DungeonGUIView {

  private StringBuilder log;

  /**
   * Construct a MockView with given StringBuilder.
   *
   * @param log the log to output the result
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addListener(DungeonGUIController c) {
    log.append("Added listeners.");

  }

  @Override
  public void startOptionPanel() {
    log.append("Optional panel satrted.");

  }

  @Override
  public void startGamePanel() {
    log.append("Game panel satrted.");

  }

  @Override
  public void startTextPanel() {
    log.append("Text panel satrted.");

  }

  @Override
  public void startBattlePanle() {
    log.append("Battle panel satrted.");

  }

  @Override
  public void setStatus(String[][] status, int curRow, int curCol) {
    log.append("Set status" + " " + curRow + " " + curCol);

  }

  @Override
  public void setGameLog(String log) {
    this.log.append(log);

  }

  @Override
  public void setOtyugh(String[][] otyughs) {
    this.log.append("Add otyughs.");

  }

  @Override
  public void showHint() {
    this.log.append("Show hints.");

  }

  @Override
  public void gameOver(boolean won) {
    this.log.append("Game over from view.");

  }

  @Override
  public void finishBattle() {
    this.log.append("Finish battle.");

  }

}
