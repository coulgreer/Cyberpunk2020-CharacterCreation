package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import rpg.Player;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.commerce.VendorTrader;

/**
 * A panel used to hold all elements used to display all items that a vendor can
 * sell.
 * 
 * @author Coul Greer
 */
public class ShopTab extends JPanel {

	/**
	 * Constructs a panel that helps organize elements used for the shopping GUI
	 * experience.
	 * 
	 * @param player the object that purchases items from the vendor
	 */
	public ShopTab(Player player) {
		super(new BorderLayout());
		CyberpunkVendor vendor = new CyberpunkVendor(new VendorTrader());

		JPanel infoPanel = new InventoryInfoPanel(player);
		add(infoPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.addTab("Weapons", new ShopWeaponCategoryPanel(player, vendor));
		add(tabbedPane, BorderLayout.CENTER);
	}

}
