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

/**
 * An instance of <code>CyberpunkWeapon</code> that provides its own flat damage
 * probability, and uses <code>Ammunition</code> of the type
 * {@link rpg.cyberpunk._2020.combat.Arrow#AMMUNITION_TYPE_ARROW Arrow} in order
 * to deal the flat damage to a target.
 * 
 * @author Coul Greer
 */
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

	/**
	 * Constructs a default Bow that uses the {@link CyberpunkSkill#ARCHERY archery}
	 * skill and has a weapon type of {@link CyberpunkWeapon#WEAPON_TYPE_BOW Bow}.
	 * 
	 * @param weaponName          the identifier of this weapon
	 * @param description         a blurb used to help give an idea of the given
	 *                            weapon
	 * @param weaponAccuracy      the flat modifier for the hit score
	 * @param concealability      the rating score for how easy this weapon can be
	 *                            hidden
	 * @param availability        the rating score for how easy this weapon can be
	 *                            found on the market
	 * @param damage              the probability of damage represented in die
	 *                            notation
	 * @param ammunitionContainer the holder of all ammunition
	 * @param rateOfFire          the amount of attacks that can be made per turn
	 * @param reliability         the rating score for how likely this weapon is to
	 *                            fail in combat
	 * @param rangeModifier       the flat modifier for how far this weapon can make
	 *                            an attack
	 * @param cost                the base value used when transacting
	 * @param weight              the heaviness value
	 * @param attachmentPoints    the slots that allow for weapon modifiers to be
	 *                            attached to
	 */
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
		if (weaponName == null) {
			throw new NullPointerException();
		} else {
			this.weaponName = weaponName;
		}
	}

	private void setDescription(String description) {
		if (description == null) {
			throw new NullPointerException();
		} else {
			this.description = description;
		}
	}

	private void setConcealability(Concealability concealability) {
		if (concealability == null) {
			throw new NullPointerException();
		} else {
			this.concealability = concealability;
		}
	}

	private void setAvailability(Availability availability) {
		if (availability == null) {
			throw new NullPointerException();
		} else {
			this.availability = availability;
		}
	}

	private void setAmmunitionContainer(AmmunitionContainer ammunitionContainer) {
		if (ammunitionContainer == null) {
			throw new NullPointerException();
		} else {
			this.ammunitionContainer = ammunitionContainer;
		}
	}

	private void setDamage(Probability damage) {
		if (damage == null) {
			throw new NullPointerException();
		} else {
			this.damage = damage;
		}
	}

	private void setRateOfFire(int rateOfFire) {
		if (rateOfFire < 1) {
			throw new IllegalArgumentException("rate of fire: " + rateOfFire + "; min value: 1");
		} else {
			this.rateOfFire = rateOfFire;
		}
	}

	private void setReliability(Reliability reliability) {
		if (reliability == null) {
			throw new NullPointerException();
		} else {
			this.reliability = reliability;
		}
	}

	private void setRangeModifier(int rangeModifier) {
		if (rangeModifier < 0) {
			throw new IllegalArgumentException("range modifier: " + rangeModifier + "; min value: 0");
		} else {
			this.rangeModifier = rangeModifier;
		}
	}

	private void setWeight(double weight) {
		if (weight < 0.0) {
			throw new IllegalArgumentException("weight: " + weight + "; min value: 0");
		} else {
			this.weight = weight;
		}
	}

	private void setAttachmentsAndAttachmentPoints(Set<String> attachmentPoints) {
		if (attachmentPoints == null) {
			throw new NullPointerException();
		} else {
			this.attachmentPoints = attachmentPoints;
			this.attachments = new HashMap<String, WeaponAttachment>();
		}
	}

	private void setCost(double cost) {
		if (cost < 0.0) {
			throw new IllegalArgumentException("cost: " + cost + "; min value: 0");
		} else {
			this.cost = cost;
		}
	}

	@Override
	public void setCombatant(Combatant c) {
		if (c == null) {
			this.combatant = NullCombatant.getInstance();
		} else {
			this.combatant = c;
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

	@Override
	public Probability getDamage() {
		return damage;
	}

}
