package rpg.general.combat;

public interface Protective {
	/**
	 * Returns the type of this protective. For example, the type can be: light,
	 * medium, or heavy OR soft or hard in other cases.
	 * 
	 * @return the type of this protective
	 */
	public String getArmorType();

	/**
	 * Returns whether or not this protective covers the given
	 * <code>BodyLocation</code>. This can be used to show protection value at a
	 * certain area, or this can be ignored all together if using rules such as
	 * Armor Class (AC) which uses the combined AC of all gear to show a miss or hit
	 * instead of decreasing the amount of incoming damage on hit.
	 * 
	 * @param location the area that may be covered by this protective
	 * @return <code>true</code>, if this protective covers the given BodyLocation
	 */
	public boolean isCovering(BodyLocation location);

	/**
	 * Returns the burden of this protective. The burden of this protective usually
	 * reflects as lower base stats and disadvantage to certain actions.
	 * 
	 * @return the score used to show the burden of this protective
	 */
	public int getEncumbranceValue();

	/**
	 * Returns the number value assigned to this protective that can later be used
	 * to prevent or decrease incoming damage. This is also the max score if
	 * durability is implemented.
	 * 
	 * @return a score representing how effective this protective piece is
	 */
	public int getProtectionScore();

	/**
	 * Returns the durability at the given <code>BodyLocation</code> (optional
	 * operation). The returned durability represents the current protection score
	 * out of the initial protection score.
	 * 
	 * @param location the desired <code>BodyLocation</code> to be checked
	 * @return the current protection score at a given location
	 */
	public int getDurabilityAt(BodyLocation location);

	/**
	 * Decrements the durability of this protective in the given location (optional
	 * operation).
	 * 
	 * @param location     the desired location to decrement
	 * @param damagePoints the amount of points to remove from the durability of
	 *                     this protective
	 */
	public void damage(BodyLocation location, int damagePoints);

	/**
	 * Increments the durability of this protective in the given location (optional
	 * operation).
	 * 
	 * @param location     the desired location to increment
	 * @param repairPoints the amount of points to add to the durability of this
	 *                     protective
	 */
	public void repair(BodyLocation location, int repairPoints);

	/**
	 * Decrements the durability of this protective in all locations (optional
	 * operation).
	 * 
	 * @param damagePoints the amount of points to remove from the durability of
	 *                     this protective
	 */
	public void damageAll(int damagePoints);

	/**
	 * Increments the durability of this protective in all locations (optional
	 * operation).
	 * 
	 * @param repairPoints the amount of points to add to the durability of this
	 *                     protective
	 */
	public void repairAll(int repairPoints);

}
