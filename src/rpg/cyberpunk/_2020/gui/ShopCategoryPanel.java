package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * A panel that holds elements relevant to displaying the weapon category that a
 * vendor has. Helps organize all children elements for layout managers.
 */
public class ShopCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a display that uses a <code>JTable</code> and an
	 * <code>ActionListener</code> to construct a display of available items for a
	 * Player to buy with the action performed when the button is pressed in
	 * <code>BuyButtonPanel</code>.
	 * 
	 * @param table    the displayer of items that can be purchased
	 * @param listener the actions to execute upon button press
	 */
	public ShopCategoryPanel(JTable table, ActionListener listener) {
		setLayout(new BorderLayout());

		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new BuyButtonPanel(listener);
		add(buttonPanel, BorderLayout.SOUTH);
	}

}
