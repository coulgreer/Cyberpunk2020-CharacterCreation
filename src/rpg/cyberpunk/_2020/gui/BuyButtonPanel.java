package rpg.cyberpunk._2020.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;

public class BuyButtonPanel extends JPanel {
	private Player player;
	private CyberpunkVendor vendor;
	private JTable table;

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
					ShopWeaponCategoryTable.WeaponShopTableModel.OBJECT_INDEX);

			player.buy(vendor.sellWeapon(weapon), vendor.getAskPrice(weapon));
		}
	}

}
