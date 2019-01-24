package rpg.general.combat;

public interface Shootable {
	public boolean fire(int shotsFired);

	public Magazine reload(Magazine magazine);

	public String getAmmoType();

	public int getAmmoCapacity();

	public int getAmmoCount();
}
