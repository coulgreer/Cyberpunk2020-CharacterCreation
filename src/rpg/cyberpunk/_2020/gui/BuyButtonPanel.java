package rpg.cyberpunk._2020.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;

/**
 * A panel that provides housing for the buy button and the default presentation
 * of its immediate children.
 * 
 * @author Coul Greer
 */
public class BuyButtonPanel extends JPanel {
	private Player player;
	private CyberpunkVendor vendor;
	private JTable table;

	/**
	 * Constructs a panel used to house a button that allows the user to purchase
	 * the current selection on the presented table.
	 * 
	 * @param player the purchaser of an item
	 * @param vendor the seller of an item. Used to get cost of a selected table
	 *               element.
	 * @param table  the object holding a collection of items to be bought
	 */
	public BuyButtonPanel(Player player, CyberpunkVendor vendor, JTable table) {
		this.player = player;
		this.vendor = vendor;
		this.table = table;

		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(e -> buy());

		add(buyButton);
	}

	private void buy() {
		int selectedRowIndex = table.getSelectedRow();
		int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

		if (actualSelectedRowIndex >= 0) {
			CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
					ShopWeaponCategoryTable.ShopWeaponTableModel.OBJECT_INDEX);

			player.buy(vendor.sellWeapon(weapon), vendor.getAskPrice(weapon));
		}
	}

}
