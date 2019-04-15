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
	 * Constructs a panel used to house a button that allows the user to purchase an
	 * item.
	 * 
	 * @param listener the listener task executed upon clicking the buy button
	 */
	public BuyButtonPanel(ActionListener listener) {
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(listener);

		add(buyButton);
	}

}
