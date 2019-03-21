package rpg.cyberpunk._2020.combat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.Combatant;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.Probability;

public class MissileWeapon extends CyberpunkWeapon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8061754554621958209L;
	
	private String weaponName;
	private String description;
	private String weaponType;
	private String skillName;
	private int weaponAccuracy;
	private Concealability concealability;
	private Availability availability;
	private Probability damage;
	private AmmunitionContainer ammunitionContainer;
	private int rateOfFire;
	private Reliability reliability;
	private int rangeModifier;
	private double cost;
	private double weight;
	private Set<String> attachmentPoints;
	private Map<String, WeaponAttachment> attachments;
	private Combatant combatant;

	public MissileWeapon(String weaponName, String description, String weaponType, int weaponAccuracy,
			Concealability concealability, Availability availability, Probability damage,
			AmmunitionContainer ammunitionContainer, int rateOfFire, Reliability reliability, int rangeModifier,
			double cost, double weight, Set<String> attachmentPoints) {
		this(weaponName, description, weaponType, parseSkillFromWeaponType(weaponType), weaponAccuracy, concealability,
				availability, damage, ammunitionContainer, rateOfFire, reliability, rangeModifier, cost, weight,
				attachmentPoints);
	}

	private static String parseSkillFromWeaponType(String weaponType) {
		switch (weaponType) {
		case WEAPON_TYPE_LIGHT_PISTOL:
		case WEAPON_TYPE_MEDIUM_PISTOL:
		case WEAPON_TYPE_HEAVY_PISTOL:
		case WEAPON_TYPE_VERY_HEAVY_PISTOL:
			return CyberpunkSkill.HANDGUN;
		case WEAPON_TYPE_LIGHT_SUBMACHINEGUN:
		case WEAPON_TYPE_MEDIUM_SUBMACHINEGUN:
		case WEAPON_TYPE_HEAVY_SUBMACHINEGUN:
			return CyberpunkSkill.SUBMACHINEGUN;
		case WEAPON_TYPE_RIFLE:
		case WEAPON_TYPE_SHOTGUN:
			return CyberpunkSkill.RIFLE;
		case WEAPON_TYPE_HEAVY_WEAPON:
			return CyberpunkSkill.HEAVY_WEAPONS;
		case WEAPON_TYPE_MELEE_WEAPON:
			return CyberpunkSkill.MELEE;
		default:
			return CyberpunkSkill.NONE;
		}
	}

	public MissileWeapon(String weaponName, String description, String weaponType, String skillName, int weaponAccuracy,
			Concealability concealability, Availability availability, Probability damage,
			AmmunitionContainer ammunitionContainer, int rateOfFire, Reliability reliability, int rangeModifier,
			double cost, double weight, Set<String> attachmentPoints) {
		setWeaponName(weaponName);
		setDescription(description);
		setWeaponType(weaponType);
		setSkillName(skillName);
		this.weaponAccuracy = weaponAccuracy;
		setConcealability(concealability);
		setAvailability(availability);
		setDamage(damage);
		setAmmunitionContainer(ammunitionContainer);
		setRateOfFire(rateOfFire);
		setReliability(reliability);
		setRangeModifier(rangeModifier);
		setCost(cost);
		setWeight(weight);
		setAttachmentsAndAttachmentPoints(attachmentPoints);
		combatant = NullCombatant.getInstance();
	}

	private void setWeaponName(String weaponName) {
		if (weaponName != null) {
			this.weaponName = weaponName;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setDescription(String description) {
		if (description != null) {
			this.description = description;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setWeaponType(String weaponType) {
		if (weaponType != null) {
			this.weaponType = weaponType;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setSkillName(String skillName) {
		if (skillName != null) {
			this.skillName = skillName;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setConcealability(Concealability concealability) {
		if (concealability != null) {
			this.concealability = concealability;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setAvailability(Availability availability) {
		if (availability != null) {
			this.availability = availability;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setDamage(Probability damage) {
		if (damage != null) {
			this.damage = damage;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setAmmunitionContainer(AmmunitionContainer ammunitionContainer) {
		if (ammunitionContainer != null) {
			this.ammunitionContainer = ammunitionContainer;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setRateOfFire(int rateOfFire) {
		if (rateOfFire >= 1) {
			this.rateOfFire = rateOfFire;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setReliability(Reliability reliability) {
		if (reliability != null) {
			this.reliability = reliability;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setRangeModifier(int rangeModifier) {
		if (rangeModifier >= 0) {
			this.rangeModifier = rangeModifier;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setCost(double cost) {
		if (cost >= 0.0) {
			this.cost = cost;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setWeight(double weight) {
		if (weight >= 0.0) {
			this.weight = weight;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setAttachmentsAndAttachmentPoints(Set<String> attachmentPoints) {
		if (attachmentPoints != null) {
			this.attachmentPoints = attachmentPoints;
			this.attachments = new HashMap<String, WeaponAttachment>();
		} else {
			throw new IllegalArgumentException("");
		}
	}

	@Override
	public void setCombatant(Combatant c) {
		if (c != null) {
			combatant = c;
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public String getWeaponType() {
		return weaponType;
	}

	@Override
	public String getSkillName() {
		return skillName;
	}

	@Override
	public int getRangeScore() {
		return getRangeModifier() + combatant.getRangeModifier(false);
	}

	@Override
	public int getDamageScore() {
		return getDamageModifier() + combatant.getDamageModifier(this);
	}

	@Override
	public int getHitScore() {
		return getHitModifier() + combatant.getHitModifier(this);
	}

	@Override
	public Die getDamageDice() {
		return damage.getDice();
	}

	@Override
	public WeaponAttachment addAttachment(WeaponAttachment attachment) {
		String targetAttachmentPoint = attachment.getAttachmentPoint();
		if (attachmentPoints.contains(targetAttachmentPoint)) {
			return attachments.put(targetAttachmentPoint, attachment);
		} else {
			return attachment;
		}
	}

	@Override
	public Iterator<WeaponAttachment> getAttachments() {
		return attachments.values().iterator();
	}

	@Override
	public boolean fire(int shotsToFire) {
		boolean hasFired = false;

		for (int shotsFired = 0; shotsFired < shotsToFire && !ammunitionContainer.isEmpty(); shotsFired++) {
			ammunitionContainer.withdrawAmmunition();
			hasFired = true;
		}

		return hasFired;
	}

	@Override
	public List<Ammunition> reload(AmmunitionContainer newMagazine) {
		return ammunitionContainer.transferAmmunitionFrom(newMagazine);
	}

	@Override
	public int getAmmunitionCount() {
		return ammunitionContainer.getAmmunitionCount();
	}

	@Override
	public int getAmmunitionCapacity() {
		return ammunitionContainer.getAmmunitionCapacity();
	}

	@Override
	public int getRateOfFire() {
		return rateOfFire;
	}

	@Override
	public String getAmmunitionType() {
		return ammunitionContainer.getAmmunitionType();
	}

	@Override
	public int getHitModifier() {
		return weaponAccuracy;
	}

	@Override
	public int getDamageModifier() {
		return damage.getModifier();
	}

	@Override
	public int getRangeModifier() {
		return rangeModifier;
	}

	@Override
	public String getName() {
		return weaponName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public Concealability getConcealability() {
		return concealability;
	}

	@Override
	public Availability getAvailability() {
		return availability;
	}

	@Override
	public Reliability getReliability() {
		return reliability;
	}

}
