package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import rpg.Player;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.commerce.VendorTrader;

public class ShopTab extends JPanel {

	public ShopTab(Player player) {
		super(new BorderLayout());
		CyberpunkVendor vendor = new CyberpunkVendor(new VendorTrader());

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.addTab("Weapons", new ShopWeaponCategoryPanel(player, vendor));
		add(tabbedPane, BorderLayout.CENTER);
	}

}
