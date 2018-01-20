package core;

import java.awt.EventQueue;

import controller.CharacterCreationController;
import model.Character;
import view.CharacterCreationView;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Character character = new Character();
					CharacterCreationView frame = new CharacterCreationView();
					CharacterCreationController controller = new CharacterCreationController(character, frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
