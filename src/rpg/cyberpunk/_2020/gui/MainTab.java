package rpg.cyberpunk._2020.gui;

import javax.swing.JTabbedPane;

import rpg.Player;

public class MainTab extends JTabbedPane {

	public MainTab(Player player) {
		addTab("Shop", new ShopTab(player));
	}

}
