package rpg.cyberpunk._2020;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import rpg.Gender;
import rpg.cyberpunk._2020.combat.AikidoFightingStyleFactory;
import rpg.cyberpunk._2020.combat.AnimalKungFuFightingStyleFactory;
import rpg.cyberpunk._2020.combat.ArmorManager;
import rpg.cyberpunk._2020.combat.BoxingFightingStyleFactory;
import rpg.cyberpunk._2020.combat.BrawlingFightingStyleFactory;
import rpg.cyberpunk._2020.combat.CapoeriaFightingStyleFactory;
import rpg.cyberpunk._2020.combat.ChoiLiFutFightingStyleFactory;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.FightingMove;
import rpg.cyberpunk._2020.combat.FightingStyle;
import rpg.cyberpunk._2020.combat.FightingStyleFactory;
import rpg.cyberpunk._2020.combat.JudoFightingStyleFactory;
import rpg.cyberpunk._2020.combat.KarateFightingStyleFactory;
import rpg.cyberpunk._2020.combat.TaeKwonDoFightingStyleFactory;
import rpg.cyberpunk._2020.combat.ThaiKickBoxingFightingStyleFactory;
import rpg.cyberpunk._2020.combat.WrestlingFightingStyleFactory;
import rpg.cyberpunk._2020.commerce.BottomlessInventory;
import rpg.cyberpunk._2020.commerce.Inventory;
import rpg.cyberpunk._2020.commerce.PlayerTrader;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.cyberpunk._2020.stats.Role;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.general.combat.Ammunition;
import rpg.general.combat.BodyLocation;
import rpg.general.combat.Combatant;
import rpg.general.commerce.Item;
import rpg.general.commerce.Trader;
import rpg.general.stats.Attribute;
import rpg.util.Name;
import rpg.util.Probability;

public class Player {
  public static final String PROPERTY_NAME_MONEY = "Money";
  public static final String PROPERTY_NAME_INVENTORY_WEIGHT = "Inventory: Weight";
  public static final String PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED =
      "Inventory: Weapon Manipulated";
  public static final String PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED =
      "Inventory: Armor Manipulated";
  public static final String PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED =
      "Inventory: Ammunition Manipulated";
  public static final String PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED =
      "Inventory: Item Manipulated";
  public static final String PROPERTY_NAME_EQUIPMENT_WEAPON = "Equipment: Weapon";
  public static final String PROPERTY_NAME_EQUIPMENT_ARMOR = "Equipment: Armor";
  public static final String PROPERTY_NAME_ROLE = "Role";
  public static final String PROPERTY_NAME_CAREER_SKILL = "Career Skill";
  public static final String PROPERTY_NAME_AGE = "Age";
  public static final String PROPERTY_NAME_GENDER = "Gender";
  public static final String PROPERTY_NAME_EYES = "Eyes";
  public static final String PROPERTY_NAME_HEIGHT = "Height";
  public static final String PROPERTY_NAME_HAIR = "Hair";
  public static final String PROPERTY_NAME_SKIN_TONE = "Skin Tone";
  public static final String PROPERTY_NAME_WEIGHT = "Weight";
  public static final String PROPERTY_NAME_LIFE_EVENT = "Life Event";

  public static final int MIN_AGE = 16;

  /**
   * A constant representing the index of the primary weapon slot.
   */
  public static final int PRIMARY_SLOT = 0;

  /**
   * A constant representing the index of the secondary weapon slot.
   */
  public static final int SECONDARY_SLOT = 1;

  private static final int PRIMARY_LANGUAGE_BONUS = 8;

  private PropertyChangeSupport changeSupport;
  private Inventory pocketInventory = new BottomlessInventory();
  private Role role;
  private Trader trader;
  private Combatant<CyberpunkWeapon> combatant;
  private FightingStyleFactory unarmedWeaponFactory;
  private CyberpunkWeapon[] equippedWeapons;
  private ArmorManager armorManager;
  private Map<String, Map<String, Attribute>> attributesByNameByType;
  private Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName;

  private Name alias;
  private Characteristics characteristics;
  private String primaryLanguage;
  private Motivations motivations;
  private Background background;
  private Map<Age, String> eventsByAge;
  private String backstory;

  private List<String> careerSkillNames;

  public Player() {
    changeSupport = new PropertyChangeSupport(this);
    trader = new PlayerTrader(0.0);
    combatant = new CyberpunkCombatant(this);
    unarmedWeaponFactory = BrawlingFightingStyleFactory.getInstance();
    equippedWeapons = new CyberpunkWeapon[] {unarmedWeaponFactory.createStrike(),
        unarmedWeaponFactory.createStrike()};
    armorManager = new ArmorManager();
    attributesByNameByType = StatisticFactory.createAttributesByNameByType();
    skillsByNameByCategoryName = StatisticFactory.createSkillByNameByCategoryName(
        attributesByNameByType.get(StatisticFactory.INDEPENDENT_ATTRIBUTE), this);

    alias = new Name("UNKNOWN");
    characteristics = new Characteristics();
    primaryLanguage = "";
    motivations = new Motivations();
    background = new Background();
    eventsByAge = new HashMap<Age, String>();
    eventsByAge.put(new Age(MIN_AGE), "N/A");
    backstory = "";

    careerSkillNames = Collections.emptyList();
  }

  public void buy(CyberpunkWeapon weapon, double price) {
    double oldMoney = trader.getMoney();

    try {
      trader.buy(price);
      addToInventory(weapon);
    } catch (Exception ex) {
      trader.sell(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
  }

  public void buy(CyberpunkArmor armor, double price) {
    double oldMoney = trader.getMoney();

    try {
      trader.buy(price);
      addToInventory(armor);
    } catch (Exception ex) {
      trader.sell(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
  }

  public void buy(List<Ammunition> ammunition, double price) {
    double oldMoney = trader.getMoney();

    try {
      trader.buy(price);

      Iterator<Ammunition> iterator = ammunition.iterator();
      while (iterator.hasNext()) {
        addToInventory(iterator.next());
      }
    } catch (Exception ex) {
      trader.sell(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
  }

  public void sell(CyberpunkWeapon weapon, double price) {
    double oldMoney = trader.getMoney();

    try {
      trader.sell(price);
      removeFromInventory(weapon, 1);
    } catch (Exception ex) {
      trader.buy(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
  }

  public void sell(CyberpunkArmor armor, double price) {
    double oldMoney = trader.getMoney();

    try {
      trader.sell(price);
      removeFromInventory(armor, 1);
    } catch (Exception ex) {
      trader.buy(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
  }

  public void sell(Ammunition ammunition, double price) {
    double oldMoney = trader.getMoney();

    try {
      trader.sell(price);
      removeFromInventory(ammunition, 1);
    } catch (Exception ex) {
      trader.buy(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
  }

  public void sell(Item item, double price) {
    double oldMoney = trader.getMoney();

    trader.sell(price);
    try {
      removeFromInventory(item, 1);
    } catch (Exception ex) {
      trader.buy(price);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());

  }

  public double getMoney() {
    return trader.getMoney();
  }

  public void equip(int slot, CyberpunkWeapon weapon) {
    CyberpunkWeapon tempWeapon = equippedWeapons[slot];

    unequip(slot);
    try {
      removeFromInventory(weapon, 1);

      if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
        equippedWeapons[slot] = weapon;
      }
    } catch (NullPointerException ex) {
      equip(slot, tempWeapon);
      throw ex;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_EQUIPMENT_WEAPON, tempWeapon, weapon);
  }

  // TODO Think of throwing an exception to allow propagation of an error to the
  // UI.
  public void equip(CyberpunkArmor armor) {
    if (armorManager.add(armor)) {
      removeFromInventory(armor, 1);

      changeSupport.firePropertyChange(PROPERTY_NAME_EQUIPMENT_ARMOR, null, armor);
    }
  }

  public void unequip(int slot) {
    CyberpunkWeapon weapon = equippedWeapons[slot];

    addToInventory(weapon);

    if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
      equippedWeapons[slot] = createFightingMove(FightingMove.STRIKE);
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_EQUIPMENT_WEAPON, weapon, null);
  }

  // TODO Think of throwing an exception to allow propagation of an error to the
  // UI.
  public void unequip(CyberpunkArmor armor) {
    if (armorManager.remove(armor)) {
      addToInventory(armor);

      changeSupport.firePropertyChange(PROPERTY_NAME_EQUIPMENT_ARMOR, armor, null);
    }
  }

  public Attribute getAttribute(String name) {
    for (Map<String, Attribute> attributesByName : attributesByNameByType.values()) {
      if (attributesByName.containsKey(name)) {
        return attributesByName.get(name);
      }
    }

    throw new NoSuchElementException("An attribute with the name " + name + " does not exist.");
  }

  public Iterator<Attribute> createAttributeByTypeIterator(String type) {
    return attributesByNameByType //
        .get(type) //
        .values() //
        .iterator();
  }

  public CyberpunkSkill getSkill(String name) {
    for (Map<String, CyberpunkSkill> skillsByName : skillsByNameByCategoryName.values()) {
      if (skillsByName.containsKey(name)) {
        return skillsByName.get(name);
      }
    }

    return null;
  }

  public Iterator<Map.Entry<String, Map<String, CyberpunkSkill>>> createSkillCategoryIterator() {
    // TODO (Coul Greer): Think about moving this kind of immutable nested Map into the factor. Use
    // Collections and its 'unmodifiable' methods as an example.
    Map<String, Map<String, CyberpunkSkill>> immutablekillsByNameByCategoryName =
        new HashMap<String, Map<String, CyberpunkSkill>>();

    for (String key : skillsByNameByCategoryName.keySet()) {
      Map<String, CyberpunkSkill> skillsByName = skillsByNameByCategoryName.get(key);
      immutablekillsByNameByCategoryName.put(key, Collections.unmodifiableMap(skillsByName));
    }

    return Collections.unmodifiableMap(immutablekillsByNameByCategoryName) //
        .entrySet() //
        .iterator();
  }

  public Iterator<CyberpunkSkill> createLanguageIterator() {
    // TODO (Coul Greer): Think about moving CP Attribute names and CP Skill names to the
    // StatisticFactory class. Since the StatisticFactory creates all Attributes and Skills it may
    // make sense to make it the hub for object to object constants since not all Attributes or
    // Skills needs to know the name of all other Statistics of its kind.

    Map<String, CyberpunkSkill> skillsByName =
        skillsByNameByCategoryName.get(CyberpunkAttribute.INTELLIGENCE);

    Map<String, CyberpunkSkill> filteredSkillsByName = skillsByName.entrySet() //
        .stream() //
        .filter(entry -> entry.getKey().startsWith(StatisticFactory.LANGUAGE_IDENTIFIER)) //
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    return filteredSkillsByName //
        .values() //
        .iterator();
  }

  public Probability getTotalAttackChance(int slot) {
    return combatant.getTotalAttackChance(equippedWeapons[slot]);
  }

  public int getPlayerAttackModifier(int slot) {
    return combatant.getAttackModifier(equippedWeapons[slot]);
  }

  public int getWeaponAttackModifier(int slot) {
    return equippedWeapons[slot].getAttackModifier();
  }

  public Probability getTotalDamageChance(int slot) {
    return combatant.getTotalDamageChance(equippedWeapons[slot]);
  }

  public int getPlayerDamageModifier(int slot) {
    return combatant.getDamageModifier(equippedWeapons[slot]);
  }

  public int getWeaponDamageModifier(int slot) {
    return equippedWeapons[slot].getDamageModifier();
  }

  public int getRangeScore(int slot) {
    return combatant.getRangeScore(equippedWeapons[slot]);
  }

  public int getPlayerRangeModifier(int slot) {
    return combatant.getRangeModifier(equippedWeapons[slot]);
  }

  public int getWeaponRangeModifier(int slot) {
    return equippedWeapons[slot].getRangeModifier();
  }

  public void attack(int slot, int shotsFired) {
    equippedWeapons[slot].attack(shotsFired);
  }

  public List<Ammunition> reload(int slot, List<Ammunition> ammunition) {
    List<Ammunition> spareAmmunition = equippedWeapons[slot].reload(ammunition);
    spareAmmunition.stream().forEach(a -> addToInventory(a));
    return spareAmmunition;
  }

  public void setFightingStance(FightingStyle style, FightingMove move) {
    unarmedWeaponFactory = getFightingStyleFactory(style);
    CyberpunkWeapon weapon = createFightingMove(move);

    equip(PRIMARY_SLOT, weapon);
    equip(SECONDARY_SLOT, weapon);
  }

  private FightingStyleFactory getFightingStyleFactory(FightingStyle style) {
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

  private CyberpunkWeapon createFightingMove(FightingMove move) {
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

  // TODO (Coul Greer): Consider hiding this from any other classes. Perhaps use
  // the player as a middle man to access a weapons data and delete this function.
  public CyberpunkWeapon getWeapon(int slot) {
    return equippedWeapons[slot];
  }

  public boolean hasItem(Object o) {
    return pocketInventory.contains(o);
  }

  public int getQuantity(Item item) {
    return pocketInventory.getQuantity(item);
  }

  public void addToInventory(CyberpunkWeapon weapon) {
    double oldWeight = getTotalWeight();

    if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
      pocketInventory.add(weapon);
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, null, weapon);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, weapon);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public void addToInventory(CyberpunkArmor armor) {
    double oldWeight = getTotalWeight();

    pocketInventory.add(armor);

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, null, armor);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, armor);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public void addToInventory(Ammunition ammunition) {
    double oldWeight = getTotalWeight();

    pocketInventory.add(ammunition);

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, null,
        ammunition);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, ammunition);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public void removeFromInventory(CyberpunkWeapon weapon, int quantity) {
    double oldWeight = getTotalWeight();

    if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
      for (int i = 0; i < quantity; i++) {
        pocketInventory.remove(weapon);
      }
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, weapon, null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, weapon, null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public void removeFromInventory(CyberpunkArmor armor, int quantity) {
    double oldWeight = getTotalWeight();

    for (int i = 0; i < quantity; i++) {
      pocketInventory.remove(armor);
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, armor, null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, armor, null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public void removeFromInventory(Ammunition ammunition, int quantity) {
    double oldWeight = getTotalWeight();

    for (int i = 0; i < quantity; i++) {
      pocketInventory.remove(ammunition);
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, ammunition,
        null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, ammunition, null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public void removeFromInventory(Item item, int quantity) {
    double oldWeight = getTotalWeight();

    for (int i = 0; i < quantity; i++) {
      pocketInventory.removeItem(item);
    }

    if (item instanceof CyberpunkWeapon) {
      changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, item, null);
    }

    if (item instanceof CyberpunkArmor) {
      changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, item, null);
    }

    if (item instanceof Ammunition) {
      changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, item, null);
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, item, null);
    changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
  }

  public Collection<CyberpunkWeapon> createCarriedWeaponCollection() {
    return pocketInventory.createWeaponCollection();
  }

  public Collection<CyberpunkArmor> createCarriedArmorCollection() {
    return pocketInventory.createArmorCollection();
  }

  public Collection<Ammunition> createCarriedAmmunitionCollection() {
    return pocketInventory.createAmmunitionCollection();
  }

  public Collection<Item> createCarriedItemCollection() {
    return pocketInventory.createItemCollection();
  }

  public Collection<CyberpunkArmor> createEquippedArmorCollection() {
    return armorManager.createArmorCollection();
  }

  public double getTotalWeight() {
    return pocketInventory.getTotalWeight() //
        + equippedWeapons[PRIMARY_SLOT].getWeight() + equippedWeapons[SECONDARY_SLOT].getWeight() //
        + armorManager.getTotalWeight();
  }

  public int getLocationDurability(BodyLocation location) {
    return armorManager.getDurabilityAt(location);
  }

  public int getEncumbranceValue() {
    return armorManager.getEncumbrancePenalty();
  }

  public Name getAlias() {
    return alias;
  }

  public void setAlias(Name alias) {
    if (alias == null) {
      throw new NullPointerException();
    } else {
      this.alias = alias;
    }
  }

  public Age getAge() {
    return characteristics.getAge();
  }

  public void setAge(Age age) {
    Age oldValue = characteristics.getAge();
    int tempAge = oldValue.toInt();

    while (tempAge < age.toInt()) {
      tempAge++;
      eventsByAge.put(new Age(tempAge), "N/A");
    }

    while (tempAge > age.toInt()) {
      eventsByAge.remove(new Age(tempAge));
      tempAge--;
    }

    characteristics.setAge(age);
    changeSupport.firePropertyChange(PROPERTY_NAME_AGE, oldValue, age);
  }

  public String getPrimaryLanguage() {
    return primaryLanguage;
  }

  public void setPrimaryLanguage(String primaryLanguage) {
    if (primaryLanguage == null) {
      throw new NullPointerException();
    } else if (!hasLanguage(primaryLanguage)) {
      throw new NoSuchElementException();
    } else {
      reallocateLanguagePointsTo(primaryLanguage);
      this.primaryLanguage = primaryLanguage;
    }
  }

  private boolean hasLanguage(String name) {
    Iterator<CyberpunkSkill> iterator = createLanguageIterator();

    while (iterator.hasNext()) {
      CyberpunkSkill skill = iterator.next();
      if (skill.getName().equals(name)) {
        return true;
      }
    }

    return false;
  }

  // TODO (Coul Greer): Maybe only allow this at character creation. This can currently result in
  // a user decrementing a level and then switching prime languages which inflates the points they
  // can spend or have. This will then result in the user only being refunded part of the Language
  // Bonus but still getting the full bonus for the new language.
  private void reallocateLanguagePointsTo(String primaryLanguage) {
    CyberpunkSkill oldSkill = getSkill(this.primaryLanguage);
    if (oldSkill != null) {
      refundLanguagePointsFrom(oldSkill);
    }

    CyberpunkSkill newSkill = getSkill(primaryLanguage);
    if (newSkill != null) {
      allocateLanguagePointsTo(newSkill);
    }
  }

  private void refundLanguagePointsFrom(CyberpunkSkill skill) {
    if (skill == null) {
      throw new NullPointerException();
    } else {
      for (int i = 0; i < PRIMARY_LANGUAGE_BONUS; i++) {
        skill.decrementLevel();
      }
    }
  }

  private void allocateLanguagePointsTo(CyberpunkSkill skill) {
    if (skill == null) {
      throw new NullPointerException();
    } else {
      for (int i = 0; i < PRIMARY_LANGUAGE_BONUS; i++) {
        skill.incrementLevel();
      }
    }
  }

  public Gender getGender() {
    return characteristics.getGender();
  }

  public void setGender(Gender gender) {
    Gender oldValue = characteristics.getGender();
    characteristics.setGender(gender);

    changeSupport.firePropertyChange(PROPERTY_NAME_GENDER, oldValue, gender);
  }

  public String getEyes() {
    return characteristics.getEyes();
  }

  public void setEyes(String eyes) {
    String oldValue = characteristics.getEyes();
    characteristics.setEyes(eyes);

    changeSupport.firePropertyChange(PROPERTY_NAME_EYES, oldValue, eyes);
  }

  public String getHeight() {
    return characteristics.getHeight();
  }

  public void setHeight(String height) {
    String oldValue = characteristics.getHeight();
    characteristics.setHeight(height);

    changeSupport.firePropertyChange(PROPERTY_NAME_HEIGHT, oldValue, height);
  }

  public String getHair() {
    return characteristics.getHair();
  }

  public void setHair(String hair) {
    String oldValue = characteristics.getHair();
    characteristics.setHair(hair);

    changeSupport.firePropertyChange(PROPERTY_NAME_HAIR, oldValue, hair);
  }

  public String getSkinTone() {
    return characteristics.getSkinTone();
  }

  public void setSkinTone(String skinTone) {
    String oldValue = characteristics.getSkinTone();
    characteristics.setSkinTone(skinTone);

    changeSupport.firePropertyChange(PROPERTY_NAME_SKIN_TONE, oldValue, skinTone);
  }

  public String getWeight() {
    return characteristics.getWeight();
  }

  public void setWeight(String weight) {
    String oldValue = characteristics.getWeight();
    characteristics.setWeight(weight);

    changeSupport.firePropertyChange(PROPERTY_NAME_WEIGHT, oldValue, weight);
  }

  public String getPersonalityTrait() {
    return motivations.getPersonalityTrait();
  }

  public void setPersonalityTrait(String personalityTrait) {
    motivations.setPersonalityTrait(personalityTrait);
  }

  public String getValuedPerson() {
    return motivations.getValuedPerson();
  }

  public void setValuedPerson(String valuedPerson) {
    motivations.setValuedPerson(valuedPerson);
  }

  public String getValuedConcept() {
    return motivations.getValuedConcept();
  }

  public void setValuedConcept(String valuedConcept) {
    motivations.setValuedConcept(valuedConcept);
  }

  public String getFeelingsTowardOthers() {
    return motivations.getFeelingsTowardOthers();
  }

  public void setFeelingsTowardOthers(String feelingsTowardOthers) {
    motivations.setFeelingsTowardOthers(feelingsTowardOthers);
  }

  public String getValuedPosession() {
    return motivations.getValuedPosession();
  }

  public void setValuedPosession(String valuedPosession) {
    motivations.setValuedPosession(valuedPosession);
  }

  public String getFamilyRanking() {
    return background.getFamilyRanking();
  }

  public void setFamilyRanking(String familyRanking) {
    background.setFamilyRanking(familyRanking);
  }

  public String getParentStatus() {
    return background.getParentStatus();
  }

  public void setParentStatus(String parentStatus) {
    background.setParentStatus(parentStatus);
  }

  public boolean hasParentTragedy() {
    return background.hasParentTragedy();
  }

  public void setParentTragedy(boolean hasParentTragedy) {
    background.setParentTragedy(hasParentTragedy);
  }

  public String getParentTragedy() {
    return background.getParentTragedy();
  }

  public void setParentTragedy(String parentTragedy) {
    background.setParentTragedy(parentTragedy);
  }

  public String getFamilyStatus() {
    return background.getFamilyStatus();
  }

  public void setFamilyStatus(String familyStatus) {
    background.setFamilyStatus(familyStatus);
  }

  public boolean hasFamilyTragedy() {
    return background.hasFamilyTragedy();
  }

  public void setFamilyTragedy(boolean hasFamilyTragedy) {
    background.setFamilyTragedy(hasFamilyTragedy);
  }

  public String getFamilyTragedy() {
    return background.getFamilyTragedy();
  }

  public void setFamilyTragedy(String familyTragedy) {
    background.setFamilyTragedy(familyTragedy);
  }

  public String getChildhoodEnvironment() {
    return background.getChildhoodEnvironment();
  }

  public void setChildhoodEnvironment(String childhoodEnvironment) {
    background.setChildhoodEnvironment(childhoodEnvironment);
  }

  public ObservableList<Sibling> getSiblings() {
    return background.getSiblings();
  }

  public void addAll(Collection<Sibling> collection) {
    background.addAll(collection);
  }

  public void addSibling(Sibling sibling) {
    background.addSibling(sibling);
  }

  public void removeSibling(Sibling sibling) {
    background.removeSibling(sibling);
  }

  public void clearSiblings() {
    background.clear();
  }

  public Map<Age, String> getEventsByAge() {
    return Collections.unmodifiableMap(eventsByAge);
  }

  public void updateEvent(Age age, String lifeEvent) {
    if (age.toInt() < MIN_AGE) {
      throw new IllegalArgumentException("age = " + age + "; min = " + MIN_AGE);
    } else if (lifeEvent == null) {
      throw new NullPointerException();
    } else {
      String oldValue = eventsByAge.replace(age, lifeEvent);

      changeSupport.firePropertyChange(PROPERTY_NAME_LIFE_EVENT, oldValue, lifeEvent);
    }
  }

  public String getBackstory() {
    return backstory;
  }

  public void setBackstory(String backstory) {
    if (backstory == null) {
      this.backstory = "";
    } else {
      this.backstory = backstory;
    }
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    Object oldValue = this.role;

    this.role = role;
    changeSupport.firePropertyChange(PROPERTY_NAME_ROLE, oldValue, role);
  }

  public List<String> getCareerSkillNames() {
    return careerSkillNames;
  }

  public void setCareerSkillNames(List<String> careerSkillNames) {
    List<String> oldValue = careerSkillNames;

    if (careerSkillNames == null) {
      throw new NullPointerException();
    } else {
      this.careerSkillNames = careerSkillNames;
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_CAREER_SKILL, oldValue, careerSkillNames);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(listener);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(propertyName, listener);
  }

}
