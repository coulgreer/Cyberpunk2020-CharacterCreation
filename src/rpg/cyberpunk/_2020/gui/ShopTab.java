package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.commerce.VendorTrader;
import rpg.cyberpunk._2020.gui.ShopArmorCategoryTable.ShopArmorTableModel;
import rpg.cyberpunk._2020.gui.ShopWeaponCategoryTable.ShopWeaponTableModel;

/**
 * A panel used to hold all elements used to display all items that a vendor can
 * sell.
 * 
 * @author Coul Greer
 */
public class ShopTab extends JPanel {
	private Player player;
	private CyberpunkVendor vendor;

	/**
	 * Constructs a panel that helps organize elements used for the shopping GUI
	 * experience.
	 * 
	 * @param player the object that purchases items from the vendor
	 */
	public ShopTab(Player player, CyberpunkVendor vendor) {
		super(new BorderLayout());
		this.player = player;
		this.vendor = vendor;

		JPanel infoPanel = new InventoryInfoPanel(player);
		add(infoPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		add(tabbedPane, BorderLayout.CENTER);

		JTable weaponTable = new ShopWeaponCategoryTable(vendor.getStoredWeapons());
		tabbedPane.addTab("Weapons", new ShopCategoryPanel(weaponTable, evt -> buyWeapon(weaponTable)));

		JTable armorTable = new ShopArmorCategoryTable(vendor.getStoredArmors());
		tabbedPane.addTab("Armors", new ShopCategoryPanel(armorTable, evt -> buyArmor(armorTable)));

	}

	private void buyWeapon(JTable table) {
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex != -1) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			if (actualSelectedRowIndex >= 0) {
				CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
						ShopWeaponTableModel.OBJECT_INDEX);

				player.buy(vendor.sellWeapon(weapon), vendor.getAskPrice(weapon));
			}
		}
	}

	private void buyArmor(JTable table) {
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex != -1) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			if (actualSelectedRowIndex >= 0) {
				CyberpunkArmor armor = (CyberpunkArmor) table.getModel().getValueAt(actualSelectedRowIndex,
						ShopArmorTableModel.OBJECT_INDEX);

				player.buy(vendor.sellArmor(armor), vendor.getAskPrice(armor));
			}
		}
	}

}
