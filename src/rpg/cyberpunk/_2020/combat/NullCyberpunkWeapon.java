package rpg.cyberpunk._2020.combat;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.Combatant;
import rpg.general.combat.EmptyAmmunitionContainer;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.NullProbability;
import rpg.util.Probability;

public class NullCyberpunkWeapon extends CyberpunkWeapon {
	private static final Probability DAMAGE = new Probability(new Die(0, 0), 0);
	private static final long serialVersionUID = 1L;

	private static NullCyberpunkWeapon uniqueInstance;
	private AmmunitionContainer ammunitionContainer;

	private NullCyberpunkWeapon() {
		ammunitionContainer = new EmptyAmmunitionContainer();
	}

	public static NullCyberpunkWeapon getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCyberpunkWeapon();
		}

		return uniqueInstance;
	}

	@Override
	public void setCombatant(Combatant c) {
		// Do Nothing

	}

	@Override
	public String getWeaponType() {
		return NO_WEAPON_TYPE;
	}

	@Override
	public String getSkillName() {
		return CyberpunkSkill.NONE;
	}

	@Override
	public int getRangeScore() {
		return 0;
	}

	@Override
	public int getDamageScore() {
		return 0;
	}

	@Override
	public int getHitScore() {
		return 0;
	}

	@Override
	public Die getDamageDice() {
		return DAMAGE.getDice();
	}

	@Override
	public WeaponAttachment addAttachment(WeaponAttachment attachment) {
		return attachment;
	}

	@Override
	public Iterator<WeaponAttachment> getAttachments() {
		return Collections.emptyIterator();
	}

	@Override
	public boolean fire(int numberOfShots) {
		return false;
	}

	@Override
	public List<Ammunition> reload(AmmunitionContainer newContainer) {
		return ammunitionContainer.transferAmmunitionFrom(newContainer);
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
		return 0;
	}

	@Override
	public String getAmmunitionType() {
		return CyberpunkWeapon.NO_AMMUNITION_TYPE;
	}

	@Override
	public int getHitModifier() {
		return 0;
	}

	@Override
	public int getDamageModifier() {
		return 0;
	}

	@Override
	public int getRangeModifier() {
		return 0;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public double getWeight() {
		return 0;
	}

	@Override
	public double getCost() {
		return 0;
	}

	@Override
	public Concealability getConcealability() {
		return Concealability.CANNOT_HIDE;
	}

	@Override
	public Availability getAvailability() {
		return Availability.UNAVAILABLE;
	}

	@Override
	public Reliability getReliability() {
		return Reliability.UNRELIABLE;
	}

	@Override
	public Probability getDamage() {
		return NullProbability.getInstance();
	}
}
