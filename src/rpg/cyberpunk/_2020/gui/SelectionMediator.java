package rpg.cyberpunk._2020.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used to manage a group of objects that implement
 * <code>Selectable</code>. This class allows only one Selectable to be selected
 * at a time the rest are set to a non-selected state.
 * 
 * @author Coul Greer
 */
public class SelectionMediator {
	private List<Selectable> colleagues;
	private Selectable activeColleague;

	/**
	 * Constructs a SelectionMediator that initiates an ArrayList of Selectables and
	 * sets the active Selectable to <code>null</code>.
	 */
	public SelectionMediator() {
		colleagues = new ArrayList<Selectable>();
		activeColleague = null;
	}

	/**
	 * Appends a Selectable to the SelectionMediator.
	 * 
	 * @param s an instance of Selectable to added
	 */
	public void registerColleague(Selectable s) {
		colleagues.add(s);
	}

	/**
	 * Removes a Selectable from the SelectionMediator.
	 * 
	 * @param s an instance of Selectable to removed
	 */
	public void removeColleague(Selectable s) {
		colleagues.remove(s);
	}

	/**
	 * Makes the given Selectable the only active Selectable in the mediator.
	 * 
	 * @param selectedItem the Selectable to make active
	 */
	public void setActive(Selectable selectedItem) {
		if (colleagues.contains(selectedItem)) {
			activeColleague = selectedItem;
			for (Selectable item : colleagues) {
				item.setSelected(item == selectedItem);
			}
		}
	}

	/**
	 * Returns the Selectable that is currently active.
	 * 
	 * @return the Selectable that is active
	 */
	public Selectable getSelected() {
		return activeColleague;
	}
}
