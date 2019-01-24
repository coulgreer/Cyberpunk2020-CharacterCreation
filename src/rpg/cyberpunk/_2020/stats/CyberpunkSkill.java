package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Skill;

public interface CyberpunkSkill extends Skill, SkillVisitable {
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
	public static final String KNOWN_LANGUAGE = "Known Language";
	public static final String PERSIAN = "Perian";
	public static final String GREEK = "Greek";
	public static final String JAPANESE = "Japanese";
	public static final String KOREAN = "Korean";
	// Niger-Congo Languages
	public static final String NIGER_CONGO = "Niger-Congo";
	public static final String SHONA = "Shona";
	public static final String AKAN = "Akan";
	public static final String KONGO = "Kongo";
	public static final String ZULU = "Zulu";
	public static final String SWAHILI = "Swahili";
	// Baltic Languages
	public static final String BALTIC = "Baltic";
	public static final String LATVIAN = "Latvian";
	public static final String LITHUANIAN = "Lithuanian";
	// Finnic Languages
	public static final String FINNIC = "Finnic";
	public static final String ESTONIAN = "Estonian";
	public static final String FINNISH = "Finnish";
	// Celtic Languages
	public static final String CELTIC = "Celtic";
	public static final String GAELIC = "Gaelic";
	public static final String WELSH = "Welsh";
	public static final String BRETON = "Breton";
	// Germanic Languages
	public static final String GERMANIC = "Germanic";
	public static final String DANISH = "Danish";
	public static final String DUTCH = "Dutch";
	public static final String ENGLISH = "English";
	public static final String GERMAN = "German";
	public static final String NORWEGIAN = "Norwegian";
	public static final String SWEDISH = "Swedish";
	public static final String YIDDISH = "Yiddish";
	// Pacific Island Languages
	public static final String PACIFIC_ISLAND_GROUP = "Pacific Island Group";
	public static final String CHUUKESE = "Chuukese";
	public static final String TAGALOG = "Tagalog";
	public static final String SAMOAN = "Samoan";
	public static final String JAVANESE = "Javanese";
	public static final String MALAY = "Malay";
	public static final String SUNDANESE = "Sundanese";
	public static final String INDONESIAN = "Indonesian";
	public static final String HAWAIIAN = "Hawaiian";
	// Romantic Languages
	public static final String ROMANTIC = "Romantic";
	public static final String FRENCH = "French";
	public static final String ITALIAN = "Italian";
	public static final String SPANISH = "Spanish";
	public static final String PORTUGUESE = "Portuguese";
	public static final String LATIN = "Latin";
	// Semetic Languages
	public static final String SEMETIC = "Semetic";
	public static final String ARABIC = "Arabic";
	public static final String HEBREW = "Hebrew";
	// Sino Tibetan and SE Asian Languages
	public static final String SINO_TIBETAN_AND_SE_ASIAN = "Sino-Tibetan and SE Asian";
	public static final String BURMESE = "Burmese";
	public static final String CANTONESE = "Cantonese";
	public static final String MANDARIN = "Mandarin";
	public static final String THAI = "Thai";
	public static final String TIBETAN = "Tibetan";
	public static final String VIETNAMESE = "Vietnamese";
	// Slavic Languages
	public static final String SLAVIC = "Slavic";
	public static final String BULGARIAN = "Bulgarian";
	public static final String RUSSIAN = "Russian";
	public static final String CZECH = "Czech";
	public static final String POLISH = "Polish";
	public static final String UKRANIAN = "Ukranian";
	public static final String SLOVAK = "Slovak";

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
	public static final String MARTIAL_ART = "Martial Art";
	public static final String AIKIDO = "Aikido";
	public static final String ANIMAL_KUNG_FU = "Animal Kung Fu";
	public static final String BOXING = "Boxing";
	public static final String CAPOERIA = "Capoeria";
	public static final String CHOI_LI_FUT = "Choi Li Fut";
	public static final String JUDO = "Judo";
	public static final String KARATE = "Karate";
	public static final String TAE_KWON_DO = "Tae Kwon Do";
	public static final String THAI_KICK_BOXING = "Thai Kick Boxing";
	public static final String WRESTLING = "Wrestling";
	// Piloting
	public static final String PILOTING = "Piloting";
	public static final String PILOT_GYRO = "Pilot Gyro";
	public static final String PILOT_FIXED_WING = "Pilot Fixed Wing";
	public static final String PILOT_DIRIGIBLE = "Pilot Dirigible";
	public static final String PILOT_VECTORED_THRUST_VEHICLE = "Pilot Vectored Thrust Vehicle";

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

	public static final int MIN_LEVEL = 0;
	public static final int MAX_LEVEL = 10;
	public static final int INITIAL_VALUE = 0;
	public static final int INITIAL_IP = 0;
	public static final int INITIAL_IP_GOAL = 10;

	public void increaseCurrentImprovementPoints(int improvementPoints);

	public int getCurrentImprovementPoints();

	public int getNeededImprovementPoints();
}
