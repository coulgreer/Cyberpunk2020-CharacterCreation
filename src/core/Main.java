package core;

import controller.CharacterCreationController;
import model.Character;
import view.CharacterCreationPage;

public class Main {

	public static void main(String[] args) {
		Character character = new Character();
		CharacterCreationPage page = new CharacterCreationPage();
		
		page.setVisible(true);
		page.initializeGUI();
	}

}
