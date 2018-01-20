package controller;

import model.Character;
import view.CharacterCreationView;

public class CharacterCreationController {
	private Character character;
	private CharacterCreationView characterPage;

	public CharacterCreationController(Character character, CharacterCreationView characterPage) {
		this.character = character;
		this.characterPage = characterPage;

		populateSkill();
	}

	private void populateSkill() {
		for (Character.Skill skill : character.getSkillList()) {
			characterPage.addSkill(skill.getType(), skill.getSkill(), skill.getDescription(), skill.getRank());
		}

	}
}
