package rpg.cyberpunk._2020.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

/**
 * A renderer that converts a String into an icon.
 * 
 * @author Coul Greer
 */
public class WeaponTypeRenderer extends DefaultTableCellRenderer {
	public static final int ICON_HEIGHT = 28;

	/**
	 * Constructs a renderer that centers its data elements.
	 */
	public WeaponTypeRenderer() {
		super();

		setHorizontalAlignment(CENTER);
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
		case CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON:
			imgAddress = "img/ICONS/explosive-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_BOW:
			imgAddress = "img/ICONS/bow-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_UNARMED:
			imgAddress = "img/ICONS/fist-512.png";
			break;
		default:
			// TODO Think about default icon and add a icon for exotics.
			imgAddress = "img/ICONS/fist-512.png";
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
