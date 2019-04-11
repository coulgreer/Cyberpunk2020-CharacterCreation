package rpg.cyberpunk._2020.combat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.Combatant;
import rpg.general.combat.EmptyAmmunitionContainer;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.Probability;

/**
 * An instance of <code>CyberpunkWeapon</code> that has a rate of fire of 1 and
 * cannot hold any ammunition.
 * 
 * @author Coul Greer
 *
 */
public class MeleeWeapon extends CyberpunkWeapon {
	public static final int RATE_OF_FIRE = 1;

	private String weaponName;
	private String description;
	private String weaponType;
	private String skillName;
	private int weaponAccuracy;
	private Concealability concealability;
	private Availability availability;
	private Probability damage;
	private boolean isEdged;
	private AmmunitionContainer ammunitionContainer;
	private Reliability reliability;
	private int rangeModifier;
	private double cost;
	private double weight;
	private Set<String> attachmentPoints;
	private Map<String, WeaponAttachment> attachments;
	private Combatant combatant;

	public MeleeWeapon(String weaponName, String description, String weaponType, String skillName, int weaponAccuracy,
			Concealability concealability, Availability availability, Probability damage, boolean isEdged,
			Reliability reliability, int rangeModifier, double cost, double weight, Set<String> attachmentPoints) {
		setWeaponName(weaponName);
		setDescription(description);
		setWeaponType(weaponType);
		setSkillName(skillName);
		this.weaponAccuracy = weaponAccuracy;
		setConcealability(concealability);
		setAvailability(availability);
		setDamage(damage);
		this.isEdged = isEdged;
		this.ammunitionContainer = new EmptyAmmunitionContainer();
		setReliability(reliability);
		setRangeModifier(rangeModifier);
		setCost(cost);
		setWeight(weight);
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

	private void setAttachmentsAndAttachmentPoints(Set<String> attachmentPoints) {
		if (attachmentPoints != null) {
			this.attachmentPoints = attachmentPoints;
			this.attachments = new HashMap<String, WeaponAttachment>();
		} else {
			throw new IllegalArgumentException("");
		}
	}

	private void setWeight(double weight) {
		if (weight >= 0.0) {
			this.weight = weight;
		} else {
			throw new IllegalArgumentException();
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
	public boolean fire(int numberOfShots) {
		return true;
	}

	@Override
	public List<Ammunition> reload(AmmunitionContainer container) {
		return ammunitionContainer.transferAmmunitionFrom(container);
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
		return RATE_OF_FIRE;
	}

	@Override
	public String getAmmunitionType() {
		return CyberpunkWeapon.NO_AMMUNITION_TYPE;
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
		return isEdged ? description + " Halves all soft armor SP." : description;
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
