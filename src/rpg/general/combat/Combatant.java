package rpg.general.combat;

import java.io.Serializable;
import rpg.util.Measurement;
import rpg.util.Probability;

/**
 * An interface that is the actor in combat. Used to get combat statistics such as range, attack,
 * and damage modifiers. Also, calculates the scores for range, attack, and damage.
 *
 * @param <W> the type of Weapon this combatant can calculate
 */
public interface Combatant<W extends Weapon> extends Serializable {
  /**
   * Returns a modifier value based on information provided by an instance of <code>Weapon</code>.
   * The value represents the ability to land an attack on a target excluding modifiers from the
   * weapon.
   * 
   * @param weapon the source of information used to derive the attack modifier
   * @return the modifier value to be used in calculation of the attack score
   */
  public int getAttackModifier(W weapon);

  /**
   * Returns a modifier value based on information provided by an instance of <code>Weapon</code>.
   * The returned value represents the flat amount of damage dealt.
   * 
   * @param weapon the source of information used to derive the damage modifier
   * @return the modifier value to be used in the calculation of the damage score
   */
  public int getDamageModifier(W weapon);

  /**
   * Returns a modifier value based on information provided by an instance <code>Weapon</code>. The
   * returned value represents the additional range that a weapon can make an attack at. Usually,
   * thrown weapons are the only weapons that return a non-zero value.
   * 
   * @param weapon the source of information used to decide if a weapon is suited for applying a
   *        non-zero value
   * @return the modifier value to be used in the calculation of the range score
   */
  public Measurement getRangeModifier(W weapon);

  /**
   * Returns the total chance of landing an attack on a target. This is based off the
   * <code>Weapon</code> instance, and this includes the die and the guaranteed score to land the
   * attack.
   * 
   * @param weapon the source of information used to derive the chance to land an attack combined
   *        with the attack score
   * @return the chance to land an attack with the given weapon
   */
  public Probability getTotalAttackChance(W weapon);

  /**
   * Returns the total probability of damage dealt to a target. This includes the die and the
   * guaranteed score to deal damage.
   * 
   * @param weapon the source of information used to derive the probability of damage combined with
   *        the damage score
   * @return the damage chance of the <code>Weapon</code>
   */
  public Probability getTotalDamageChance(W weapon);

  /**
   * Returns the optimal range that a <code>Weapon</code> can make an attack at.
   * 
   * @param weapon the source of information used to derive the optimal range of attack
   * @return the optimal range an attack
   */
  public Measurement getRangeScore(W weapon);
}
