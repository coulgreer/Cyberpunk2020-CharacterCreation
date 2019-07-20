package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.cyberpunk._2020.systems.CharacterPointsManager;
import rpg.general.stats.Attribute;
import rpg.util.Die;

/**
 * A pane that displays all attributes in a JPanel and uses a <code>CharacterPointsManager</code> to
 * display the options for the displayed JComboBoxes as well as using the CharacterPointsManager to
 * trigger updates to the screen.
 */
public class FastCharacterPointsPane extends JPanel implements PropertyChangeListener {
  private static final long serialVersionUID = 1L;

  private CharacterPointsManager manager;
  private List<Attribute> attributes;
  private JLabel pointLabel;

  /**
   * Constructs an instance of FastCharacterPointsPane that sets the attributes and manager as well
   * as initializing the display and creates a List of JComboBoxes to track all displayed
   * JComboBoxes.
   * 
   * @param attributes a list of <code>Attribute</code>s to display
   * @param manager the observed object used to update the view as well as providing the rolled
   *        points used by JComboBoxes
   */
  public FastCharacterPointsPane(CharacterPointsManager manager) {
    super(new BorderLayout());
    setManager(manager);

    add(createContent(), BorderLayout.CENTER);
    add(createButtonComponent(), BorderLayout.SOUTH);
  }

  private void setManager(CharacterPointsManager manager) {
    if (manager == null) {
      throw new NullPointerException();
    } else {
      this.manager = manager;
      setAttributes(manager.getAttributes());
      manager.addPropertyChangeListener(CharacterPointsManager.PROPERTY_NAME_POINTS, this);
    }
  }

  private void setAttributes(List<Attribute> attributes) {
    if (attributes == null) {
      throw new NullPointerException();
    } else {
      this.attributes = attributes;
    }
  }

  private Component createContent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createPointContent(), BorderLayout.NORTH);
    panel.add(createAttributeContainer(), BorderLayout.CENTER);

    return panel;
  }

  private Component createPointContent() {
    JPanel panel = new JPanel();

    pointLabel = new JLabel(getPointText());
    panel.add(pointLabel);

    return panel;
  }

  private String getPointText() {
    List<Integer> pool = new ArrayList<Integer>(manager.getRolledPoints());
    pool.sort(Comparator.naturalOrder());
    return "Point Pool: " + pool;
  }

  private Container createAttributeContainer() {
    JPanel panel = new JPanel(new GridLayout(0, 3));

    for (int i = 0; i < attributes.size(); i++) {
      panel.add(new AttributePane.ComboBox.Builder(attributes.get(i), manager) //
          .withDefaultIndex(i) //
          .build());
    }

    return panel;
  }

  private Component createButtonComponent() {
    JPanel panel = new JPanel();

    JButton button = new JButton("Roll Points");
    button.addActionListener(evt -> {
      manager.rollPoints(new Die(StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT, 10));
    });
    panel.add(button);

    return panel;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Object source = evt.getSource();

    if (manager == source) {
      pointLabel.setText(getPointText());
    }
  }

}
