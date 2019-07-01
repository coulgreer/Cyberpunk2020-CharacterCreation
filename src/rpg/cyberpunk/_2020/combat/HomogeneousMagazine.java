package rpg.cyberpunk._2020.combat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;

public class HomogeneousMagazine implements AmmunitionContainer {
  private static final long serialVersionUID = 1L;

  private String ammunitionType;
  private int capacity;
  private Deque<Ammunition> ammunition;

  public HomogeneousMagazine(String ammunitionType, int capacity) {
    ammunition = new ArrayDeque<>();
    setAmmunitionType(ammunitionType);
    setAmmunitionCapacity(capacity);
  }

  private void setAmmunitionType(String ammunitionType) {
    if (ammunitionType == null) {
      throw new NullPointerException();
    } else {
      this.ammunitionType = ammunitionType;
    }
  }

  private void setAmmunitionCapacity(int capacity) {
    if (capacity < EMPTY) {
      throw new IllegalArgumentException("capacity = " + capacity + "; min capacity = " + EMPTY);
    } else {
      this.capacity = capacity;
    }
  }

  @Override
  public List<Ammunition> withdrawAmmunition(int ammunitionCount) {
    List<Ammunition> withdrawnAmmunition = new ArrayList<Ammunition>();

    for (int i = 0; i < ammunitionCount || getAmmunitionCount() > EMPTY; i++) {
      withdrawnAmmunition.add(ammunition.removeFirst());
    }

    return withdrawnAmmunition;
  }

  @Override
  public List<Ammunition> depositAmmunition(List<Ammunition> cartridges) {
    List<Ammunition> spareAmmunition = new ArrayList<Ammunition>();

    for (Ammunition ammo : cartridges) {
      if ((isEmpty() && isCompatibleAmmunitionType(ammo))
          || (!isEmpty() && isCompatibleAmmunition(ammo))) {
        ammunition.addFirst(ammo);
      } else {
        spareAmmunition.add(ammo);
      }
    }

    return spareAmmunition;
  }

  private boolean isCompatibleAmmunitionType(Ammunition cartridge) {
    if (cartridge == null) {
      return false;
    }

    return (cartridge.getAmmunitionType()).equals(getAmmunitionType());
  }

  private boolean isCompatibleAmmunition(Ammunition cartridge) {
    if (cartridge == null) {
      return false;
    }

    return cartridge.equals(getAmmunition());
  }

  @Override
  public String getAmmunitionType() {
    return ammunitionType;
  }

  @Override
  public Ammunition getAmmunition() {
    return ammunition.peekFirst();
  }

  @Override
  public int getAmmunitionCount() {
    return ammunition.size();
  }

  @Override
  public int getAmmunitionCapacity() {
    return capacity;
  }

  @Override
  public boolean isEmpty() {
    return getAmmunitionCount() == AmmunitionContainer.EMPTY;
  }

  @Override
  public boolean isFull() {
    return getAmmunitionCount() == capacity;
  }
}
