package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * A cell renderer that formats information to have a dollar sign and at least 1
 * leading zero and 2 digits after the decimal. The goal for this renderer is to
 * represent the format for real US currency.
 * 
 * @author Coul Greer
 */
public class CurrencyRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructs a renderer that aligns its contents to the right.
	 */
	public CurrencyRenderer() {
		super();

		setHorizontalAlignment(RIGHT);
	}

	@Override
	public void setValue(Object value) {
		Double cost = (Double) value;

		setText(String.format("$%01.2f", cost));
	}

}
