package rpg.general.combat;

import rpg.util.Measurement;

/**
 * Allows an object to be used as a weapon in the sense of displaying how accurate an object is to
 * hit its intended target, how much damage the object does, and how far the object can attack on
 * its own.
 */
public interface Weaponizable {
  /**
   * @return a number used to boost base hit probability
   */
  public int getAttackModifier();

  /**
   * @return a number used to boost base damage probability
   */
  public int getDamageModifier();

  /**
   * @return a flat measurement used to boost base range
   */
  public Measurement getRangeModifier();
}
