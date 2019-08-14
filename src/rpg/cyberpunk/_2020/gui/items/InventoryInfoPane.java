package rpg.cyberpunk._2020.gui.items;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import rpg.cyberpunk._2020.Player;

/**
 * A panel that shows the current weight and money available for a given player.
 */
public class InventoryInfoPane extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private Player player;
	private JLabel moneyLabel;
	private JLabel weightLabel;

	/**
	 * Constructs a panel with two labels each representing either currently held
	 * (or lack there of) money or the weight of their inventory and equipped items
	 * combined.
	 * 
	 * @param player the owner of the inventory used to get the displayed
	 *               information such as: weight and money
	 */
	public InventoryInfoPane(Player player) {
		super(new GridLayout(1, 2));
		this.player = player;
		player.addPropertyChangeListener(Player.PROPERTY_NAME_MONEY, this);
		player.addPropertyChangeListener(Player.PROPERTY_NAME_INVENTORY_WEIGHT, this);

		moneyLabel = new JLabel(generateFormattedMoney(player));
		moneyLabel.setHorizontalAlignment(JLabel.CENTER);
		add(moneyLabel);

		weightLabel = new JLabel(generateFormattedWeight(player));
		weightLabel.setHorizontalAlignment(JLabel.CENTER);
		add(weightLabel);
	}

	private String generateFormattedMoney(Player player) {
		return String.format("Available Money: $%01.2f", player.getMoney());
	}

	private String generateFormattedWeight(Player player) {
		return "Current Inventory Weight: " + player.getTotalWeight();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		String propertyName = evt.getPropertyName();

		if (source == player) {
			if (Player.PROPERTY_NAME_MONEY.equals(propertyName)) {
				moneyLabel.setText(generateFormattedMoney(player));
			} else if (Player.PROPERTY_NAME_INVENTORY_WEIGHT.equals(propertyName)) {
				weightLabel.setText(generateFormattedWeight(player));
			}
		}
	}

}
