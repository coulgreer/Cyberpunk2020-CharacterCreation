package rpg.general.commerce;

public interface ShoppingCommand {
	/**
	 * Executes a given process specified by implementer.
	 */
	public void execute();

	/**
	 * Undoes <code>execute()</code> action. Does nothing if no action to undo.
	 */
	public void undo();
}
