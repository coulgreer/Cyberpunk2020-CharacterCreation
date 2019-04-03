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

public class Bow extends CyberpunkWeapon {

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

	public Bow(String weaponName, String description, int weaponAccuracy, Concealability concealability,
			Availability availability, Probability damage, AmmunitionContainer ammunitionContainer, int rateOfFire,
			Reliability reliability, int rangeModifier, double cost, double weight, Set<String> attachmentPoints) {
		setWeaponName(weaponName);
		setDescription(description);
		this.weaponType = CyberpunkWeapon.WEAPON_TYPE_BOW;
		this.skillName = CyberpunkSkill.ARCHERY;
		this.weaponAccuracy = weaponAccuracy;
		setConcealability(concealability);
		setAvailability(availability);
		setDamage(damage);
		setAmmunitionContainer(ammunitionContainer);
		setRateOfFire(rateOfFire);
		setReliability(reliability);
		setRangeModifier(rangeModifier);
		setWeight(weight);
		setCost(cost);
		setAttachmentsAndAttachmentPoints(attachmentPoints);
		this.combatant = NullCombatant.getInstance();
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

	private void setAmmunitionContainer(AmmunitionContainer ammunitionContainer) {
		if (ammunitionContainer != null) {
			this.ammunitionContainer = ammunitionContainer;
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

	private void setCost(double cost) {
		if (cost >= 0.0) {
			this.cost = cost;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setCombatant(Combatant c) {
		this.combatant = c;
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
		return getRangeModifier() + combatant.getRangeModifier(true);
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
	public Iterator<WeaponAttachment> getAttachments() {
		return attachments.values().iterator();
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
	public boolean fire(int numberOfShots) {
		boolean hasFired = false;

		for (int shotsFired = 0; shotsFired < numberOfShots && !ammunitionContainer.isEmpty(); shotsFired++) {
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
