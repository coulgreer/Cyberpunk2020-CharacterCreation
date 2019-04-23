package rpg.cyberpunk._2020.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MainMenuBar() {
		JMenu saveLoadMenu = new JMenu("Save/Load");

		// TODO (Coul Greer): Add the option to save or load

		add(saveLoadMenu);
	}

}
