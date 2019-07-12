package rpg.cyberpunk._2020.combat;

import java.util.Objects;
import org.apache.commons.text.WordUtils;
import rpg.general.combat.Ammunition.Type;
import rpg.general.combat.Weapon;
import rpg.util.Die;

/**
 * A weapon used specifically in the tabletop rpg Cyberpunk 2020 series. This weapon has special
 * properties such as: Reliability, Concealability, and Availablity. The reliability represents the
 * likelihood a weapon is to malfunction upon use. Concealability represents the ease in which a
 * weapon can be hidden. Availability represents the likelihood that a weapon will be found on the
 * market. These weapons can also be modified through the use of attachments as well.
 *
 */
public abstract class CyberpunkWeapon implements Weapon {

  /**
   * A collection of values used to represent the availability rating of a weapon.
   */
  public enum Availability {
    /**
     * The value representing that a weapon cannot be obtained.
     */
    UNAVAILABLE("N/A"),

    /**
     * The value representing that a weapon can be found almost anywhere.
     */
    EXCELLENT("E"),

    /**
     * The value representing that a weapon can be found in most sports & gun stores or on the
     * Street.
     */
    COMMON("C"),

    /**
     * The value representing that a weapon is a specialty weapon, can only be found on the black
     * market, or is stolen from the military.
     */
    POOR("P"),

    /**
     * The value representing that a weapon is stolen, one-of-a-kind, special military issue, or may
     * be highly illegal.
     */
    RARE("R");

    private String abbreviation;

    /**
     * Constructs a value that has an abbreviation for use when display space is limited.
     * 
     * @param abbreviation the String value that is used to shorten this ratings full value
     */
    Availability(String abbreviation) {
      this.abbreviation = abbreviation;
    }

    /**
     * Returns the shortened version of this rating.
     * 
     * @return the shortened version of this rating
     */
    public String getAbbreviation() {
      return abbreviation;
    }

    @Override
    public String toString() {
      String str = name();

      str = str.replace("_", " ");
      str = WordUtils.capitalizeFully(str);
      return str;
    }
  }

  /**
   * A collection of values representing the reliability of a weapon.
   */
  public enum Reliability {
    /**
     * The value representing that this weapon is highly unlikely to jam upon fumbling. About a 3 in
     * 10 chance of jamming upon a fumble.
     */
    VERY_RELIABLE("VR"),

    /**
     * The value representing that this weapon is somewhat likely to jam upon fumbling. About a 5 in
     * 10 chance of jamming upon a fumble.
     */
    STANDARD("ST"),

    /**
     * The value representing that this weapon is very likely to jam upon fumbling. About a 8 in 10
     * chance of jamming upon a fumble.
     */
    UNRELIABLE("UR");

    private String abbreviation;

    /**
     * Constructs a value that has an abbreviation for use when display space is limited.
     * 
     * @param abbreviation the String value that is used to shorten this ratings full value
     */
    Reliability(String abbreviation) {
      this.abbreviation = abbreviation;
    }

    /**
     * Returns the shortened version of this rating.
     * 
     * @return the shortened version of this rating
     */
    public String getAbbreviation() {
      return abbreviation;
    }

    @Override
    public String toString() {
      String str = name();

      str = str.replace("_", " ");
      str = WordUtils.capitalizeFully(str);
      return str;
    }
  }

  /**
   * A collection of values representing the concealability of a weapon.
   */
  public enum Concealability {
    /**
     * A value representing that a weapon can be hidden in a minimum of a pocket, pants leg, or
     * sleeve.
     */
    POCKET("P"),

    /**
     * A value representing that a weapon can be hidden in a minimum of a jacket, coat, or shoulder
     * rig.
     */
    JACKET("J"),

    /**
     * A value representing that a weapon can be hidden in a minimum of a long coat.
     */
    LONG_COAT("L"),

    /**
     * A value representing that a weapon cannot be hidden at all.
     */
    CANNOT_HIDE("N");

    private String abbreviation;

    /**
     * Constructs a value that has an abbreviation for use when display space is limited.
     * 
     * @param abbreviation the String value that is used to shorten this ratings full value
     */
    Concealability(String abbreviation) {
      this.abbreviation = abbreviation;
    }

    /**
     * Returns the shortened version of this rating.
     * 
     * @return the shortened version of this rating
     */
    public String getAbbreviation() {
      return abbreviation;
    }

    @Override
    public String toString() {
      String str = name();

      str = str.replace("_", " ");
      str = WordUtils.capitalizeFully(str);
      return str;
    }
  }

  public static final String WEAPON_TYPE_LIGHT_PISTOL = "Light Pistol";
  public static final String WEAPON_TYPE_MEDIUM_PISTOL = "Medium Pistol";
  public static final String WEAPON_TYPE_HEAVY_PISTOL = "Heavy Pistol";
  public static final String WEAPON_TYPE_VERY_HEAVY_PISTOL = "Very Heavy Pistol";
  public static final String WEAPON_TYPE_LIGHT_SUBMACHINEGUN = "Light Submachinegun";
  public static final String WEAPON_TYPE_MEDIUM_SUBMACHINEGUN = "Medium Submachinegun";
  public static final String WEAPON_TYPE_HEAVY_SUBMACHINEGUN = "Heavy Submachinegun";
  public static final String WEAPON_TYPE_RIFLE = "Rifle";
  public static final String WEAPON_TYPE_SHOTGUN = "Shotgun";
  public static final String WEAPON_TYPE_HEAVY_WEAPON = "Heavy Weapon";
  public static final String WEAPON_TYPE_MELEE_WEAPON = "Melee Weapon";
  public static final String WEAPON_TYPE_BOW = "Bow";
  public static final String WEAPON_TYPE_THROWN = "Thrown";
  public static final String WEAPON_TYPE_EXOTIC = "Exotic Weapon";
  public static final String WEAPON_TYPE_UNARMED = "Unarmed";

  public static final Type CARTRIDGE = new Type("Cartridge");
  public static final Type SHELL = new Type("Shell");
  public static final Type ARROW = new Type("Arrow");
  public static final Type QUARREL = new Type("Quarrel");
  public static final Type NEEDLE = new Type("Needle");
  public static final Type PELLET = new Type("Pellet");
  public static final Type GRENADE = new Type("Grenade");
  public static final Type CANISTER = new Type("Canister");
  public static final Type ENERGY = new Type("Energy");

  private static final long serialVersionUID = 1L;

  private final Die hitChance;

  protected CyberpunkWeapon() {
    this.hitChance = new Die(1, 10);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof CyberpunkWeapon)) {
      return false;
    }

    CyberpunkWeapon weapon = (CyberpunkWeapon) o;
    return getName().equals(weapon.getName()) && getWeaponType().equals(weapon.getWeaponType())
        && (weapon.getAmmunitionCount() == getAmmunitionCount())
        && (weapon.getAmmunitionCapacity() == getAmmunitionCapacity());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getWeaponType(), getAmmunitionCount(), getAmmunitionCapacity());
  }

  @Override
  public String toString() {
    return getName() + ": << " //
        + "<hit:" + hitChance + "+" + getAttackModifier() + "> " //
        + "<damage: " + getDamageDice() + "+" + getDamageModifier() + "> >>";
  }

  @Override
  public Die getHitDice() {
    return hitChance;
  }

  /**
   * Returns the rating for how hard this weapon is to hide.
   * 
   * @return the rating for how hard this weapon is to hide
   */
  public abstract Concealability getConcealability();

  /**
   * Returns the rating of how easy this weapon is to find on the open market.
   * 
   * @return the rating of how easy this weapon is to find on the open market
   */
  public abstract Availability getAvailability();

  /**
   * Returns the rating of how likely a weapon is to jam on a fumble in combat.
   * 
   * @return the rating of how likely a weapon is to jam on a fumble in combat
   */
  public abstract Reliability getReliability();
}
