package rpg.cyberpunk._2020;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import rpg.Player;
import rpg.cyberpunk._2020.gui.MainMenuBar;
import rpg.cyberpunk._2020.gui.MainTab;

public class CharacterCreation {
	public static void main(String arg[]) {
		Player player = new Player();

		JFrame frame = new JFrame("Cyberpunk 2020 Character Creator");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(new MainMenuBar());
		frame.add(new MainTab(player), BorderLayout.CENTER);

		frame.setSize(new Dimension(1280, 720));
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
