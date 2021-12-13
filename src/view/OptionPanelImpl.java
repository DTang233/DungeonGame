package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.DungeonGUIController;

/**
 * The class extends JPanel and implements OptionPanel interface.
 */
public class OptionPanelImpl extends JPanel implements OptionPanel {

  private static final long serialVersionUID = 1L;
  GridBagConstraints constraints = null;

  private JLabel rowLable;
  private JLabel colLable;
  private JLabel interconnectivityLable;
  private JLabel isWrappedLable1;
  private JLabel isWrappedLable2;
  private JLabel percentageLable;
  private JLabel difficultyLable;
  private JLabel modeLable;

  private Map<String, String> optionMap;
  String notation;

  /**
   * Create a OptionPanel.
   */
  public OptionPanelImpl() {

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.notation = null;

    String[] rowOption = { "3", "4", "5", "6", "7", "8", "9", "10" };
    String[] colOption = { "6", "3", "4", "5", "6", "7", "8", "9", "10" };
    String[] interconnectivity = { "0", "1", "2", "3", "4", "5" };
    String[] isWrapped = { "true", "false" };
    String[] percentage = { "20", "30", "40", "50", "60", "70", "80", "90" };
    String[] difficulty = { "1", "2", "3", "4", "5", "6" };
    String[] graphicalMode = { "true", "false" };

    this.optionMap = new HashMap<String, String>();

    // create labels
    this.rowLable = new JLabel("Number of row");
    this.optionMap.put(rowLable.getText(), "3");

    this.colLable = new JLabel("Number of column");
    this.optionMap.put(colLable.getText(), "6");

    this.interconnectivityLable = new JLabel("Number of interconnectivity");
    this.optionMap.put(interconnectivityLable.getText(), "0");

    this.isWrappedLable1 = new JLabel("Is wrapped");
    this.isWrappedLable2 = new JLabel(
        "Row and colunmn cannot be both smaller than 6 when it's wrapped");
    this.optionMap.put(isWrappedLable1.getText(), "true");

    this.percentageLable = new JLabel("Percentage of treasure");
    this.optionMap.put(percentageLable.getText(), "20");

    this.difficultyLable = new JLabel("Difficulty");
    this.optionMap.put(difficultyLable.getText(), "1");

    this.modeLable = new JLabel("Graphical mode");
    this.optionMap.put(modeLable.getText(), "true");

    initOptions(rowOption, this.rowLable);
    initOptions(colOption, this.colLable);
    initOptions(interconnectivity, this.interconnectivityLable);
    initOptions(isWrapped, this.isWrappedLable1);
    initOptions(percentage, this.percentageLable);
    initOptions(difficulty, this.difficultyLable);
    initOptions(graphicalMode, this.modeLable);

  }

  private void initOptions(String[] rowOpt, JLabel label) {
    label.setForeground(Color.blue);

    label.setFont(getFont().deriveFont(Font.BOLD, 14f));
    add(label);

    JComboBox<String> box = new JComboBox<>(rowOpt);
    box.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        String selected = box.getSelectedItem().toString();

        if (selected.equals("false")) {
          isWrappedLable2
              .setText("Row and colunmn cannot be both smaller than 10 when it's unwrapped");
        } else if (selected.equals("true")) {
          isWrappedLable2
              .setText("Row and colunmn cannot be both smaller than 6 when it's wrapped");

        }

        optionMap.put(label.getText(), selected);
      }
    });

    add(box);
    isWrappedLable2.setForeground(Color.red);
    add(isWrappedLable2);

  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(500, 400);
  }

  @Override
  public void addListener(DungeonGUIController c) {
    JButton start = new JButton("START");

    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        c.initDungeon(getRow(), getCol(), getInterconnectivity(), getIsWrapped(), getPercentage(),
            getDifficulty(), getMode());

      }
    });

    add(start);

  }

  private int getRow() {
    int num = -1;
    String res = this.optionMap.get(this.rowLable.getText());
    try {
      num = Integer.parseInt(res);

    } catch (NumberFormatException e) {

      return -1;
    }
    return num;
  }

  private int getCol() {
    int num = -1;
    String res = this.optionMap.get(this.colLable.getText());
    try {
      num = Integer.parseInt(res);

    } catch (NumberFormatException e) {

      return -1;
    }
    return num;
  }

  private int getInterconnectivity() {

    return isNumeric(this.optionMap.get(this.interconnectivityLable.getText()));
  }

  private boolean getIsWrapped() {
    String bool = this.optionMap.get(this.isWrappedLable1.getText());

    boolean res = bool.equals("true") ? true : false;

    return res;
  }

  private int getPercentage() {

    return isNumeric(this.optionMap.get(this.percentageLable.getText()));
  }

  private int getDifficulty() {

    return isNumeric(this.optionMap.get(this.difficultyLable.getText()));
  }

  private boolean getMode() {
    String bool = this.optionMap.get(this.modeLable.getText());

    boolean res = bool.equals("true") ? true : false;

    return res;
  }

  private int isNumeric(String in) {

    int num = -1;
    try {
      num = Integer.parseInt(in);

    } catch (NumberFormatException e) {

      return -1;
    }
    return num;
  }

}
