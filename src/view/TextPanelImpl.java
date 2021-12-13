package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * The class extends JPanel and implements TextPanel interface.
 */
public class TextPanelImpl extends JPanel implements TextPanel {

  private static final long serialVersionUID = 5501895807847395957L;
  private JLabel log;

  /**
   * Create a TextPanel.
   */
  public TextPanelImpl() {
    setPreferredSize(new Dimension(400, 400));
    this.log = new JLabel();
    this.log.setForeground(Color.RED);
    this.log.setText("Start a new game!");
    add(log);
  }

  @Override
  public void setGameLog(String log) {
    this.log.setText(log);

  }

}
