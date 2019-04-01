package rpg.cyberpunk._2020.gui;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rpg.Player;

/**
 * A panel that shows the current weight and money available for a given player.
 * 
 * @author Coul Greer
 */
public class InventoryInfoPanel extends JPanel implements PropertyChangeListener {
	private Player player;
	private JLabel moneyLabel;
	private JLabel weightLabel;

	/**
	 * Constructs a panel with two labels each representing either currently held
	 * (or lack there of) money or the weight of their inventory and equipped items
	 * combined.
	 * 
	 * @param player
	 */
	public InventoryInfoPanel(Player player) {
		super(new GridLayout(1, 2));
		this.player = player;
		player.addPropertyChangeListener(Player.PROPERTY_NAME_MONEY, this);
		player.addPropertyChangeListener(Player.PROPERTY_NAME_INVENTORY_WEIGHT, this);

		moneyLabel = new JLabel(String.format("Available Money: $%01.2f", player.getMoney()));
		moneyLabel.setHorizontalAlignment(JLabel.CENTER);
		add(moneyLabel);

		weightLabel = new JLabel(String.format("Current Inventory Weight: %01.2f", player.getTotalWeight()));
		weightLabel.setHorizontalAlignment(JLabel.CENTER);
		add(weightLabel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		String propertyName = evt.getPropertyName();

		if (source == player) {
			if (Player.PROPERTY_NAME_MONEY.equals(propertyName)) {
				moneyLabel.setText(String.format("Available Money: $%01.2f", player.getMoney()));
			} else if (Player.PROPERTY_NAME_INVENTORY_WEIGHT.equals(propertyName)) {
				weightLabel.setText(String.format("Current Inventory Weight: %01.2f", player.getTotalWeight()));
			}
		}
	}

}
