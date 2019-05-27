package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import rpg.cyberpunk._2020.Player;
import rpg.general.stats.Restriction;

/**
 * A restriction that uses a role to dictate if another object is or is not
 * limited in its abilities.
 * 
 * @author Coul Greer
 */
public class RoleRestriction implements Restriction, PropertyChangeListener {
	private Role role;
	private Player player;
	private boolean isRestricted;

	/**
	 * Constructs a limit that observers a player to see if it has the given role.
	 * 
	 * @param role   the duty that a player needs in order to lift the restriction
	 * @param player the object to observe for the given role
	 */
	public RoleRestriction(Role role, Player player) {
		setRole(role);
		setPlayer(player);
		setRestricted(!hasRole());
	}

	private void setRole(Role role) {
		if (role == null) {
			throw new IllegalArgumentException("The field 'role' must not be null.");
		} else {
			this.role = role;
		}
	}

	private void setPlayer(Player player) {
		if (player == null) {
			throw new IllegalArgumentException("The field 'player' must not be null.");
		} else {
			this.player = player;
			player.addPropertyChangeListener(Player.PROPERTY_NAME_ROLE, this);
		}
	}

	private void setRestricted(boolean isRestricted) {
		this.isRestricted = isRestricted;
	}

	private boolean hasRole() {
		return (role.equals(player.getRole()));
	}

	@Override
	public boolean isRestricted() {
		return isRestricted;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (player == evt.getSource()) {
			setRestricted(!hasRole());
		}
	}

}
