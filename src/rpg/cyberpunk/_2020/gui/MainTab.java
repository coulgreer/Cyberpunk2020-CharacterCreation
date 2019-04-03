package rpg.cyberpunk._2020.gui;

import javax.swing.JTabbedPane;

import rpg.Player;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.commerce.VendorTrader;

/**
 * A tabbed panel used to hold all other panels that a user can navigate to.
 * Used to keep other classes clean that use this object.
 * 
 * @author Coul Greer
 */
public class MainTab extends JTabbedPane {

	/**
	 * Constructs a tabbed pane with a default set of tabs.
	 * 
	 * @param player the object used to derive information to display on the GUI
	 */
	public MainTab(Player player) {
		CyberpunkVendor vendor = new CyberpunkVendor(new VendorTrader());

		addTab("Inventory", new InventoryTab(player, vendor));
		addTab("Shop", new ShopTab(player, vendor));
	}

}
