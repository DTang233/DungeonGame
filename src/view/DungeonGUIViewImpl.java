package view;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import controller.DungeonGUIController;
import location.Direction;

/**
 * The class extends JFrame and implements DungeonGUIView interface.
 */
public class DungeonGUIViewImpl extends JFrame implements DungeonGUIView {

  private static final long serialVersionUID = 3054604793248811630L;

  private OptionPanel optionPanel;
  private GamePanel gamePanel;
  private ActionPanel actionPanel;
  private TextPanel textPanel;
  private BattleFrame battleFrame;
  private JScrollPane scrollFrame;

  private JMenuItem newGame;
  private JMenuItem quit;
  private JCheckBoxMenuItem hint;

  private Direction direction;
  private String distance;

  /**
   * Constructs a DungeonGUIView.
   * 
   * @param title the titgle of the game
   */
  public DungeonGUIViewImpl(String title) {

    setTitle(title);
    setBounds(500, 500, 500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop program from running when closing window
    initMenu();

    this.optionPanel = new OptionPanelImpl();
    this.gamePanel = new GamePanelImpl();
    this.scrollFrame = null;
    this.actionPanel = new ActionPanelImpl();
    this.textPanel = new TextPanelImpl();
    this.battleFrame = new BattleFrameImpl();

    this.direction = null;
    this.distance = null;

  }

  private void initMenu() {

    JMenuBar menuBar = new JMenuBar();

    // Build the first menu.
    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);

    this.quit = new JMenuItem("Quit");
    menu.add(this.quit);

    this.newGame = new JMenuItem("New Game");
    menu.add(this.newGame);

    JMenu optMenu = new JMenu("Options");
    menuBar.add(optMenu);
    this.hint = new JCheckBoxMenuItem("Show hint");
    optMenu.add(hint);

    setJMenuBar(menuBar);

  }

  private void addMenuListenler(DungeonGUIController c) {
    this.quit.addActionListener(l -> System.exit(0));
    this.newGame.addActionListener(l -> startOptionPanel());
    this.hint.addItemListener(l -> c.showHint());

  }

  @Override
  public void startOptionPanel() {
    if (this.scrollFrame != null) {
      remove((Component) this.scrollFrame);
    }

    remove((Component) this.gamePanel);
    remove((Component) this.actionPanel);
    remove((Component) this.textPanel);
    getContentPane().add((Component) this.optionPanel);
    setLocationRelativeTo(null);
    pack();
    setVisible(true);

  }

  @Override
  public void startGamePanel() {

    remove((Component) this.optionPanel);
    this.scrollFrame = new JScrollPane((Component) gamePanel);
    this.scrollFrame.setPreferredSize(new Dimension(600, 600));
    getContentPane().add(scrollFrame, BorderLayout.EAST);
    getContentPane().add((Component) this.actionPanel, BorderLayout.WEST);
    pack();

  }

  @Override
  public void startTextPanel() {
    remove((Component) this.optionPanel);
    getContentPane().add((Component) this.textPanel);
    setLocationRelativeTo(null);
    pack();

  }

  @Override
  public void addListener(DungeonGUIController c) {
    this.optionPanel.addListener(c);
    this.actionPanel.addListener(c);
    this.battleFrame.addListener(c);

    addKeyListener(c);
    addMenuListenler(c);

  }

  private void addKeyListener(DungeonGUIController c) {
    this.setFocusable(true);
    this.requestFocus();
    this.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
          direction = Direction.NORTH;
          c.shoot(direction, distance);
        } else if (keyCode == KeyEvent.VK_DOWN) {
          direction = Direction.SOUTH;
          c.shoot(direction, distance);

        } else if (keyCode == KeyEvent.VK_RIGHT) {
          direction = Direction.EAST;
          c.shoot(direction, distance);
        } else if (keyCode == KeyEvent.VK_LEFT) {
          direction = Direction.WEST;
          c.shoot(direction, distance);

        } else if (keyCode == KeyEvent.VK_1) {
          distance = "1";
          c.shoot(direction, distance);
        } else if (keyCode == KeyEvent.VK_2) {
          distance = "2";
          c.shoot(direction, distance);

        } else if (keyCode == KeyEvent.VK_3) {
          distance = "3";
          c.shoot(direction, distance);
        } else if (keyCode == KeyEvent.VK_W) {

          c.move(Direction.WEST);
        } else if (keyCode == KeyEvent.VK_S) {

          c.move(Direction.SOUTH);
        } else if (keyCode == KeyEvent.VK_E) {

          c.move(Direction.EAST);
        } else if (keyCode == KeyEvent.VK_N) {

          c.move(Direction.NORTH);
        } else if (keyCode == KeyEvent.VK_P) {

          c.pick();
        }

      }
    });

  }

  @Override
  public void setStatus(String[][] status, int curRow, int curCol) {
    this.gamePanel.setStatus(status, curRow, curCol);
    pack();

  }

  @Override
  public void setGameLog(String log) {
    this.gamePanel.setGameLog(log);
    this.textPanel.setGameLog(log);
    this.battleFrame.setGameLog(log);

  }

  @Override
  public void setOtyugh(String[][] otyughs) {

    this.gamePanel.setOtyugh(otyughs);

  }

  @Override
  public void showHint() {
    this.gamePanel.showHint();

  }

  @Override
  public void gameOver(boolean won) {
    JOptionPane pane = new JOptionPane();
    if (won) {

      int dialogResult = JOptionPane.showConfirmDialog(pane,
          "You won! Would you like to play again?", "Congradulation!", JOptionPane.YES_NO_OPTION);

      if (dialogResult == JOptionPane.YES_OPTION) {
        startOptionPanel();
      } else {
        System.exit(0);
      }

    } else {
      int dialogResult = JOptionPane.showConfirmDialog(pane,
          "You were eaten by the otyugh! Would you like to play again?", "Game Over!",
          JOptionPane.YES_NO_OPTION);

      if (dialogResult == JOptionPane.YES_OPTION) {
        startOptionPanel();
      } else {
        System.exit(0);
      }
    }

  }

  @Override
  public void startBattlePanle() {

    this.battleFrame.start();

  }

  @Override
  public void finishBattle() {
    this.gamePanel.setGameLog("<html>You won a battle!<br/></html>");
    this.battleFrame.close();

  }

}
