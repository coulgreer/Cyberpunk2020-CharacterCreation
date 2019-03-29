package rpg.cyberpunk._2020.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

public class WeaponTypeRenderer extends DefaultTableCellRenderer {
	public static final int ICON_HEIGHT = 28;

	public WeaponTypeRenderer() {
		super();
	}

	@Override
	public void setValue(Object value) {
		String type = (String) value;
		String imgAddress;

		switch (type) {
		case CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL:
		case CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL:
		case CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL:
		case CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL:
			imgAddress = "img/ICONS/pistol-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN:
		case CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN:
		case CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN:
			imgAddress = "img/ICONS/smg-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_RIFLE:
			imgAddress = "img/ICONS/rifle-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_SHOTGUN:
			imgAddress = "img/ICONS/shotgun-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON:
			imgAddress = "img/ICONS/melee-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_UNARMED:
			imgAddress = "img/ICONS/fist-512.png";
			break;
		default:
			imgAddress = "";
			break;
		}

		BufferedImage bufferedImg = null;
		try {
			bufferedImg = ImageIO.read(new File(imgAddress));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image img = bufferedImg.getScaledInstance(ICON_HEIGHT, ICON_HEIGHT, Image.SCALE_SMOOTH);

		setHorizontalAlignment(CENTER);
		setIcon(new ImageIcon(img));
	}

}
