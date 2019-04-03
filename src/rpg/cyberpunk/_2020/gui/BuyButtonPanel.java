package rpg.cyberpunk._2020.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel that provides housing for the buy button and the default presentation
 * of its immediate children.
 * 
 * @author Coul Greer
 */
public class BuyButtonPanel extends JPanel {

	/**
	 * Constructs a panel used to house a button that allows the user to purchase
	 * the current selection on the presented table.
	 * 
	 * @param player the purchaser of an item
	 * @param vendor the seller of an item. Used to get cost of a selected table
	 *               element.
	 * @param table  the object holding a collection of items to be bought
	 */
	public BuyButtonPanel(ActionListener listener) {
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(listener);

		add(buyButton);
	}

}
