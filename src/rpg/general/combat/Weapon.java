package rpg.general.combat;

import rpg.general.commerce.Product;

public abstract class Weapon implements Weaponizable, Shootable, Product {
	public static final String DEFAULT_WEAPON_TYPE = "None";
	public static final String DEFAULT_SKILL_NAME = "None";
	
	protected String weaponName;
	protected String description;
	protected String weaponType;
	protected String skillName;
	protected int hitModifier;
	protected int damageModifier;
	protected int rangeModifier;
	protected int rateOfFire;
	protected double cost;
	protected double weight;

	protected Weapon(String weaponName, String description, String weaponType, String skillName, int hitModifier,
			int damageModifier, int rangeModifier, int rateOfFire, double cost, double weight) {
		validateWeaponName(weaponName);
		validateDescription(description);
		validateWeaponType(weaponType);
		validateSkillName(skillName);
		this.hitModifier = hitModifier;
		this.damageModifier = damageModifier;
		this.rangeModifier = rangeModifier;
		this.rateOfFire = rateOfFire;
		this.cost = cost;
		this.weight = weight;
	}
	
	private void validateWeaponName(String weaponName) {
		if(weaponName == null) {
			this.weaponName = "Null Name";
		} else {
			this.weaponName = weaponName;
		}
	}
	
	private void validateDescription(String description) {
		if(description == null) {
			this.description = "";
		} else {
			this.description = description;
		}
	}
	
	private void validateWeaponType(String weaponType) {
		if(weaponType == null) {
			this.weaponType = DEFAULT_WEAPON_TYPE;
		} else {
			this.weaponType = weaponType;
		}
	}
	
	private void validateSkillName(String skillName) {
		if(skillName == null) {
			this.skillName = DEFAULT_SKILL_NAME;
		} else {
			this.skillName = skillName;
		}
	}

	public String getName() {
		return weaponName;
	}

	public String getDescription() {
		return description;
	}

	public double getCost() {
		return cost;
	}

	public double getWeight() {
		return weight;
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

	public String getWeaponType() {
		return weaponType;
	}

	public String getSkillName() {
		return skillName;
	}

	public int getRateOfFire() {
		return rateOfFire;
	}

	public abstract void setCombatant(Combatant combatant);

	public abstract WeaponModifier setModifier(WeaponModifier modifier);

	public abstract String getBonuses();
}
