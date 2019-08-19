package rpg.cyberpunk._2020.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RoleFactory {
  public static final String COP = "Cop";
  public static final String ROCKERBOY = "Rockerboy";
  public static final String SOLO = "Solo";
  public static final String MEDIA = "Media";
  public static final String NOMAD = "Nomad";
  public static final String NETRUNNER = "Netrunner";
  public static final String TECHIE = "Techie";
  public static final String MEDTECH = "Medtech";
  public static final String CORPORATE = "Corporate";
  public static final String FIXER = "Fixer";

  public static Iterator<String> createNameIterator() {
    List<String> names = Arrays.asList( //
        COP, //
        ROCKERBOY, //
        SOLO, //
        MEDIA, //
        NOMAD, //
        NETRUNNER, //
        TECHIE, //
        MEDTECH, //
        CORPORATE, //
        FIXER);

    return names.iterator();
  }

  public static Role createRole(String name) {
    Role role = null;
    List<List<String>> skillNameOptions = new ArrayList<List<String>>(Role.OPTION_COUNT);

    switch (name) {
      case ROCKERBOY:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.CHARISMATIC_LEADERSHIP));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PERFORM));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.WARDROBE_AND_STYLE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.COMPOSITION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BRAWLING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PLAY_INSTRUMENT));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.STREETWISE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PERSUASION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.SEDUCTION));

        role = new Role( //
            "Rockerboy",
            "If you live to rock, this is where you belong. Rockerboys are the street poets, social"
                + " consciences and rebels of the 2000's. With the advent of digital porta-studios and"
                + " garage laser disk mastering, every Rocker with a message can take it to the"
                + " street; put it in the record stores, bounce it off the comsats.\n\nSometimes, this"
                + " message isn't something the Corporations or the Government wants to hear."
                + " Sometimes what you say is going to get right in the faces of the powerful people"
                + " who really run this world. But you don't care, because as a Rockerboy you know"
                + " it's your place to challenge authority, whether in straight-out protest songs that"
                + " tell it like it is, or just by playing kick-ass rock n' roll to get the people"
                + " away from the TV sets and into the Streets. You have a proud history as a"
                + " Rockerboy--Dylan, Springsteen, Who, Elvis, the Stones--the legions of hardrock"
                + " heroes who told the truth with screaming guitars and gut-honest lyrics.\n\nAs a"
                + " Rockerboy, you have the power to get the people up--to lead, inspire and inform. A"
                + " song from you can give the timid courage, the weak strength, and the blind vision."
                + " Rockerboy legends have led armies against Corporations and Governments. Rockerboy"
                + " songs have exposed corruption, brought down dictators. It's a lot of power for a"
                + " guy doing gigs every night in another city. But you can handle it. After all--you"
                + " came to play!",
            skillNameOptions);
        break;
      case SOLO:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.COMBAT_SENSE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HANDGUN));
        skillNameOptions.add( //
            Arrays.asList( //
                CyberpunkSkill.BRAWLING, //
                CyberpunkSkill.MARTIAL_ARTS_AIKIDO, //
                CyberpunkSkill.MARTIAL_ARTS_ANIMAL_KUNG_FU, //
                CyberpunkSkill.MARTIAL_ARTS_BOXING, //
                CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, //
                CyberpunkSkill.MARTIAL_ARTS_CHOI_LI_FUT, //
                CyberpunkSkill.MARTIAL_ARTS_JUDO, //
                CyberpunkSkill.MARTIAL_ARTS_KARATE, //
                CyberpunkSkill.MARTIAL_ARTS_TAE_KWON_DO, //
                CyberpunkSkill.MARTIAL_ARTS_THAI_KICK_BOXING, //
                CyberpunkSkill.MARTIAL_ARTS_WRESTLING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.MELEE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.WEAPONSMITH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.RIFLE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ATHLETICS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.SUBMACHINEGUN));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.STEALTH));

        role = new Role( //
            SOLO,
            "You were re-born with a gun in your hand--the flesh and blood hand, not the metallic"
                + " weapons factory that covers most of your other arm. Whether as a freelance guard"
                + " and killer-for-hire, or as one of the Corporate cybersoldiers that enforce"
                + " business deals and the Company's \"black operations\", you're one of the elite"
                + " fighting machines of the Cyberpunk world.\n\nMost Solos have put in military time,"
                + " either in one of the Government's continual \"police actions\" around the world."
                + " As the battle damage piles up, you start to rely more and more upon"
                + " hardware--cyberlimbs for weapons and armor, bio-program chips to increase your"
                + " reflexes and awareness, combat drugs to give you that edge over your opponents."
                + " When you're the best of the best, you might even leave the ranks of Corporate"
                + " samurai and go ronin--freelancing your lethal talents as killer, bodyguard or"
                + " enforcer to whoever can pay your very high fees.\n\nSounds good? There's a"
                + " price--a heavy one. You've lost so much of your original meat body that you're"
                + " almost a machine. Your killing reflexes are so jacked up that you have to restrain"
                + " yourself from going beserk at any moment. Years of combat drugs taken to keep the"
                + " edge have given you terrifying addictions. You can't trust anyone--your mother,"
                + " your friends, your lovers--no one. One night you sleep in a penthouse condo in the"
                + " City--the next in a filthy alley on the Street. But that's the price of being the"
                + " best.\n\nAnd you're willing to pay it. Because you're a Solo.",
            skillNameOptions);
        break;
      case NETRUNNER:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.INTERFACE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BASIC_TECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.EDUCATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.SYSTEM_KNOWLEDGE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.CYBERTECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.CYBERDECK_DESIGN));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.COMPOSITION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ELECTRONICS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PROGRAMMING));

        role = new Role( //
            NETRUNNER,
            "At three, your parents bought you an old Apple IV GS with a Radius 241 wall screen, and"
                + " your life was changed. By fifth grade you'd already mastered everything the school"
                + " computer literacy lab could throw at you--you were already using C and META-LINGUA"
                + " to crack into the district's mainframe and change your grades. When you were"
                + " thirteen you shifted enough funds out of unprotected TransAmerican Bank accounts"
                + " to finanace your first neural interface plugs.\n\nNow, nothing can stop you. With"
                + " your direct mental link to the computer, you can plunge headfirst into the"
                + " dizzying data-winds of the Net; the worldwide telecommunications system that joins"
                + " humanity together. As an electronic wraith, you are the ultimate \"hacker\", your"
                + " brain wired into special modems and computer links. You slip into the \"hardest\""
                + " mainframe systems with ease. Your defense and offense programs are arrayed at a"
                + " touch of your mental fingertips--a quick jolt of Demon or Vampire and the data"
                + " fortresses fall. EBM. ITT. Sony-Matsushita-Ford. You've tackled them all, buying,"
                + " trading and selling their deepest secrets at will.\n\nSometimes you uncover"
                + " important things--Corporate treachery or deadly secrets. But that's not why you"
                + " Netrun. You live for the new program, the next satellite downlink--the next piece"
                + " of hot data that comes your way. It's only a matter of time, you think--every"
                + " year, the counter intrustion programs get better, the Artificial Intelligences"
                + " smarter. Sooner or later, a faster program or programmer's going to catch up;"
                + " reach out with electronic fingers through your interface plugs, and stop your"
                + " heart. But time's on your side, and until the ride runs out, you'll be there,"
                + " barebrained and headfirst in the Net.",
            skillNameOptions);
        break;
      case TECHIE:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.JURY_RIG));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BASIC_TECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.CYBERTECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.TEACHING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.EDUCATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ELECTRONICS));
        skillNameOptions.add( //
            Arrays.asList( //
                CyberpunkSkill.GYRO_TECH, //
                CyberpunkSkill.AERO_TECH, //
                CyberpunkSkill.WEAPONSMITH, //
                CyberpunkSkill.ELECTRONIC_SECURITY));
        skillNameOptions.add( //
            Arrays.asList( //
                CyberpunkSkill.GYRO_TECH, //
                CyberpunkSkill.AERO_TECH, //
                CyberpunkSkill.WEAPONSMITH, //
                CyberpunkSkill.ELECTRONIC_SECURITY));
        skillNameOptions.add( //
            Arrays.asList( //
                CyberpunkSkill.GYRO_TECH, //
                CyberpunkSkill.AERO_TECH, //
                CyberpunkSkill.WEAPONSMITH, //
                CyberpunkSkill.ELECTRONIC_SECURITY));

        role = new Role( //
            TECHIE,
            "You can't leave anything alone--if it sits near you for more than five minutes, you've"
                + " disassembled it and made it into something new. You've always got at least two"
                + " screwdrivers and a wrench in your pockets. Computer down? No problem. Hydrogen"
                + " burner out in your Metrocar? No problem. Can't get the video to run or your"
                + " interface plugs feedbacking? No problem.\n\nYou make your living building, fixing"
                + " and modifying--a crucial occupation in a technological world where no one person"
                + " really knows how half the stuff works. You can make some good bucks fixing"
                + " everyday stuff, but for the serious moneym you need to tackle the big jobs."
                + " Illegal weapons. Illegal or stolen cybertech. Corporate espionage and"
                + " counterespionage gear for the big boys' \"black operations\". Neat little gadgets"
                + " like thermite bombs and hunter-killer robots for the occasional \"termination\".\n"
                + "\nIf you're any good, you're making a lot of money. And that money goes into new"
                + " gadgets, hardware and information. You'll buy almost any new thing--because it"
                + " might have a dozen side applications you can use. Of course, your black market"
                + " work isn't just making you friends--it's also racking you up an impressive number"
                + " of enemies as well; people who've run into your handiwork and resented it. So"
                + " you'll invest a lot in defense systems and, if really pushed into a wall, call in"
                + " a few markers on a Solo or two.\n\nYour cousin down the street is just like you,"
                + " but he's a Medtechie. In a world where half of medicine is related to mechanics,"
                + " it makes sense. He can do a black market surgical technique faster than you can"
                + " fix a toaster, and the Solos are always running to him to patch up wounds or"
                + " install new illegal cybernetics. He's got a lot of the same problems you have, but"
                + " he's hoping his new job with Trauma Team Inc. will loosen things up. You hope he's"
                + " right. You may be needing his services sooner than you think.",
            skillNameOptions);
        break;
      case MEDTECH:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.MEDICAL_TECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BASIC_TECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.DIAGNOSE_ILLNESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.EDUCATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.CRYOTANK_OPERATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.LIBRARY_SEARCH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PHARMACEUTICALS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ZOOLOGY));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HUMAN_PERCEPTION));

        role = new Role( //
            MEDTECH,
            "You can't leave anything alone--if it sits near you for more than five minutes, you've"
                + " disassembled it and made it into something new. You've always got at least two"
                + " screwdrivers and a wrench in your pockets. Computer down? No problem. Hydrogen"
                + " burner out in your Metrocar? No problem. Can't get the video to run or your"
                + " interface plugs feedbacking? No problem.\n\nYou make your living building, fixing"
                + " and modifying--a crucial occupation in a technological world where no one person"
                + " really knows how half the stuff works. You can make some good bucks fixing"
                + " everyday stuff, but for the serious moneym you need to tackle the big jobs."
                + " Illegal weapons. Illegal or stolen cybertech. Corporate espionage and"
                + " counterespionage gear for the big boys' \"black operations\". Neat little gadgets"
                + " like thermite bombs and hunter-killer robots for the occasional \"termination\".\n"
                + "\nIf you're any good, you're making a lot of money. And that money goes into new"
                + " gadgets, hardware and information. You'll buy almost any new thing--because it"
                + " might have a dozen side applications you can use. Of course, your black market"
                + " work isn't just making you friends--it's also racking you up an impressive number"
                + " of enemies as well; people who've run into your handiwork and resented it. So"
                + " you'll invest a lot in defense systems and, if really pushed into a wall, call in"
                + " a few markers on a Solo or two.\n\nYour cousin down the street is just like you,"
                + " but he's a Medtechie. In a world where half of medicine is related to mechanics,"
                + " it makes sense. He can do a black market surgical technique faster than you can"
                + " fix a toaster, and the Solos are always running to him to patch up wounds or"
                + " install new illegal cybernetics. He's got a lot of the same problems you have, but"
                + " he's hoping his new job with Trauma Team Inc. will loosen things up. You hope he's"
                + " right. You may be needing his services sooner than you think.",
            skillNameOptions);
        break;
      case MEDIA:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.CREDIBILITY));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.COMPOSITION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.EDUCATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PERSUASION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HUMAN_PERCEPTION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.SOCIAL));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.STREETWISE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PHOTO_AND_FILM));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.INTERVIEW));

        role = new Role( //
            MEDIA,
            "They're bending the truth out there. And you're going to stop them. Someone has to do"
                + " it. The Corporations rule the world. They dump toxics, destabilize economies"
                + " and commit murder with equal impunity. The Government won't stop them--they"
                + " own the Government. The only thing between the Corporations and world"
                + " domination is the Media. And that's you.\n\nYou've got a videocam and a press"
                + " pass--and you're not afraid to use them. You're a national figure, seen"
                + " nightly on a million TV sets worldwide. You've got fans, contracts and your"
                + " own Corporation backing you. They can't make you disappear. When you dig down"
                + " for the dirt and slime the corrupt officials and Corporate lapdogs try to"
                + " cover up, you can dig deep. The next morning, you can put the details of their"
                + " crimes all over the screamsheets and vidscreens. Then the Govbernment has to"
                + " act.\n\nA week ago, you followed a hot lead and discovered a medical"
                + " corporation dumping illegal drugs on the Street. This week, you're uncovering"
                + " a secret Corporate war in South America--a war with jets, bombs, and"
                + " cybertroops that's killed almost seven thousand innocent people. Eash new"
                + " story you get to the air is one more blow for freedom and justice. Not to"
                + " mention ratings.\n\nIt isn't easy. They've tried to pressure your Mediacorp"
                + " dozens of times. You've had stories suppressed--once, Corporate pressure"
                + " forced them to cancel your news show. Each time, you went to the top, backed"
                + " by your news director and your crew, and fought to get the story out. Three or"
                + " four times, they tried to kill you--that's why your backup's a crack Solo"
                + " bodyguard and you've got one of the top 'Runners in the business digging"
                + " through the Net to back your stories. You have to be good, or else.\n\nYour"
                + " 'Runner's just phoned in with a hot lead. He's found a line on twenty tons of"
                + " illegal weapons being shifted to a port in Bolivia--possibly nuclear. You grab"
                + " your gear and flag your backup. You're going to break thoses bastards.\n\nThis"
                + " time, for sure.",
            skillNameOptions);
        break;
      case COP:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AUTHORITY));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HANDGUN));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HUMAN_PERCEPTION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ATHLETICS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.EDUCATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BRAWLING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.MELEE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.INTERROGATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.STREETWISE));

        role = new Role( //
            COP,
            "In the old days, they only used to shoot at cops. Now you're lucky if you only take a"
                + " slug. The Street is mean these days, filled with new drugs, new gangs, and new"
                + " weapons that make an M-16 look like a kid's toy. If you're on a City Force,"
                + " you know how bad it is. You're carrying at least four high caliber weapons,"
                + " most of them full-auto types, wearing a Kevlar vest that'll stop 850 ft/lbs"
                + " per square inch--and you're still outgunned and outflanked. Half the gangs are"
                + " cyber to begin with--super speed, super reflexes, can see in the dark, carry"
                + " weapons in their arms... The other half are freelance Corporate mercs--gangs"
                + " hired by the Corps to enforce their policies on the Street. And there you"
                + " are--a beat cop or detective in an armored squadcar, patrolling this jungle"
                + " with the heavy predators.\n\nThe Corporate Cops--now that's the life. Heavy"
                + " weapons, full combat armor, Trauma Team to backup, AV-4 assault vehicles and"
                + " gyrocopters with miniguns. But they only patrol the sectors of the City that"
                + " the Government's licensed them for. The nice, clean sectors full of new office"
                + " buildings and fancy restaurants - where no jacked up psychopunk is going to"
                + " ever go on a killing spree with an AK-47. You get the bad sections. Burned out"
                + " buildings and abadoned cars, where every night is a new firefight and another"
                + " great opportunity for a messy death.\n\nIf you're really unlucky, you might"
                + " draw PsychoSqaud detail. PsychoSquad guys get the job of hunting down heavily"
                + " armed and armored cyborgs who've flipped out. Sure the PS guys have access to"
                + " railguns, gyros and AVs. But a cyberpsycho can walk through machine gun fire"
                + " and not feel it. A lot of the PsychoSquad detectives are crazy themselves."
                + " They load up with boosted reflexes, get some monstrously huge guns, and go"
                + " hunt the cyborgs solo. But you're not that crazy.\n\nYet.",
            skillNameOptions);
        break;
      case CORPORATE:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.RESOURCES));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HUMAN_PERCEPTION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.EDUCATION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.LIBRARY_SEARCH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.SOCIAL));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PERSUASION));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.STOCK_MARKET));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.WARDROBE_AND_STYLE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PERSONAL_GROOMING));

        role = new Role( //
            CORPORATE,
            "In the old days, they would have called you a yuppie--a hard driven, fast-track MBA"
                + " on his way up the Corporate ladder. Sure, it's selling your soul to the"
                + " Company, but lets face it, the Corporations rule the cyberpunk world. They"
                + " control governments, markets, nations, armies--you name it. You know that"
                + " whoever controls the Corporations controls everything else.\n\nRight now, your"
                + " life as a junior executive is anything but easy. There are guys underneath you"
                + " who'd kill for a shot at your job. There are guys over you who'd kill to keep"
                + " you out of their jobs. And they're not kidding about the killing--every up"
                + " and comer in the Corporation has his own crew of Solos and Netrunners to cover"
                + " his pet projects. Sabotage? Constantly. Bribery? Routine. Blackmail? Common."
                + " Promotion by assassination? Always a possibility. The stakes are that"
                + " high--one slip and you could be out on the Street with the rest of the hobos\n"
                + "\nAnd the projects your supervisors give you! Some are pretty straightforward;"
                + " design a new productivity schedule for the Corporation's medical subsidiary."
                + " Some are pretty raw--send a \"black operations\" team into the City to spread"
                + " a designer plague so the Marketing team can clean up selling the vaccine. Last"
                + " week, you led a mixed team of Solos, 'Runners and Techies on a headhunting run"
                + " to kidnap a researcher from a rival company. The week before, your project was"
                + " to steal plans for a new suborbital shuttle from the EuroSpace Agency (so that"
                + " the Aerospace Division could copy the design and sell it to the Soviets).\n\n"
                + "You told yourself you joined the Corporation to make it better place--work from"
                + " the inside, you said. But now you're not sure. Your ideals are a little"
                + " tarnished and things are getting pretty bleak. But you can't worry about"
                + " ethics now. You've got a report due in an hour, and it looks like that guy in"
                + " Sales is planning to ice your database for good. You're gonna ice him first.",
            skillNameOptions);
        break;
      case FIXER:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.STREETDEAL));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.FORGERY));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.HANDGUN));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BRAWLING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.MELEE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PICK_LOCK));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PICK_POCKET));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.INTIMIDATE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.PERSUASION));

        role = new Role( //
            FIXER,
            "You realized fast that you weren't ever going to get into a Corporate job. And you"
                + " didn't think your were tough enough or crazy enough to be a Solo either. But"
                + " as a small time punk, you knew you had a knack for figuring out what other"
                + " people wanted, and how to get it for them. For a price, of course.\n\nNow your"
                + " deals have moved past the nickle-and-dime stuff into the big time. Maybe you"
                + " move illegal weapons over the border. Or steal and resell medical supplies"
                + " from the Corporations. Perhaps you're a skill broker--acting as an agent for"
                + " high priced Solos and Runners or even hiring a whole Nomad pack to back a"
                + " client's contacts. You buy and sell favors like an old-style Mafia godfather."
                + " You have connections into all kinds of businesses, deals and political groups."
                + " You don't do this directly, of course--no, you see your contacts and allies as"
                + " a part of vast web of intrigue and coercion. If there's a hot nightclub in the"
                + " City, you've bought into it. If there are new military-class weapons on the"
                + " Street, you smuggled'em in. If there's a Corporate war going down, you're"
                + " negotiating between sides with an eye on the main chance.\n\nBut you're not"
                + " entirely in it for the bucks. If someone needs to get the heat off, you'll"
                + " hide them. You get people housing when there isn't any, and you bring in food"
                + " when the neighborhoods are blockaded. Maybe you do it because you know they'll"
                + " owe you later, but you're not sure. You're one part Robin Hood and two parts"
                + " Al Capone. Back in the 90's, they would have called you a crimelord. But this"
                + " is the fragmented, deadly 2020s. Now they call you a Fixer.",
            skillNameOptions);
        break;
      case NOMAD:
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.FAMILY));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.AWARENESS));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ENDURANCE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.MELEE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.RIFLE));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.DRIVING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BASIC_TECH));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.WILDERNESS_SURVIVAL));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.BRAWLING));
        skillNameOptions.add(Arrays.asList(CyberpunkSkill.ATHLETICS));

        role = new Role( //
            NOMAD,
            "They drove your family off the Farm ten years ago. The Corporations rolled in, took"
                + " over the land, and put rent-a-cops all over the place. It wasn't the first"
                + " time it'd happened and it wouldn't be the last. Gradually, your family fell in"
                + " with a bunch of other homeless families, and they met another group... until"
                + " you'd create a Nomad pack of nearly two hundred members.\n\nNow, crammed into"
                + " a huge, ragtag fleet of cars, vans, busses and RV's, your Nomad pack roams the"
                + " freeways. You look for supplies, odd jobs and spare parts in the world where"
                + " society has fragmented. The pack is your home--it has teachers, Med Techs,"
                + " leaders, and mechanics--it's virtually a town on wheels in which everyone is"
                + " related by marriage or kinship. Sometimes the Pack pulls into a town just to"
                + " fuel up or get grub. Other times, it swings south to follow the harvest; you"
                + " pick crops in trade for cash or food. Less terrorizing cities and hiring out"
                + " as muscle in Corporate wars. For obvious reasons, the cops don't like Nomads."
                + " But it doesn't matter - your vehicle are usually well armored and bristling"
                + " with stolen weapons; mini guns, rocket launchers and the like. Every kid knows"
                + " how to use rifle, and everyone packs a knife. Being homeless in the 2000's"
                + " isn't easy.\n\nThe most visible members of the Pack are the Scouts - leather"
                + " armored riders on bikes or in fast muscle cars, who protect the convoy from"
                + " attacks and hunt up safe campsites. As a Scout, you're on the lookout for"
                + " trouble, and you usually can find enough of it, with rival Nomad Packs, the"
                + " Law, and the cowboy, you ride the hard trail. You've got a gun, a bike and"
                + " that's all you need. You're a Nomad.",
            skillNameOptions);
        break;
      default:
        break;
    }

    return role;
  }

}
