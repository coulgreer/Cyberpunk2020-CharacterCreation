package rpg.cyberpunk._2020.combat;

import java.util.Set;
import rpg.general.combat.AmmunitionContainer;
import rpg.util.Die;
import rpg.util.Measurement;
import rpg.util.Probability;

/**
 * A Firearm that uses its own damage instead of the damage from its ammunition.
 * 
 * @author Coul Greer
 */
public class ExoticFirearm extends Firearm {
  private static final long serialVersionUID = 1L;

  private Probability damage;

  /**
   * Constructs a Firearm that uses the given damage instead of the damage from its ammunition.
   * 
   * @param weaponName the name used to identify this weapon
   * @param description a blurb used to give an idea of what this weapon is/can do
   * @param weaponType the category of weapon that this weapon belongs to
   * @param weaponAccuracy the flat modifier to accuracy based solely on the weapon
   * @param concealability the rating representing the ability to hide this weapon
   * @param availability the rating representing how easy this weapon is to obtain
   * @param ammunitionContainer the holder of the ammunition put into this weapon as well as giving
   *        the maximum capacity
   * @param rateOfFire the amount of attacks that can be made in one turn with this weapon
   * @param reliability the rating representing the probability of a weapon jamming or
   *        malfunctioning
   * @param rangeModifier the average distance that this weapon can make an attack without suffering
   *        penalties to accuracy
   * @param cost the amount of money this weapon is worth
   * @param weight the heaviness of this weapon
   * @param attachmentPoints a set of points where modifiers can be attached to this weapon
   * 
   * @see rpg.cyberpunk._2020.combat.Firearm#Firearm(String, String, String, String, int,
   *      Concealability, Availability, AmmunitionContainer, int, Reliability, Measurement, double,
   *      Measurement, Set)
   */
  public ExoticFirearm(String weaponName, String description, String weaponType, String skillName,
      int weaponAccuracy, Concealability concealability, Availability availability,
      Probability damage, AmmunitionContainer ammunitionContainer, int rateOfFire,
      Reliability reliability, Measurement rangeModifier, double cost, Measurement weight,
      Set<String> attachmentPoints) {
    super(weaponName, description, weaponType, skillName, weaponAccuracy, concealability,
        availability, ammunitionContainer, rateOfFire, reliability, rangeModifier, cost, weight,
        attachmentPoints);
    this.damage = damage;
  }

  @Override
  public int getDamageModifier() {
    return damage.getModifier();
  }

  @Override
  public Die getDamageDice() {
    return damage.getDice();
  }

}
