package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Character;
import view.CharacterCreationView;

public class CharacterCreationController {
	private Character characterModel;
	private CharacterCreationView characterView;

	public CharacterCreationController(Character characterModel, CharacterCreationView characterView) {
		this.characterModel = characterModel;
		this.characterView = characterView;

		this.characterView.drawInjuryPoints(characterModel.getInjuryPoints());
		this.characterView.addDocumentChangedListener(new StatChangedListener());
		this.characterView.addLoadCharacterListener(new LoadCharacterListener());
		this.characterView.addChangeRoleListener(new ChangeRoleListener());
		this.characterView.addSaveCharacterListener(new SaveCharacterListener());

		populateSkill();
	}

	private void populateSkill() {
		for (Character.Skill skill : characterModel.getSkillList().values()) {
			characterView.addSkill(skill.getType(), skill.getSkill(), skill.getDescription());
		}
	}

	class ChangeRoleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox comboBox = (JComboBox) e.getSource();
			String selectedRole = (String) comboBox.getSelectedItem();
			characterModel.setRole(selectedRole);
		}
	}

	// Currently only updates Handle for user
	class StatChangedListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			characterModel.setMovementAllowance(((JSpinner) e.getSource()).getValue().toString());
			characterView.setRunLevel(characterModel.getRunLevel() + "");
			characterView.setLeapLevel(characterModel.getLeapLevel() + "");

		}

	}

	class LoadCharacterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int returnVal = characterView.getFileChooser().showOpenDialog(characterView);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					Scanner sc = new Scanner(characterView.getFileChooser().getSelectedFile());
					while (sc.hasNextLine()) {
						String[] strings = sc.nextLine().split("\t");
						String key = strings[0];
						String value = strings[1];
						switch (key) {
						case "handle":
							characterView.setHandle(value);
							characterModel.setHandle(value);
							break;
						case "role":
							characterView.setRole(value);
							characterModel.setRole(value);
							break;
						case "character_points":
							characterView.setCharacterPoints(value);
							characterModel.setCharacterPoints(value);
							break;
						case "intelligence":
							characterView.setIntelligenceLevel(value);
							characterModel.setIntelligenceLevel(value);
							break;
						case "unmodified_reflexes":
							characterView.setUnmodifiedReflexesLevel(value);
							characterModel.setUnmodifiedReflexesLevel(value);
							break;
						case "modified_reflexes":
							characterView.setModifiedReflexesLevel(value);
							characterModel.setModifiedReflexesLevel(value);
							break;
						case "technical_ability":
							characterView.setTechnicalAbilityLevel(value);
							characterModel.setTechnicalAbilityLevel(value);
							break;
						case "cool":
							characterView.setCoolLevel(value);
							characterModel.setCoolLevel(value);
							break;
						case "attractiveness":
							characterView.setAttractivenessLevel(value);
							characterModel.setAttractivenessLevel(value);
							break;
						case "luck":
							characterView.setLuckLevel(value);
							characterModel.setLuckLevel(value);
							break;
						case "movement_allowance":
							characterView.setMovementAllowanceLevel(value);
							characterModel.setMovementAllowance(value);
							break;
						case "body":
							characterView.setBodyLevel(value);
							characterModel.setBodyLevel(value);
							break;
						case "current_empathy":
							characterView.setCurrentEmpathyLevel(value);
							characterModel.setCurrentEmpathyLevel(value);
							break;
						case "total_empathy":
							characterView.setTotalEmpathyLevel(value);
							characterModel.setTotalEmpathyLevel(value);
							break;
						case "run":
							characterView.setRunLevel(value);
							characterModel.setRunLevel(value);
							break;
						case "leap":
							characterView.setLeapLevel(value);
							characterModel.setLeapLevel(value);
							break;
						case "lift":
							characterView.setLiftLevel(value);
							characterModel.setLiftLevel(value);
							break;
						case "head_armor_sp":
							characterView.setHeadArmorStoppingPower(value);
							characterModel.setHeadArmorStoppingPower(value);
							break;
						case "torso_armor_sp":
							characterView.setTorsoArmorStoppingPower(value);
							characterModel.setTorsoArmorStoppingPower(value);
							break;
						case "right_arm_armor_sp":
							characterView.setRightArmArmorStoppingPower(value);
							characterModel.setRightArmArmorStoppingPower(value);
							break;
						case "left_arm_armor_sp":
							characterView.setLeftArmArmorStoppingPower(value);
							characterModel.setLeftLegArmorStoppingPower(value);
							break;
						case "right_leg_armor_sp":
							characterView.setRightLegArmorStoppingPower(value);
							characterModel.setRightLegArmorStoppingPower(value);
							break;
						case "left_leg_armor_sp":
							characterView.setLeftLegArmorStoppingPower(value);
							characterModel.setLeftLegArmorStoppingPower(value);
							break;
						case "save_modifier":
							characterView.setSaveModifier(value);
							characterModel.setSaveModifier(value);
							break;
						case "body_type_modifier":
							characterView.setBodyTypeModifier(value);
							characterModel.setBodyTypeModifier(value);
							break;
						case "injury_points":
							characterModel.setInjuryPoints(value);
							break;
						default:
							break;
						}
					}
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Opened: " + characterView.getFileChooser().getSelectedFile().getPath());
			}
		}

	}

	class SaveCharacterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int returnVal;
			returnVal = characterView.getFileChooser().showSaveDialog(characterView);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if (characterView.getFileChooser().getSelectedFile().exists()
						|| new File(characterView.getFileChooser().getSelectedFile().toString() + ".char").exists()) {
					returnVal = JOptionPane.showConfirmDialog(
							characterView, "Do you want to overwrite "
									+ characterView.getFileChooser().getSelectedFile().getName() + " ?",
							"Confirm Overwrite", JOptionPane.YES_NO_OPTION);
					if (returnVal == JOptionPane.YES_OPTION) {
						try {
							writeDataToFile();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				} else {
					try {
						writeDataToFile();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
				System.out.println("Saved to: " + characterView.getFileChooser().getSelectedFile().getPath());
			}
		}

		private void writeDataToFile() throws IOException {
			File file = characterView.getFileChooser().getSelectedFile();
			String filename = file.toString();
			FileWriter fw;
			if (!filename.endsWith(".char")) {
				filename += ".char";
				fw = new FileWriter(filename);
			} else {
				fw = new FileWriter(filename);
			}
			fw.write("handle\t" + characterModel.getHandle() + "\n" //
					+ "role\t" + characterModel.getRole().getRoleName() + "\n" //
					+ "character_points\t" + characterModel.getCharacterPoints() + "\n" //
					+ "intelligence\t" + characterModel.getIntelligenceLevel() + "\n" //
					+ "unmodified_reflexes\t" + characterModel.getUnmodifiedReflexesLevel() + "\n" //
					+ "modified_reflexes\t" + characterModel.getModifiedReflexesLevel() + "\n" //
					+ "technical_ability\t" + characterModel.getTechnicalAbilityLevel() + "\n" //
					+ "cool\t" + characterModel.getCoolLevel() + "\n" //
					+ "attractiveness\t" + characterModel.getAttractivenessLevel() + "\n" //
					+ "luck\t" + characterModel.getLuckLevel() + "\n" //
					+ "movement_allowance\t" + characterModel.getMovementAllowance() + "\n" //
					+ "body\t" + characterModel.getBodyLevel() + "\n" //
					+ "current_empathy\t" + characterModel.getCurrentEmpathyLevel() + "\n" //
					+ "total_empathy\t" + characterModel.getTotalEmpathyLevel() + "\n" //
					+ "run\t" + characterModel.getRunLevel() + "\n" //
					+ "leap\t" + characterModel.getLeapLevel() + "\n" //
					+ "lift\t" + characterModel.getLiftLevel() + "\n" //
					+ "head_armor_sp\t" + characterModel.getHeadArmorStoppingPower() + " \n" //
					+ "torso_armor_sp\t" + characterModel.getTorsoArmorStoppingPower() + "\n" //
					+ "right_arm_armor_sp\t" + characterModel.getRightArmArmorStoppingPower() + "\n" //
					+ "left_arm_armor_sp\t" + characterModel.getLeftArmArmorStoppingPower() + "\n" //
					+ "right_leg_armor_sp\t" + characterModel.getRightLegArmorStoppingPower() + "\n" //
					+ "left_leg_armor_sp\t" + characterModel.getLeftLegArmorStoppingPower() + "\n" //
					+ "save_modifier\t" + characterModel.getSaveModifier() + "\n" //
					+ "body_type_modifier\t" + characterModel.getBodyTypeModifier() + "\n" //
					+ "injury_points\t" + characterModel.getInjuryPoints() + "\n");
			fw.close();

		}

	}
}
