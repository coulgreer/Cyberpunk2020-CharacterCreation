package rpg.general.combat;

import java.io.Serializable;
import java.util.List;
import rpg.general.combat.Ammunition.Type;

/**
 * An interface that handles Ammunition when used in the context of being inside a weapon. Allows
 * the return of the current and maximum amount of ammunition that can be held within a weapon.
 * Also, handles the deposit and withdraw of ammunition from an instance of this interface.
 */
public interface AmmunitionContainer extends Serializable {
  /**
   * A constant value representing that an AmmunitionContainer has no ammunition present.
   */
  public static final int EMPTY = 0;

  /**
   * Removes ammunition from this container as a list.
   * 
   * @param ammunitionCount the amount of <code>Ammunition</code> to remove from this container
   * @return a list of ammunition that was stored in this container
   */
  public List<Ammunition> withdrawAmmunition(int ammunitionCount);

  /**
   * Adds ammunition to this container returning the remaining ammunition that exceeds this
   * container's capacity
   * 
   * @param ammuntion the list of ammunition to be stored and managed
   * @return the ammunition remaining ammunition not put inside the container
   */
  public List<Ammunition> depositAmmunition(List<Ammunition> ammunition);

  /**
   * @return an ammunition currently stored and is next to be withdrawn
   */
  public Ammunition getAmmunition();

  /**
   * @return the current amount of ammunition stored
   */
  public int getAmmunitionCount();

  /**
   * @return the max amount of ammunition that can be stored
   */
  public int getAmmunitionCapacity();

  /**
   * Gets the type of ammunition that can be stored.
   * <p>
   * Some examples would be 5mm, .50cal, shotgun shell, arrow, etc. For example, assume that a
   * .50cal cartridge would not fit in a 9mm magazine or in a receiver that fits a shotgun shell.
   * 
   * @return the type of ammunition that is stored
   */
  public Type getAmmunitionType();

  /**
   * @return <code>true</code>, if no ammunition is being stored
   */
  public boolean isEmpty();

  /**
   * @return <code>true</code>, if the capacity has been reached for this container
   */
  public boolean isFull();
}
