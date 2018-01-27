package core;

import java.awt.EventQueue;

import controller.CharacterCreationController;
import model.CharacterCreationModel;
import view.CharacterCreationView;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterCreationModel characterModel = new CharacterCreationModel();
					CharacterCreationView characterView = new CharacterCreationView();
					CharacterCreationController characterController = new CharacterCreationController(characterModel, characterView);
					characterView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
