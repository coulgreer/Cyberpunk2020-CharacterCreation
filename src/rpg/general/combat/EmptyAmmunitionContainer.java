package rpg.general.combat;

import java.util.List;
import rpg.general.combat.Ammunition.Type;

/**
 * An instance of AmmunitionContainer that models itself after the Null Object pattern. There is no
 * unique instance, however, this is still a do-nothing AmmunitionContainer used for weapons that
 * cannot hold ammunition.
 */
public class EmptyAmmunitionContainer implements AmmunitionContainer {
  private static final long serialVersionUID = 1L;

  /**
   * @throws UnsupportedOperationException because no ammunition can be contained within
   */
  @Override
  public List<Ammunition> withdrawAmmunition(int ammunitionCount) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Ammunition> depositAmmunition(List<Ammunition> ammunition) {
    return ammunition;
  }

  @Override
  public Ammunition getAmmunition() {
    return null;
  }

  @Override
  public int getAmmunitionCount() {
    return AmmunitionContainer.EMPTY;
  }

  @Override
  public int getAmmunitionCapacity() {
    return AmmunitionContainer.EMPTY;
  }

  @Override
  public Type getAmmunitionType() {
    return Weapon.NO_AMMUNITION_TYPE;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public boolean isFull() {
    return true;
  }

}
