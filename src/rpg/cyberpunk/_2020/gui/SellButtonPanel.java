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
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a panel used to house a button that allows the user to sell an
	 * item
	 * 
	 * @param sellListener the listener task executed upon clicking the sell button
	 */
	public SellButtonPanel(ActionListener sellListener, ActionListener equipListener) {
		JButton sellButton = new JButton("Sell");
		sellButton.addActionListener(sellListener);
		add(sellButton);
	}

}
