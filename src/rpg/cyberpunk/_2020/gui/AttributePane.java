package rpg.cyberpunk._2020.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import rpg.general.stats.Attribute;

/**
 * A JPanel that listens to an <code>Attribute</code> and allows the increasing and decreasing of
 * its level.
 */
public class AttributePane extends JPanel implements PropertyChangeListener {
  private static final int iconHeight = 16;
  private static final long serialVersionUID = 1L;

  private Attribute attribute;
  private JLabel valueLabel;

  /**
   * Constructs a JPanel that uses an <code>Attribute</code> to populate its children as well as
   * allowing the manipulation and observation of the Attribute.
   * 
   * @param attribute the object being visually represented
   */
  public AttributePane(Attribute attribute) {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setAttribute(attribute);

    add(createTitleContent());
    add(createLevelContent());
  }

  private void setAttribute(Attribute attribute) {
    if (attribute == null) {
      throw new NullPointerException();
    } else {
      this.attribute = attribute;
      attribute.addPropertyChangeListener(this);
    }
  }

  private Component createTitleContent() {
    JPanel panel = new JPanel();

    panel.add(new JLabel(attribute.getName()));

    return panel;
  }

  private Component createLevelContent() {
    JPanel panel = new JPanel();

    valueLabel = new JLabel(getValueText());
    valueLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(valueLabel);
    panel.add(createButtonContainer());

    return panel;
  }

  private Container createButtonContainer() {
    JPanel panel = new JPanel(new GridLayout(2, 1));

    try {
      BufferedImage bufferedImage = ImageIO.read(new File("img/increase-arrow-minor-64x64.png"));
      ImageIcon icon = new ImageIcon(
          bufferedImage.getScaledInstance(iconHeight, iconHeight, Image.SCALE_SMOOTH));
      JButton button = new JButton(icon);
      button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
      button.addActionListener(evt -> attribute.incrementLevel());
      panel.add(button);
    } catch (IOException ex) {
      // TODO (Coul Greer): Add a logger.
      System.out.println(ex);
    }

    try {
      BufferedImage bufferedImage = ImageIO.read(new File("img/decrease-arrow-minor-64x64.png"));
      ImageIcon icon = new ImageIcon(
          bufferedImage.getScaledInstance(iconHeight, iconHeight, Image.SCALE_SMOOTH));
      JButton button = new JButton(icon);
      button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
      button.addActionListener(evt -> attribute.decrementLevel());
      panel.add(button);
    } catch (IOException ex) {
      // TODO (Coul Greer): Add a logger.
      System.out.println(ex);
    }

    return panel;
  }

  private String getValueText() {
    return Integer.toString(attribute.getModifier());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String propertyName = evt.getPropertyName();

    if (propertyName.equals(Attribute.PROPERTY_NAME_ATTRIBUTE_LEVEL)) {
      valueLabel.setText(getValueText());
    }
  }

}
