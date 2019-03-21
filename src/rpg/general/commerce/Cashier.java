package rpg.general.commerce;

import java.util.Stack;

public class Cashier {
	private Stack<ShoppingCommand> commands;
	private ShoppingCommand command;

	public Cashier() {
		command = new NullCommand();
		commands = new Stack<ShoppingCommand>();
	}

	/**
	 * Registers a command to be later used when <code>checkout()</code> is called.
	 * <code>command</code> will later be used
	 * 
	 * @param command the action to be set for future calls to
	 *                <code>checkout()</code>.
	 */
	public void setCommand(ShoppingCommand command) {
		this.command = command;
	}

	/**
	 * Pushes the current command onto a stack and calls the command's
	 * <code>execute()</code>.
	 */
	public void checkout() {
		commands.push(command);
		command.execute();
	}

	/**
	 * Pops a command off the stack and calls its <code>undo()</code> if the stack
	 * is not empty.
	 */
	public void undoCheckout() {
		if (!commands.isEmpty()) {
			commands.pop().undo();
		}
	}
}
