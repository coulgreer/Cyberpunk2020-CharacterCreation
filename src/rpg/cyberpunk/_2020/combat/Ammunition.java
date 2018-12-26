package rpg.cyberpunk._2020.combat;

public interface Ammunition {
	public static enum AmmoType {
		NONE("NONE"), //
		_5MM("5mm"), //
		_6MM("6mm"), //
		_9MM("9mm"), //
		_10MM("10mm"), //
		_11MM("11mm"), //
		_12MM("12mm"), //
		_556("5.56"), //
		_762("7.62"), //
		_12_GAUGE("12ga"), //
		_20MM("20mm"), //
		ARROW("Arrow"), //
		GRENADE("Grenade"), //
		MINE("Mine");

		private String name;

		AmmoType(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public AmmoType getAmmoType();

	public double getSoftArmorMultiplier();

	public double getHardArmorMultiplier();

	public double getDamageMultiplier();
}
