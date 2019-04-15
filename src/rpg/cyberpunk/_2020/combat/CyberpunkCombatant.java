package rpg.cyberpunk._2020.combat;

import java.util.ArrayList;
import java.util.List;

import rpg.Player;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.Combatant;
import rpg.general.combat.Weapon;
import rpg.util.Probability;

/**
 * An instance of Combatant that uses Skills from Cyberpunk 2020 in order to get
 * the scores and modifiers for hit, attack, and damage. Weapons can also be
 * manipulated through this instance as well.
 * 
 * @author Coul Greer
 */
public class CyberpunkCombatant implements Combatant {
	/**
	 * A constant representing the index of the primary weapon slot.
	 */
	public static final int PRIMARY_SLOT = 0;

	/**
	 * A constant representing the index of the secondary weapon slot.
	 */
	public static final int SECONDARY_SLOT = 1;

	/**
	 * A constant representing the capacity of weapons a combatant can hold.
	 */
	public static final int MAX_WEAPON_AMOUNT = 2;

	/**
	 * The range modifier used when no range modifier is provided or an error would
	 * occur.
	 */
	public static final int DEFAULT_RANGE_MODIFIER = 0;

	/**
	 * The damage modifier used when no damage modifier is provided or an error
	 * would occur.
	 */
	public static final int DEFAULT_DAMAGE_MODIFIER = 0;

	private FightingStyleFactory unarmedWeaponFactory;
	private final Player player;
	private final Weapon[] weapons;

	/**
	 * Constructs a CyberpunkCombatant that requires a player to get the modifiers,
	 * and initializes this combatant to start of with Brawling Strikes equipped in
	 * both slots.
	 * 
	 * @param player the owner of the stats used to derive scores and modifiers
	 */
	public CyberpunkCombatant(Player player) {
		this.player = player;

		unarmedWeaponFactory = BrawlingFightingStyleFactory.getInstance();
		weapons = new Weapon[] { unarmedWeaponFactory.createStrike(), unarmedWeaponFactory.createStrike() };
	}

	/**
	 * @throws IndexOutOfBoundsException if slot is not {@link #PRIMARY_SLOT} or
	 *                                   {@link #SECONDARY_SLOT}
	 * @throws NullPointerException      if weapon is null
	 */
	@Override
	public void arm(int slot, Weapon weapon) {
		validateSlot(slot);
		validateWeapon(weapon);
		weapons[slot] = weapon;
		weapon.setCombatant(this);
	}

	private void validateSlot(int slot) {
		if (slot != PRIMARY_SLOT && slot != SECONDARY_SLOT) {
			throw new IndexOutOfBoundsException(
					"index: " + slot + "; valid indices: " + PRIMARY_SLOT + " or " + SECONDARY_SLOT);
		}
	}

	private void validateWeapon(Weapon weapon) {
		if (weapon == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * @throws IndexOutOfBoundsException if slot is not {@link #PRIMARY_SLOT} or
	 *                                   {@link #SECONDARY_SLOT}
	 */
	@Override
	public void disarm(int slot) {
		validateSlot(slot);
		arm(slot, unarmedWeaponFactory.createStrike());
	}

	/**
	 * @throws IndexOutOfBoundsException if slot is not {@link #PRIMARY_SLOT} or
	 *                                   {@link #SECONDARY_SLOT}
	 */
	@Override
	public int getRangeScore(int slot) {
		validateSlot(slot);
		return weapons[slot].getRangeScore();
	}

	/**
	 * @throws IndexOutOfBoundsException if slot is not {@link #PRIMARY_SLOT} or
	 *                                   {@link #SECONDARY_SLOT}
	 */
	@Override
	public Probability getTotalDamageChance(int slot) {
		validateSlot(slot);
		Weapon weapon = weapons[slot];
		return new Probability(weapon.getDamageDice(), weapon.getDamageScore());
	}

	/**
	 * @throws IndexOutOfBoundsException if slot is not {@link #PRIMARY_SLOT} or
	 *                                   {@link #SECONDARY_SLOT}
	 */
	@Override
	public Probability getTotalHitChance(int slot) {
		validateSlot(slot);
		Weapon weapon = weapons[slot];
		return new Probability(weapon.getHitDice(), weapon.getHitScore());
	}

	@Override
	public int getHitModifier(Weapon weapon) {
		return player.getSkillValue(weapon.getSkillName());
	}

	@Override
	public int getDamageModifier(Weapon weapon) {
		switch (weapon.getWeaponType()) {
		case CyberpunkWeapon.WEAPON_TYPE_UNARMED:
			return getMiscellaneousDamageModifier(weapon);
		default:
			return DEFAULT_DAMAGE_MODIFIER;
		}
	}

	private int getMiscellaneousDamageModifier(Weapon weapon) {
		switch (weapon.getSkillName()) {
		case CyberpunkSkill.AIKIDO:
			return player.getSkillValue(CyberpunkSkill.AIKIDO);
		case CyberpunkSkill.ANIMAL_KUNG_FU:
			return player.getSkillValue(CyberpunkSkill.ANIMAL_KUNG_FU);
		case CyberpunkSkill.BOXING:
			return player.getSkillValue(CyberpunkSkill.BOXING);
		case CyberpunkSkill.CAPOERIA:
			return player.getSkillValue(CyberpunkSkill.CAPOERIA);
		case CyberpunkSkill.CHOI_LI_FUT:
			return player.getSkillValue(CyberpunkSkill.CHOI_LI_FUT);
		case CyberpunkSkill.JUDO:
			return player.getSkillValue(CyberpunkSkill.JUDO);
		case CyberpunkSkill.KARATE:
			return player.getSkillValue(CyberpunkSkill.KARATE);
		case CyberpunkSkill.TAE_KWON_DO:
			return player.getSkillValue(CyberpunkSkill.TAE_KWON_DO);
		case CyberpunkSkill.THAI_KICK_BOXING:
			return player.getSkillValue(CyberpunkSkill.THAI_KICK_BOXING);
		case CyberpunkSkill.WRESTLING:
			return player.getSkillValue(CyberpunkSkill.WRESTLING);
		default:
			return DEFAULT_DAMAGE_MODIFIER;
		}
	}

	@Override
	public int getRangeModifier(boolean isThrown) {
		if (isThrown) {
			return player.getAttributeValue(CyberpunkAttribute.BODY_TYPE) * 10;
		} else {
			return DEFAULT_RANGE_MODIFIER;
		}
	}

	@Override
	public void attack(int slot, int shotsFired) {
		validateSlot(slot);
		weapons[slot].fire(shotsFired);
	}

	public void setUnarmedStance(FightingStyle style, FightingMove move) {
		unarmedWeaponFactory = parseFightingStyleFactory(style);
		Weapon unarmedWeapon = parseFightingMove(move);

		arm(PRIMARY_SLOT, unarmedWeapon);
		arm(SECONDARY_SLOT, unarmedWeapon);
	}

	private FightingStyleFactory parseFightingStyleFactory(FightingStyle style) {
		switch (style) {
		case BRAWLING:
			return BrawlingFightingStyleFactory.getInstance();
		case KARATE:
			return KarateFightingStyleFactory.getInstance();
		case JUDO:
			return JudoFightingStyleFactory.getInstance();
		case BOXING:
			return BoxingFightingStyleFactory.getInstance();
		case THAI_BOXING:
			return ThaiKickBoxingFightingStyleFactory.getInstance();
		case CHOI_LI_FUT:
			return ChoiLiFutFightingStyleFactory.getInstance();
		case AIKIDO:
			return AikidoFightingStyleFactory.getInstance();
		case ANIMAL_KUNG_FU:
			return AnimalKungFuFightingStyleFactory.getInstance();
		case TAE_KWON_DO:
			return TaeKwonDoFightingStyleFactory.getInstance();
		case WRESTLING:
			return WrestlingFightingStyleFactory.getInstance();
		case CAPEOIRA:
			return CapoeriaFightingStyleFactory.getInstance();
		default:
			return unarmedWeaponFactory;
		}
	}

	private Weapon parseFightingMove(FightingMove move) {
		switch (move) {
		case STRIKE:
			return unarmedWeaponFactory.createStrike();
		case KICK:
			return unarmedWeaponFactory.createKick();
		case BLOCK:
			return unarmedWeaponFactory.createBlock();
		case DODGE:
			return unarmedWeaponFactory.createDodge();
		case DISARM:
			return unarmedWeaponFactory.createDisarm();
		case THROW:
			return unarmedWeaponFactory.createThrow();
		case HOLD:
			return unarmedWeaponFactory.createHold();
		case ESCAPE:
			return unarmedWeaponFactory.createEscape();
		case CHOKE:
			return unarmedWeaponFactory.createChoke();
		case SWEEP:
			return unarmedWeaponFactory.createSweep();
		case GRAPPLE:
			return unarmedWeaponFactory.createGrapple();
		default:
			return unarmedWeaponFactory.createStrike();
		}
	}

	@Override
	public int getAmmoCount(int slot) {
		try {
			validateSlot(slot);
			return weapons[slot].getAmmunitionCount();
		} catch (IndexOutOfBoundsException ex) {
			return AmmunitionContainer.EMPTY;
		}
	}

	@Override
	public List<Ammunition> reload(int slot, AmmunitionContainer storageUnit) {
		try {
			validateSlot(slot);
			return weapons[slot].reload(storageUnit);
		} catch (IndexOutOfBoundsException ex) {
			ArrayList<Ammunition> remainingAmmunition = new ArrayList<>();

			while (!storageUnit.isEmpty()) {
				remainingAmmunition.add(storageUnit.withdrawAmmunition());
			}

			return remainingAmmunition;
		}
	}

	/**
	 * @throws IndexOutOfBoundsException if slot is not {@link #PRIMARY_SLOT} or
	 *                                   {@link #SECONDARY_SLOT}
	 */
	@Override
	public Weapon getWeapon(int slot) {
		validateSlot(slot);
		return weapons[slot];
	}
}
