package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeListener;
import rpg.general.stats.Skill;

/**
 * A specialized type of skill that uses Improvement Points to level up skills automatically. This
 * skill also has the ability to enable or disable the skill as defined by the implementing classes.
 */
public interface CyberpunkSkill extends Skill {
  // Default Skill
  public static final String NONE = "None";

  // Special Skills
  public static final String SPECIAL_SKILLS = "Special Skills";
  public static final String AUTHORITY = "Authority";
  public static final String CHARISMATIC_LEADERSHIP = "Charismatic Leadership";
  public static final String COMBAT_SENSE = "Combat Sense";
  public static final String CREDIBILITY = "Credibility";
  public static final String FAMILY = "Family";
  public static final String INTERFACE = "Interface";
  public static final String JURY_RIG = "Jury Rig";
  public static final String MEDICAL_TECH = "Medical Tech";
  public static final String RESOURCES = "Resources";
  public static final String STREETDEAL = "Streetdeal";

  // Attractiveness Skills
  public static final String ATTRACTIVENESS_SKILLS = "Attractiveness Skills";
  public static final String PERSONAL_GROOMING = "Personal Grooming";
  public static final String WARDROBE_AND_STYLE = "Wardrobe and Style";

  // Body Skills
  public static final String BODY_TYPE_SKILLS = "Body Type Skills";
  public static final String ENDURANCE = "Endurance";
  public static final String STRENGTH_FEAT = "Strength Feat";
  public static final String SWIMMING = "Swimming";

  // Cool Skills
  public static final String COOL_SKILLS = "Cool Skills";
  public static final String INTERROGATION = "Interrogation";
  public static final String INTIMIDATE = "Intimidate";
  public static final String ORATORY = "Oratory";
  public static final String RESIST_TORTURE_AND_DRUGS = "Resist Torture and Drugs";
  public static final String STREETWISE = "Streetwise";

  // Empathy Skills
  public static final String EMPATHY_SKILLS = "Empathy Skills";
  public static final String HUMAN_PERCEPTION = "Human Perception";
  public static final String INTERVIEW = "Interview";
  public static final String LEADERSHIP = "Leadership";
  public static final String SEDUCTION = "Seduction";
  public static final String SOCIAL = "Social";
  public static final String PERSUASION = "Persuasion";
  public static final String PERFORM = "Perform";

  // Intelligence Skills
  public static final String INTELLIGENCE_SKILLS = "Intelligence Skills";
  public static final String ACCOUNTING = "Accounting";
  public static final String ANTHROPOLOGY = "Anthropology";
  public static final String AWARENESS = "Awareness";
  public static final String BIOLOGY = "Biology";
  public static final String BOTANY = "Botany";
  public static final String CHEMISTRY = "Chemistry";
  public static final String COMPOSITION = "Composition";
  public static final String DIAGNOSE_ILLNESS = "Diagnose Illness";
  public static final String EDUCATION = "Education";
  public static final String EXPERT = "Expert";
  public static final String GAMBLE = "Gamble";
  public static final String GEOLOGY = "Geology";
  public static final String HIDE_AND_EVADE = "Hide and Evade";
  public static final String HISTORY = "History";
  public static final String LIBRARY_SEARCH = "Library Search";
  public static final String MATHEMATICS = "Mathematics";
  public static final String PHYSICS = "Physics";
  public static final String PROGRAMMING = "Programming";
  public static final String SHADOW_AND_TRACK = "Shadow and Track";
  public static final String STOCK_MARKET = "Stock Market";
  public static final String SYSTEM_KNOWLEDGE = "System Knowledge";
  public static final String TEACHING = "Teaching";
  public static final String WILDERNESS_SURVIVAL = "Wilderness Survival";
  public static final String ZOOLOGY = "Zoology";
  // Languages
  public static final String ARABIC = "Known Language: Arabic";
  public static final String BENGALI = "Known Language: Bengali";
  public static final String BHOJPURI = "Known Language: Bhojpuri";
  public static final String ENGLISH = "Known Language: English";
  public static final String FRENCH = "Known Language: French";
  public static final String GERMAN = "Known Language: German";
  public static final String GUJARATI = "Known Language: Gujarati";
  public static final String HAUSA = "Known Language: Hausa";
  public static final String HINDI = "Known Language: Hindi";
  public static final String INDONESIAN = "Known Language: Indonesian";
  public static final String ITALIAN = "Known Language: Italian";
  public static final String JAPANESE = "Known Language: Japanese";
  public static final String JAVANESE = "Known Language: Javanese";
  public static final String KOREAN = "Known Language: Korean";
  public static final String LAHNDA = "Known Language: Lahnda";
  public static final String MANDARIN = "Known Language: Mandarin Chinese";
  public static final String MARATHI = "Known Language: Marathi";
  public static final String PERSIAN = "Known Language: Persian";
  public static final String PORTUGUESE = "Known Language: Portuguese";
  public static final String RUSSIAN = "Russian";
  public static final String SPANISH = "Known Language: Spanish";
  public static final String SWAHILI = "Known Language: Swahili";
  public static final String TAMIL = "Known Language: Tamil";
  public static final String TELUGU = "Known Language: Telugu";
  public static final String THAI = "Known Language: Thai";
  public static final String TURKISH = "Known Language: Turkish";
  public static final String URDU = "Known Language: Urdu";
  public static final String VIETNAMESE = "Known Language: Vietnamese";
  public static final String WU = "Known Language: Wu Chinese";
  public static final String YUE = "Known Language: Yue Chinese";

  // Reflex Skills
  public static final String REFLEX_SKILLS = "Reflex Skills";
  public static final String ARCHERY = "Archery";
  public static final String ATHLETICS = "Athletics";
  public static final String BRAWLING = "Brawling";
  public static final String DANCE = "Dance";
  public static final String DODGE_AND_ESCAPE = "Dodge and Escape";
  public static final String DRIVING = "Driving";
  public static final String FENCING = "Fencing";
  public static final String HANDGUN = "Handgun";
  public static final String HEAVY_WEAPONS = "Heavy Weapons";
  public static final String MELEE = "Melee";
  public static final String MOTORCYCLE = "Motorcycle";
  public static final String OPERATE_HEAVY_MACHINERY = "Operate Heavy Machinery";
  public static final String RIFLE = "Rifle";
  public static final String STEALTH = "Stealth";
  public static final String SUBMACHINEGUN = "Submachinegun";
  // Martial Arts
  public static final String MARTIAL_ARTS_AIKIDO = "Martial Arts: Aikido";
  public static final String MARTIAL_ARTS_ANIMAL_KUNG_FU = "Martial Arts: Animal Kung Fu";
  public static final String MARTIAL_ARTS_BOXING = "Martial Arts: Boxing";
  public static final String MARTIAL_ARTS_CAPOERIA = "Martial Arts: Capoeria";
  public static final String MARTIAL_ARTS_CHOI_LI_FUT = "Martial Arts: Choi Li Fut";
  public static final String MARTIAL_ARTS_JUDO = "Martial Arts: Judo";
  public static final String MARTIAL_ARTS_KARATE = "Martial Arts: Karate";
  public static final String MARTIAL_ARTS_TAE_KWON_DO = "Martial Arts: Tae Kwon Do";
  public static final String MARTIAL_ARTS_THAI_KICK_BOXING = "Martial Arts: Thai Kick Boxing";
  public static final String MARTIAL_ARTS_WRESTLING = "Martial Arts: Wrestling";
  // Piloting
  public static final String PILOT_GYRO = "Pilot: Gyro";
  public static final String PILOT_FIXED_WING = "Pilot: Fixed Wing";
  public static final String PILOT_DIRIGIBLE = "Pilot: Dirigible";
  public static final String PILOT_VECTORED_THRUST_VEHICLE = "Pilot: Vectored Thrust Vehicle";

  // Technical Skills
  public static final String TECHNICAL_ABILITY_SKILLS = "Technical Ability Skills";
  public static final String AERO_TECH = "Aero Tech";
  public static final String AV_TECH = "AV Tech";
  public static final String BASIC_TECH = "Basic Tech";
  public static final String CRYOTANK_OPERATION = "Cryotank Operation";
  public static final String CYBERDECK_DESIGN = "Cyberdeck Design";
  public static final String CYBERTECH = "Cybertech";
  public static final String DEMOLITIONS = "Demolitions";
  public static final String DISGUISE = "Disguise";
  public static final String ELECTRONICS = "Electronics";
  public static final String ELECTRONIC_SECURITY = "Electronic Security";
  public static final String FIRST_AID = "First Aid";
  public static final String FORGERY = "Forgery";
  public static final String GYRO_TECH = "Gyro Tech";
  public static final String PAINT_OR_DRAW = "Paint or Draw";
  public static final String PHOTO_AND_FILM = "Photo and Film";
  public static final String PHARMACEUTICALS = "Pharmaceuticals";
  public static final String PICK_LOCK = "Pick Lock";
  public static final String PICK_POCKET = "Pick Pocket";
  public static final String PLAY_INSTRUMENT = "Play Instrument";
  public static final String WEAPONSMITH = "Weaponsmith";

  /**
   * A constant used to identify when the level state changes.
   */
  public static final String PROPERTY_NAME_SKILL_LEVEL = "Skill Level";

  /**
   * A constant used to identify when the value changes.
   */
  public static final String PROPERTY_NAME_SKILL_VALUE = "Skill Value";

  /**
   * A constant used to identify when the improvementPoints state change.
   */
  public static final String PROPERTY_NAME_SKILL_IMPROVEMENT_POINTS = "Skill Improvement Points";

  /**
   * A constant used to identify when the isEnabled state changes.
   */
  public static final String PROPERTY_NAME_SKILL_IS_ENABLED = "Skill Is Enabled";

  /**
   * The lowest level that a skill is allowed to be at.
   */
  public static final int MIN_LEVEL = 0;

  /**
   * The highest level that a skill is allowed to be at.
   */
  public static final int MAX_LEVEL = 10;

  /**
   * The default multiplier used for the needed improvement points to reach the next level.
   */
  public static final int DEFAULT_DIFFICULTY_MODIFIER = 1;

  /**
   * The starting amount of improvement points for a skill.
   */
  public static final int INITIAL_IP = 0;

  /**
   * The starting amount of improvement points needed for a skill to automatically level up.
   */
  public static final int INITIAL_IP_GOAL = 10;

  /**
   * @return <code>true</code> if this <code>CyberpunkSkill</code> is modifiable
   */
  public boolean isEnabled();

  /**
   * @return the multiplier used to express the difficulty to learn a skill
   */
  public int getDifficultyModifier();

  /**
   * Adds a given amount of points to the current amount of improvement points.
   * 
   * @param improvementPoints the amount of points to be added to the current amount of improvement
   *        points
   */
  public void increaseCurrentImprovementPoints(int improvementPoints);

  /**
   * Removes a given amount of points from the current amount of improvement points.
   * 
   * @param improvementPoints the amount of points to be removed from the current amount of
   *        improvement points
   */
  public void decreaseCurrentImprovementPoints(int improvementPoints);

  /**
   * @return the current amount of improvement points
   */
  public int getCurrentImprovementPoints();

  /**
   * @return the improvement points needed to reach the next level based on the current level
   */
  public int getTargetImprovementPoints();

  /**
   * Adds a PropertyChangeListener to the listener list.
   * 
   * @param listener the PropertyChangeListener to be added
   */
  public void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Removes a PropertyChangeListener from the listener list.
   * 
   * @param listener the PropertyChangeListener to be removed
   */
  public void removePropertyChangeListener(PropertyChangeListener listener);

  /**
   * Adds a PropertyChangeListener for a specific property.
   * 
   * @param propertyName the name of the property to listen on
   * @param listener the PropertyChangeListener to be added
   */
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

  /**
   * Removes a PropertyChangeListener for a specific property.
   * 
   * @param propertyName the name of the property that was listened on
   * @param listener the PropertyChangeListener to be removed
   */
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

}
