package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * A renderer that turns Numbers into formatted data measured in meters. This
 * renderer is used to display Numbers in terms of distance.
 * 
 * @author Coul Greer
 *
 */
public class DistanceRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructs a renderer that aligns its contents to the right.
	 */
	public DistanceRenderer() {
		super();

		setHorizontalAlignment(RIGHT);
	}

	@Override
	public void setValue(Object value) {
		Number number = (Number) value;

		setText(number + "m");
	}

}
