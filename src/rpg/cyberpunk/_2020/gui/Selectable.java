package rpg.cyberpunk._2020.gui;

/**
 * An interface that allows for an object to be selected.
 */
public interface Selectable {
	/**
	 * Returns true, if this object has been selected.
	 * 
	 * @return <code>true</code>, if this object has been selected
	 */
	public boolean isSelected();

	/**
	 * Sets the state of the implementing object to be selected or not selected.
	 * 
	 * @param isSelected the desired state for the implementing object to be in
	 */
	public void setSelected(boolean isSelected);
}
