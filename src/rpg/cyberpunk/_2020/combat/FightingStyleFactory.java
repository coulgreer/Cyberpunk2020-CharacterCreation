package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.util.Die;
import rpg.util.Measurement;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * An interface for creating a factory that creates a <code>CyberpunkWeapon</code> for each attack
 * of a given style.
 * 
 * @author Coul Greer
 */
public interface FightingStyleFactory {
  /**
   * The constant concealability used for all weapons created.
   */
  public static final Concealability CONCEALABILITY = Concealability.POCKET;

  /**
   * The constant availability used for all weapons created.
   */
  public static final Availability AVAILABILITY = Availability.EXCELLENT;

  /**
   * The constant reliability used for all weapons created.
   */
  public static final Reliability RELIABILITY = Reliability.VERY_RELIABLE;

  /**
   * The damage probability for all Strike attacks made by a weapon.
   */
  public static final Probability STRIKE_DAMAGE = new Probability(new Die(1, 6, 2), 0);

  /**
   * The damage probability for all Kick attacks made by a weapon.
   */
  public static final Probability KICK_DAMAGE = new Probability(new Die(1, 6), 0);

  /**
   * The damage probability for all Block actions made by a weapon.
   */
  public static final Probability BLOCK_DAMAGE = NullProbability.getInstance();

  /**
   * The damage probability for all Dodge actions made by a weapon.
   */
  public static final Probability DODGE_DAMAGE = NullProbability.getInstance();

  /**
   * The damage probability for all Disarm attacks made by a weapon.
   */
  public static final Probability DISARM_DAMAGE = NullProbability.getInstance();

  /**
   * The damage probability for all Throw attacks made by a weapon.
   */
  public static final Probability THROW_DAMAGE = new Probability(new Die(1, 6), 0);

  /**
   * The damage probability for all Hold attacks made by a weapon.
   */
  public static final Probability HOLD_DAMAGE = NullProbability.getInstance();

  /**
   * The damage probability for all Escape actions made by a weapon.
   */
  public static final Probability ESCAPE_DAMAGE = NullProbability.getInstance();

  /**
   * The damage probability for all Choke attacks made by a weapon.
   */
  public static final Probability CHOKE_DAMAGE = new Probability(new Die(1, 6), 0);

  /**
   * The damage probability for all Sweep attacks made by a weapon.
   */
  public static final Probability SWEEP_DAMAGE = NullProbability.getInstance();

  /**
   * The damage probability for all Grapple attacks made by a weapon.
   */
  public static final Probability GRAPPLE_DAMAGE = NullProbability.getInstance();

  /**
   * The constant representing the range value of all weapons made.
   */
  public static final Measurement RANGE_MODIFIER = new Measurement( //
      Measurement.Type.LENGTH, //
      1.0, //
      Measurement.Unit.METER);

  /**
   * The constant value of the weapon when making a purchase.
   */
  public static final double COST = 0.0;

  /**
   * The constant heaviness of created weapons.
   */
  public static final Measurement WEIGHT = new Measurement( //
      Measurement.Type.MASS, //
      0.0, //
      Measurement.Unit.KILOGRAM);

  /**
   * Returns a weapon used to make Strike attacks.
   * 
   * @return a weapon used to make Strike attacks
   */
  public CyberpunkWeapon createStrike();

  /**
   * Returns a weapon used to make Kick attacks.
   * 
   * @return a weapon used to make Kick attacks
   */
  public CyberpunkWeapon createKick();

  /**
   * Returns a weapon used to make Block action.
   * 
   * @return a weapon used to make Block action
   */
  public CyberpunkWeapon createBlock();

  /**
   * Returns a weapon used to make Dodge action.
   * 
   * @return a weapon used to make Dodge action
   */
  public CyberpunkWeapon createDodge();

  /**
   * Returns a weapon used to make Disarm attacks.
   * 
   * @return a weapon used to make Disarm attacks
   */
  public CyberpunkWeapon createDisarm();

  /**
   * Returns a weapon used to make Throw attacks.
   * 
   * @return a weapon used to make Throw attacks
   */
  public CyberpunkWeapon createThrow();

  /**
   * Returns a weapon used to make Hold attacks.
   * 
   * @return a weapon used to make Hold attacks
   */
  public CyberpunkWeapon createHold();

  /**
   * Returns a weapon used to make Escape action.
   * 
   * @return a weapon used to make Escape action
   */
  public CyberpunkWeapon createEscape();

  /**
   * Returns a weapon used to make Choke attacks.
   * 
   * @return a weapon used to make Choke attacks
   */
  public CyberpunkWeapon createChoke();

  /**
   * Returns a weapon used to make Sweep attacks.
   * 
   * @return a weapon used to make Sweep attacks
   */
  public CyberpunkWeapon createSweep();

  /**
   * Returns a weapon used to make Grapple attacks.
   * 
   * @return a weapon used to make Grapple attacks
   */
  public CyberpunkWeapon createGrapple();
}
