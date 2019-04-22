package rpg.cyberpunk._2020.combat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;

public class HomogeneousMagazine implements AmmunitionContainer {
	private static final long serialVersionUID = 1L;

	private String ammunitionType;
	private int capacity;
	private Deque<Ammunition> ammunition;

	public HomogeneousMagazine(String ammunitionType, int capacity) {
		setAmmunitionType(ammunitionType);
		setAmmunitionCapacity(capacity);
		ammunition = new ArrayDeque<>();
	}

	private void setAmmunitionType(String ammunitionType) {
		if (ammunitionType != null) {
			this.ammunitionType = ammunitionType;
		} else {
			this.ammunitionType = Ammunition.NO_AMMUNITION_TYPE;
		}
	}

	private void setAmmunitionCapacity(int capacity) {
		if (capacity >= EMPTY) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException("The capcity value of Magazine is not allowed to be negative.");
		}
	}

	@Override
	public Ammunition withdrawAmmunition() {
		if (getAmmunitionCount() > EMPTY) {
			Ammunition cartridge = ammunition.removeFirst();
			return cartridge;
		} else {
			throw new NoSuchElementException(
					"This HomogeneousMagazine is empty, therefore, there are no cartridges to withdraw.");
		}
	}

	@Override
	public boolean depositAmmunition(Ammunition cartridge) {
		boolean hasDeposited = false;

		if (ammunition.size() > EMPTY) {
			if ((ammunition.peekFirst()).equals(cartridge)) {
				ammunition.addFirst(cartridge);
				hasDeposited = true;
			} else {
				hasDeposited = false;
			}
		} else {
			if (ammunitionType.equals(cartridge.getAmmunitionType())) {
				ammunition.addFirst(cartridge);
				hasDeposited = true;
			} else {
				hasDeposited = false;
			}
		}

		return hasDeposited;
	}

	@Override
	public List<Ammunition> transferAmmunitionFrom(AmmunitionContainer container) {
		List<Ammunition> remaining = new ArrayList<>();

		while (!container.isEmpty()) {
			if (this.isFull()) {
				remaining.add(container.withdrawAmmunition());
			} else {
				addAmmunitionToStack(remaining, container);
			}
		}

		return remaining;
	}

	private void addAmmunitionToStack(List<Ammunition> remaining, AmmunitionContainer newContainer) {
		if (!isEqualAmmunitionType(newContainer)) {
			remaining.add(newContainer.withdrawAmmunition());
		} else if (this.isEmpty()) {
			this.depositAmmunition(newContainer.withdrawAmmunition());
		} else if (!isEqualAmmunition(newContainer)) {
			while (!this.isEmpty()) {
				remaining.add(this.withdrawAmmunition());
			}
			this.depositAmmunition(newContainer.withdrawAmmunition());
		} else {
			this.depositAmmunition(newContainer.withdrawAmmunition());
		}
	}

	private boolean isEqualAmmunitionType(AmmunitionContainer container) {
		String oldAmmunitionType = this.getAmmunitionType();
		String newAmmunitionType = container.getAmmunitionType();

		return oldAmmunitionType.equals(newAmmunitionType);
	}

	private boolean isEqualAmmunition(AmmunitionContainer container) {
		Ammunition oldAmmunition = this.getAmmunition();
		Ammunition newAmmunition = container.getAmmunition();

		return oldAmmunition.equals(newAmmunition);
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
