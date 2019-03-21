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

public class CyberpunkCombatant implements Combatant {
	public static final int PRIMARY_SLOT = 0;
	public static final int SECONDARY_SLOT = 1;
	public static final int MAX_WEAPON_AMOUNT = 2;
	public static final int DEFAULT_RANGE_MODIFIER = 0;
	public static final int DEFAULT_DAMAGE_MODIFIER = 0;

	private AbstractFightingStyleFactory unarmedWeaponFactory;
	private final Player player;
	private final Weapon[] weapons;

	public CyberpunkCombatant(Player player) {
		this.player = player;

		unarmedWeaponFactory = BrawlingFightingStyleFactory.getInstance();
		weapons = new Weapon[] { unarmedWeaponFactory.createStrike(), unarmedWeaponFactory.createStrike() };
	}

	@Override
	public void arm(int slot, Weapon weapon) {
		switch (slot) {
		case PRIMARY_SLOT:
			weapons[PRIMARY_SLOT] = weapon;
			weapon.setCombatant(this);
			break;
		case SECONDARY_SLOT:
			weapons[SECONDARY_SLOT] = weapon;
			weapon.setCombatant(this);
			break;
		default:
			break;
		}
	}

	@Override
	public void disarm(int slot) {
		arm(slot, unarmedWeaponFactory.createStrike());
	}

	@Override
	public int getRangeScore(int slot) {
		Weapon weapon = weapons[slot];
		return weapon.getRangeScore();
	}

	@Override
	public Probability getTotalDamageChance(int slot) {
		Weapon weapon = weapons[slot];
		return new Probability(weapon.getDamageDice(), weapon.getDamageScore());
	}

	@Override
	public Probability getTotalHitChance(int slot) {
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
		switch (slot) {
		case PRIMARY_SLOT:
			weapons[PRIMARY_SLOT].fire(shotsFired);
			break;
		case SECONDARY_SLOT:
			weapons[SECONDARY_SLOT].fire(shotsFired);
			break;
		default:
			break;
		}
	}

	public void setUnarmedStance(FightingStyle style, FightingMove move) {
		unarmedWeaponFactory = parseFightingStyleFactory(style);
		Weapon unarmedWeapon = parseFightingMove(move);

		arm(PRIMARY_SLOT, unarmedWeapon);
		arm(SECONDARY_SLOT, unarmedWeapon);
	}

	private AbstractFightingStyleFactory parseFightingStyleFactory(FightingStyle style) {
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
		switch (slot) {
		case PRIMARY_SLOT:
			return weapons[PRIMARY_SLOT].getAmmunitionCount();
		case SECONDARY_SLOT:
			return weapons[SECONDARY_SLOT].getAmmunitionCount();
		default:
			return AmmunitionContainer.EMPTY;
		}
	}

	@Override
	public List<Ammunition> reload(int slot, AmmunitionContainer storageUnit) {
		switch (slot) {
		case PRIMARY_SLOT:
			return weapons[PRIMARY_SLOT].reload(storageUnit);
		case SECONDARY_SLOT:
			return weapons[SECONDARY_SLOT].reload(storageUnit);
		default:
			ArrayList<Ammunition> remainingAmmunition = new ArrayList<>();

			while (!storageUnit.isEmpty()) {
				remainingAmmunition.add(storageUnit.withdrawAmmunition());
			}

			return remainingAmmunition;
		}
	}

	@Override
	public Weapon getWeapon(int slot) {
		return weapons[slot];
	}
}
