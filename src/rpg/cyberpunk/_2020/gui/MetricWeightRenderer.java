package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * A renderer that converts data into a numerical value preceded by 'kg' to
 * represent weight in metric.
 */
public class MetricWeightRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a renderer that forces data to be right aligned.
	 */
	public MetricWeightRenderer() {
		super();

		setHorizontalAlignment(RIGHT);
	}

	@Override
	public void setValue(Object value) {
		Double weightKg = (Double) value;

		setText(String.format("%01.2f kg", weightKg));
	}

}
