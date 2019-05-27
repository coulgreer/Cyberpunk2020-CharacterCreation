package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.cyberpunk._2020.systems.CharacterPointsManager;
import rpg.general.stats.Attribute;

/**
 * A JPanel that uses a <code>CharacterPointsManager</code> to allow the user to increase and
 * decrease the level of all independent <code>Attribute</code>s. This panel also allows the display
 * of the remaining points.
 */
public class RandomCharacterPointsPane extends JPanel implements PropertyChangeListener {
  private static final long serialVersionUID = 1L;

  private CharacterPointsManager manager;
  private List<Attribute> attributes;
  private JLabel pointLabel;

  /**
   * Constructs an instance of RandomCharacterPointsPanel that creates an instance of
   * <code>RandomCharacterPointsManager</code> as well as creating its children panels for points
   * remaining, attributes to level, and for the button to re-roll points.
   * 
   * @param player the owner of all the attributes to have their level's manipulated
   */
  public RandomCharacterPointsPane(List<Attribute> attributes, CharacterPointsManager manager) {
    super(new BorderLayout());
    setAttributes(attributes);
    setManager(manager);

    add(createCharacterPointContent(), BorderLayout.NORTH);
    add(createAttributeContainer(), BorderLayout.CENTER);
    add(createRollButtonComponent(), BorderLayout.SOUTH);
  }

  private void setAttributes(List<Attribute> attributes) {
    if (attributes == null) {
      throw new NullPointerException();
    } else {
      this.attributes = attributes;

      for (Attribute a : attributes) {
        a.addPropertyChangeListener(this);
      }
    }
  }

  private void setManager(CharacterPointsManager manager) {
    if (manager == null) {
      throw new NullPointerException();
    } else {
      this.manager = manager;
    }
  }

  private Component createCharacterPointContent() {
    JPanel panel = new JPanel();

    pointLabel = new JLabel(getPointText());
    panel.add(pointLabel);

    return panel;
  }

  private String getPointText() {
    return "Points Remaining: " + manager.getCurrentlyAvailablePoints();
  }

  private Container createAttributeContainer() {
    JPanel panel = new JPanel(new GridLayout(0, 3));

    for (Attribute a : attributes) {
      panel.add(new AttributePane(a));
    }

    return panel;
  }

  private Component createRollButtonComponent() {
    JPanel panel = new JPanel();

    JButton button = new JButton("Roll Points");
    button.addActionListener(evt -> {
      int minPoints = CyberpunkAttribute.MIN_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
      int maxPoints = CyberpunkAttribute.MAX_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
      manager.rollPoints(minPoints, maxPoints);
      pointLabel.setText(getPointText());
    });
    panel.add(button);

    return panel;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String propertyName = evt.getPropertyName();

    if (propertyName.equals(Attribute.PROPERTY_NAME_ATTRIBUTE_LEVEL)) {
      pointLabel.setText(getPointText());
    }
  }
}
