package rpg.general.commerce;

public interface ShoppingCommand {
	/**
	 * Executes a given process specified by implementer.
	 */
	public void execute();

	/**
	 * Undoes <code>execute()</code> action.
	 */
	public void undo();
}
