package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;


/**
 * The class extends JPanel and implements GamePanel interface.
 */
public class GamePanelImpl extends JPanel implements GamePanel {

  private static final long serialVersionUID = 7791882380722637518L;
  private String[][] status;
  private String[][] otyughs;
  private int curRow;
  private int curCol;
  private Panel panel;
  private JLabel log;
  private boolean hint;

  /**
   * Create a game panel.
   */
  public GamePanelImpl() {

    setPreferredSize(new Dimension(650, 650));
    this.panel = new Panel();
    this.panel.setPreferredSize(new Dimension(200, 200));
    add(this.panel);

    this.status = null;
    this.otyughs = null;
    this.hint = false;

    initLog();

    this.curRow = 0;
    this.curCol = 0;

  }

  private void initLog() {
    Panel description = new Panel();
    description.setPreferredSize(new Dimension(200, 200));
    add(description, BorderLayout.SOUTH);
    this.log = new JLabel();
    log.setForeground(Color.RED);
    log.setText("Start a new game!");
    description.add(log);

  }

  private void drawDungeon(String[][] status) {
    remove(panel);
    int row = status.length;
    int col = status[0].length;

    panel = new Panel();

    // 64x64 is the size of icon
    panel.setPreferredSize(new Dimension(64 * col, 64 * row));
    panel.setLayout(new GridLayout(row, col));

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {

        if (this.hint && this.otyughs[i][j] != null) {
          String name = this.otyughs[i][j];
          JLabel node = new JLabel();
          ImageIcon icon = new ImageIcon("./img/" + name + ".png");
          node.setIcon(icon);

          panel.add(node);
          continue;
        }

        if (this.status[i][j] != null) {

          if (this.curRow == i && this.curCol == j) {

            String name = this.status[i][j];

            JLabel label1 = new JLabel(new ImageIcon("./img/" + name + ".png"));

            LayoutManager overlay = new OverlayLayout(label1);
            label1.setLayout(overlay);

            JLabel label2 = new JLabel(new ImageIcon("./img/hero.png"));

            label2.setAlignmentX(0.5f);
            label2.setAlignmentY(0.5f);
            label1.add(label2);

            panel.add(label1);

          } else {
            String name = this.status[i][j];

            JLabel node = new JLabel();
            ImageIcon icon = new ImageIcon("./img/" + name + ".png");
            node.setIcon(icon);

            panel.add(node);
          }

        } else {
          JLabel node = new JLabel();
          ImageIcon icon = new ImageIcon("./img/black.png");
          node.setIcon(icon);
          panel.add(node);

        }

      }
    }

    add(panel, BorderLayout.NORTH);

  }

  @Override
  public void setStatus(String[][] status, int curRow, int curCol) {
    this.status = status;
    this.curCol = curCol;
    this.curRow = curRow;

    drawDungeon(status);

  }

  @Override
  public void setGameLog(String log) {
    this.log.setText(log);
  }

  @Override
  public void setOtyugh(String[][] otyughs) {
    this.otyughs = otyughs;

  }

  @Override
  public void showHint() {

    this.hint = !this.hint;

  }

}
