package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import rpg.Player;

/**
 * A panel used to keep all the inventory GUI elements organized for layout
 * mangers.
 * 
 * @author Coul Greer
 */
public class InventoryTab extends JPanel {

	/**
	 * Constructs a panel filled with elements needed under the 'Inventory' tab.
	 * 
	 * @param player the object from which data is drawn from and passed to while
	 *               also displaying its contents to the GUI
	 */
	public InventoryTab(Player player) {
		super(new BorderLayout());

		JPanel infoPanel = new InventoryInfoPanel(player);
		add(infoPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.addTab("Weapons", new InventoryWeaponCategoryPanel(player));
		add(tabbedPane, BorderLayout.CENTER);
	}

}
