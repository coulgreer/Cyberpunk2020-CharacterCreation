package rpg.general.combat;

import rpg.cyberpunk._2020.combat.Magazine;
import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.general.commerce.Product;

public abstract class Weapon extends Product implements Weaponizable, Equipable {
	private String weaponType;
	private String skillName;
	private int hitModifier;
	private int damageModifier;
	private int rangeModifier;

	public Weapon(String name, String description, String weaponType, String skillName, int hitModifier,
			int damageModifier, int rangeModifier, double cost, double weight) {
		super(name, description, cost, weight);
		this.weaponType = weaponType;
		this.skillName = skillName;
		this.hitModifier = hitModifier;
		this.damageModifier = damageModifier;
		this.rangeModifier = rangeModifier;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public String getSkillName() {
		return skillName;
	}

	public int getHitModifier() {
		return hitModifier;
	}

	public int getDamageModifier() {
		return damageModifier;
	}

	public int getRangeModifier() {
		return rangeModifier;
	}

	public abstract boolean fire(int shotsFired);

	public abstract Magazine reload(Magazine magazine);

	public abstract AmmoType getAmmoType();

	public abstract int getAmmoCapacity();

	public abstract int getAmmoCount();

	public abstract int getRateOfFire();
	
	public abstract WeaponModifier setModifier(WeaponModifier modifier);
	
	public abstract String getBonuses();
}
