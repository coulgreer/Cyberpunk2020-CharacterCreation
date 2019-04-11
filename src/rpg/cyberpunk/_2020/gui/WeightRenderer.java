package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * A renderer that converts data into a numerical value preceded by 'kg' to
 * represent weight.
 * 
 * @author Coul Greer
 *
 */
public class WeightRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructs a renderer that forces data to be right aligned.
	 */
	public WeightRenderer() {
		super();

		setHorizontalAlignment(RIGHT);
	}

	@Override
	public void setValue(Object value) {
		Double weight = (Double) value;

		setText(String.format("%01.2f kg", weight));
	}

}
