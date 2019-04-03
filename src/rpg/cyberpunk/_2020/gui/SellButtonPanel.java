package rpg.cyberpunk._2020.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel that provides housing for the sell button and the default
 * presentation of its immediate children.
 * 
 * @author Coul Greer
 */
public class SellButtonPanel extends JPanel {

	/**
	 * Constructs a panel used to house a button that allows the user to sell the
	 * current selection on the presented table.
	 * 
	 * @param player the seller of an item
	 * @param vendor the purchaser of an item. Used to get cost of a selected table
	 *               element.
	 * @param table  the object holding a collection of items to be bought
	 */
	public SellButtonPanel(ActionListener listener) {
		JButton sellButton = new JButton("Sell");
		sellButton.addActionListener(listener);

		add(sellButton);
	}

}
