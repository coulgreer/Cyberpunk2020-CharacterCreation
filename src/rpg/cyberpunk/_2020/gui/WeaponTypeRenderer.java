package rpg.cyberpunk._2020.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

/**
 * A renderer that converts a weapon type, represented by a String, into an
 * icon.
 */
public class WeaponTypeRenderer extends DefaultTableCellRenderer {
	public static final int ICON_HEIGHT = 28;

	private static final long serialVersionUID = 1L;

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
		String weaponIconAddress = "img/icons/weapon/";
		String imageAddress;

		switch (type) {
		case CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL:
		case CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL:
		case CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL:
		case CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL:
			imageAddress = weaponIconAddress + "pistol-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN:
		case CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN:
		case CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN:
			imageAddress = weaponIconAddress + "smg-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_RIFLE:
			imageAddress = weaponIconAddress + "rifle-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_SHOTGUN:
			imageAddress = weaponIconAddress + "shotgun-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON:
			imageAddress = weaponIconAddress + "melee-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON:
			imageAddress = weaponIconAddress + "heavy-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_BOW:
			imageAddress = weaponIconAddress + "bow-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_THROWN:
			imageAddress = weaponIconAddress + "grenade-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_EXOTIC:
			imageAddress = weaponIconAddress + "explosive-512.png";
			break;
		case CyberpunkWeapon.WEAPON_TYPE_UNARMED:
			imageAddress = weaponIconAddress + "fist-512.png";
			break;
		default:
			imageAddress = "img/icons/error-512.png";
			break;
		}

		try {
			BufferedImage bufferedImage = ImageIO.read(new File(imageAddress));
			Icon icon = new ImageIcon(bufferedImage.getScaledInstance(ICON_HEIGHT, ICON_HEIGHT, Image.SCALE_SMOOTH));
			setIcon(icon);
		} catch (IOException ex) {
			// TODO (Coul Greer): Add a logger to help handle this exception.
			ex.printStackTrace();
		}
	}

}
