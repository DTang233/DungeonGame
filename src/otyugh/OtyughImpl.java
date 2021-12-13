package otyugh;

/**
 * The class implements Otyugh interface.
 */
public class OtyughImpl implements Otyugh {
  private int health;
  private double probalilityToEscape;

  /**
   * Constructs an Otyugh.
   */
  public OtyughImpl() {
    this.health = 2;
    this.probalilityToEscape = 0;
  }

  @Override
  public int getHealth() {

    return this.health;
  }

  @Override
  public void reduceHealth() {
    if (this.health > 0) {
      this.health -= 1;
      this.probalilityToEscape += 0.5;
    }

  }

  @Override
  public double getProbalilityToEscape() {

    return this.probalilityToEscape;
  }

}
