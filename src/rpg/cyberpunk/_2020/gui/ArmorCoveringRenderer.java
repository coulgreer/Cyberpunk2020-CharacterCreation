package rpg.cyberpunk._2020.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;

public class ArmorCoveringRenderer extends DefaultTableCellRenderer {
	public static final int ICON_HEIGHT = 28;

	public ArmorCoveringRenderer() {
		super();

		setHorizontalAlignment(CENTER);
	}

	@Override
	public void setValue(Object value) {
		String covers = (String) value;
		String imgAddress;

		switch (covers) {
		case CyberpunkArmor.COVERING_HEAD:
			imgAddress = "img/icons/armor/head-512.png";
			break;
		case CyberpunkArmor.COVERING_ARMS:
			imgAddress = "img/icons/armor/arm-512.png";
			break;
		case CyberpunkArmor.COVERING_TORSO:
			imgAddress = "img/icons/armor/vest-512.png";
			break;
		case CyberpunkArmor.COVERING_ARMS_AND_TORSO:
			imgAddress = "img/icons/armor/shirt-512.png";
			break;
		case CyberpunkArmor.COVERING_LEGS:
			imgAddress = "img/icons/armor/pants-512.png";
			break;
		case CyberpunkArmor.COVERING_EVERYTHING:
		case CyberpunkArmor.COVERING_NOTHING:
			imgAddress = "img/icons/armor/arm-512.png";
			break;
		default:
			imgAddress = "img/icons/armor/arm-512.png";
			break;
		}

		BufferedImage bufferedImg = null;
		try {
			bufferedImg = ImageIO.read(new File(imgAddress));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image img = bufferedImg.getScaledInstance(ICON_HEIGHT, ICON_HEIGHT, Image.SCALE_SMOOTH);

		setIcon(new ImageIcon(img));
	}

}
