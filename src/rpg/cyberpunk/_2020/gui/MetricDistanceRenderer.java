package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * A renderer that turns Numbers into formatted data measured in meters. This
 * renderer is used to display Numbers in terms of distance.
 */
public class MetricDistanceRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a renderer that aligns its contents in the center.
	 */
	public MetricDistanceRenderer() {
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
