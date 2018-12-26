package rpg.general.commerce;

public class Cashier {
	private ShoppingCommand command;
	
	public Cashier() {
		command = new NullCommand();
	}

	public void setCommand(ShoppingCommand command) {
		this.command = command;
	}

	public void checkout() {
		command.execute();
	}
}
