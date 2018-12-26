package rpg.general.combat;

public interface Modifier {
	public static final String MISCELLANEOUS = "Miscellaneous";
	
	public String getType();

	public String getBonus();
}
