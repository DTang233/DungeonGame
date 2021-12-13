package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.DungeonGUIController;
import location.Direction;

/**
 * The class extends JPanel and implements ActionPanel interface.
 */
public class ActionPanelImpl extends JPanel implements ActionPanel {

  private static final long serialVersionUID = 2957356655682895256L;
  private JButton north;
  private JButton south;
  private JButton west;
  private JButton east;
  private JButton shootW;
  private JButton shootE;
  private JButton shootS;
  private JButton shootN;
  private JButton shoot;
  private JButton pick;

  private JLabel label1;
  private JLabel label2;
  private JLabel label3;

  private Direction direction;
  private String distance;
  private JComboBox<String> box;

  /**
   * Constructs a action panel.
   */
  public ActionPanelImpl() {
    setPreferredSize(new Dimension(300, 500));
    setLayout(new GridBagLayout());

    this.north = new JButton("North");
    this.south = new JButton("South");
    this.west = new JButton("West");
    this.east = new JButton("East");

    this.shootW = new JButton("←");
    this.shootE = new JButton("→");
    this.shootN = new JButton("↑");
    this.shootS = new JButton("↓");
    this.shoot = new JButton("Shoot");

    this.pick = new JButton("Pick");

    this.label1 = new JLabel("MOVE:");
    this.label2 = new JLabel("SHOOT:");
    this.label3 = new JLabel("PICKUP:");

    this.direction = null;
    this.distance = null;

    String[] dist = { "distance", "1", "2", "3" };
    this.box = new JComboBox<>(dist);
    initLayout();

  }

  private void initLayout() {

    label1.setForeground(Color.blue);
    label1.setFont(getFont().deriveFont(Font.BOLD, 14f));
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 1;
    c.gridy = 0;
    add(label1, c);

    c.gridx = 1;
    c.gridy = 1;
    add(north, c);
    c.gridx = 1;
    c.gridy = 2;
    add(south, c);
    c.gridx = 0;
    c.gridy = 2;
    add(west, c);
    c.gridx = 2;
    c.gridy = 2;
    add(east, c);

    label2.setForeground(Color.blue);
    label2.setFont(getFont().deriveFont(Font.BOLD, 14f));
    c.gridx = 1;
    c.gridy = 3;
    add(label2, c);

    c.gridx = 1;
    c.gridy = 4;
    add(shootN, c);

    c.gridx = 1;
    c.gridy = 5;
    add(shootS, c);

    c.gridx = 0;
    c.gridy = 5;
    add(shootW, c);

    c.gridx = 2;
    c.gridy = 5;
    add(shootE, c);

    c.gridx = 1;
    c.gridy = 6;
    add(this.box, c);

    c.gridx = 1;
    c.gridy = 7;
    add(this.shoot, c);

    label3.setForeground(Color.blue);
    label3.setFont(getFont().deriveFont(Font.BOLD, 14f));
    c.gridx = 1;
    c.gridy = 8;
    add(label3, c);

    c.gridx = 1;
    c.gridy = 9;
    add(pick, c);

  }

  @Override
  public void addListener(DungeonGUIController c) {

    this.north.addActionListener(l -> c.move(Direction.NORTH));
    this.south.addActionListener(l -> c.move(Direction.SOUTH));
    this.west.addActionListener(l -> c.move(Direction.WEST));
    this.east.addActionListener(l -> c.move(Direction.EAST));

    this.shootW.addActionListener(l -> setDirection(Direction.WEST));
    this.shootE.addActionListener(l -> setDirection(Direction.EAST));
    this.shootS.addActionListener(l -> setDirection(Direction.SOUTH));
    this.shootN.addActionListener(l -> setDirection(Direction.NORTH));

    this.box.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        String selected = box.getSelectedItem().toString();
        distance = selected;
      }
    });

    this.shoot.addActionListener(l -> handelShoot(c));

    this.pick.addActionListener(l -> c.pick());

  }

  private void setDirection(Direction dir) {
    this.direction = dir;
  }

  private void handelShoot(DungeonGUIController c) {

    c.shoot(this.direction, this.distance);
  }

}
