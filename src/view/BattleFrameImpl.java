package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.DungeonGUIController;

/**
 * The class extends JFrame and implements BattleFrame interface.
 */
public class BattleFrameImpl extends JFrame implements BattleFrame {

  private static final long serialVersionUID = -6974985135447358673L;
  private JButton attack;
  private JLabel log;

  /**
   * Create a BattleFrame.
   */
  public BattleFrameImpl() {
    setBounds(500, 500, 400, 400);
    initLayout();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    pack();

  }

  private void initLayout() {

    Panel panel = new Panel();
    panel.setPreferredSize(new Dimension(300, 300));
    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    JLabel hero = new JLabel();
    ImageIcon icon = new ImageIcon("./img/hero.png");
    hero.setIcon(icon);

    c.gridx = 1;
    c.gridy = 0;
    panel.add(hero, c);

    c.gridx = 3;
    c.gridy = 0;
    JLabel otyugh = new JLabel();
    ImageIcon icon2 = new ImageIcon("./img/otyugh.png");
    otyugh.setIcon(icon2);
    panel.add(otyugh, c);

    c.gridx = 2;
    c.gridy = 1;

    this.log = new JLabel();
    log.setForeground(Color.RED);

    panel.add(log, c);

    c.gridx = 2;
    c.gridy = 2;
    this.attack = new JButton("Attack");
    panel.add(this.attack, c);
    add(panel);

  }

  @Override
  public void addListener(DungeonGUIController c) {
    this.attack.addActionListener(l -> c.attack());

  }

  @Override
  public void setGameLog(String log) {

    this.log.setText(log);

  }

  @Override
  public void start() {
    log.setText(
        "<html>Start a Battle!<br/>Your health: 2/2 <br/> Otyugh's health: 2/2<br/></html>");
    setVisible(true);

  }

  @Override
  public void close() {
    setVisible(false);

  }

}
