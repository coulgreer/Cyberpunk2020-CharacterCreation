package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;

import rpg.util.Probability;

/**
 * A renderer that displays the probability to deal damage to a target. A
 * probability with no die AND no modifier will be displayed as 3 dashes, "---".
 * 
 * @author Coul Greer
 *
 */
public class DamageRenderer extends DefaultTableCellRenderer {

	/**
	 * Constructs a renderer that centers the probability for damage
	 */
	public DamageRenderer() {
		super();

		setHorizontalAlignment(CENTER);
	}

	@Override
	public void setValue(Object value) {
		Probability damage = (Probability) value;

		if (damage.getDice().getNumberOfDie() <= 0 && damage.getModifier() <= 0) {
			setText("---");
		} else {
			setText(damage.toString());
		}
	}

}
