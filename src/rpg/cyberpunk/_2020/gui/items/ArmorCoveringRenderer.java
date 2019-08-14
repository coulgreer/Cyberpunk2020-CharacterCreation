package rpg.cyberpunk._2020.gui.items;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;

public class ArmorCoveringRenderer extends DefaultTableCellRenderer {
	public static final int ICON_HEIGHT = 28;

	private static final long serialVersionUID = 1L;

	public ArmorCoveringRenderer() {
		super();

		setHorizontalAlignment(CENTER);
	}

	@Override
	public void setValue(Object value) {
		String covers = (String) value;
		String imageAddress;

		switch (covers) {
		case CyberpunkArmor.COVERING_HEAD:
			imageAddress = "img/icons/armor/head-512.png";
			break;
		case CyberpunkArmor.COVERING_ARMS:
			imageAddress = "img/icons/armor/arm-512.png";
			break;
		case CyberpunkArmor.COVERING_TORSO:
			imageAddress = "img/icons/armor/vest-512.png";
			break;
		case CyberpunkArmor.COVERING_ARMS_AND_TORSO:
			imageAddress = "img/icons/armor/shirt-512.png";
			break;
		case CyberpunkArmor.COVERING_LEGS:
			imageAddress = "img/icons/armor/pants-512.png";
			break;
		case CyberpunkArmor.COVERING_EVERYTHING:
		case CyberpunkArmor.COVERING_NOTHING:
			imageAddress = "img/icons/armor/arm-512.png";
			break;
		default:
			imageAddress = "img/icons/armor/arm-512.png";
			break;
		}

		try {
			BufferedImage bufferedImg = ImageIO.read(new File(imageAddress));
			Icon icon = new ImageIcon(bufferedImg.getScaledInstance(ICON_HEIGHT, ICON_HEIGHT, Image.SCALE_SMOOTH));
			setIcon(icon);
		} catch (IOException e) {
			// TODO (Coul Greer): Add a logger to help handle this exception.
			e.printStackTrace();
		}
	}

}
