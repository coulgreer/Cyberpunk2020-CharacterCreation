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
	 * Constructs a renderer that aligns its contents in the center.
	 */
	public DistanceRenderer() {
		super();

		setHorizontalAlignment(CENTER);
	}

	@Override
	public void setValue(Object value) {
		Number number = (Number) value;
		if (number.doubleValue() <= 0.0) {
			setText("---");
		} else {
			setText(number + "m");

		}
	}

}
