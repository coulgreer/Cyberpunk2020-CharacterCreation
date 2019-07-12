package rpg.general.combat;

import java.util.List;
import rpg.general.combat.Ammunition.Type;

/**
 * Allows an object to store and use ammunition while also getting the amount of ammunition that can
 * be -- and is -- stored within.
 */
public interface Shootable extends Weaponizable {
  /**
   * Allows this <code>Shootable</code> to discharge/remove ammunition from itself. It is
   * recommended that <code>numberOfShots</code> should be a positive value, and any addition of
   * ammunition should be handled by <code>reload</code>.
   * 
   * @param numberOfShots the amount of ammunition/shots/etc. spent
   * @return <code>true</code> if any ammunition has been discharged
   */
  public boolean attack(int numberOfShots);

  /**
   * Allows ammunition to be transfered into a shootable instance.
   * 
   * @param ammunition the list of ammunition to store
   * @return the ammunition leftover
   */
  public List<Ammunition> reload(List<Ammunition> ammunition);

  /**
   * @return the current amount of ammunition stored
   */
  public int getAmmunitionCount();

  /**
   * @return the maximum amount of ammunition that can be stored
   */
  public int getAmmunitionCapacity();

  /**
   * @return the number of attacks that can be made per round without penalty
   */
  public int getRateOfFire();

  /**
   * Gets the type of ammunition that can be stored. Some examples would be 5mm, .50cal, shotgun
   * shell, arrow, etc.
   * <p>
   * For example, assume that a .50cal cartridge would not fit in a 9mm magazine or in a receiver
   * that fits a shotgun shell.
   * 
   * @return the type of ammunition that is stored
   */
  public Type getAmmunitionType();
}
