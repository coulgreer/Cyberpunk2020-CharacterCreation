package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.general.stats.Attribute;

/**
 * A panel that displays the data that makes up a player's background and main attributes.
 */
public class BiographyTab extends JPanel {
  private static final long serialVersionUID = 1L;

  private Player player;

  /**
   * Constructs an instance of BiographyTab that has two sections: 1) General statistics such as
   * Attributes, age, etc. as well as a portrait of the character.
   * 
   * @param player the provider of all data displayed under this tab
   */
  public BiographyTab(Player player) {
    super(new GridLayout(0, 2));
    setPlayer(player);

    add(createStatComponent());
    add(createBackgroundComponent());
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
    }
  }

  private Component createStatComponent() {
    JPanel panel = new JPanel(new GridLayout(2, 0));

    panel.add(createDetailStatComponent());
    panel.add(createAttributesComponent());

    return panel;
  }

  private Component createDetailStatComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createPictureComponent(), BorderLayout.CENTER);
    panel.add(createBasicInfoComponent(), BorderLayout.SOUTH);

    return panel;
  }

  private Component createPictureComponent() {
    JPanel panel = new JPanel();

    // TODO (Coul Greer): Add the ability for user to provide a picture as well as a default picture
    // if one was not provided.
    try {
      BufferedImage bufferedImage = ImageIO.read(new File("img/default_portrait.png"));
      ImageIcon icon = new ImageIcon(bufferedImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
      JLabel imageLabel = new JLabel(icon);
      imageLabel.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
      panel.add(imageLabel);
    } catch (IOException ex) {
      // TODO (Coul Greer): Add a logger.
      System.out.println(ex);
    }

    return panel;
  }

  private Component createBasicInfoComponent() {
    JPanel panel = new JPanel(new GridLayout(1, 0));

    Border divider = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);

    JComponent p = createInfoComponent( //
        "Alias", //
        player.getAlias().getValue());
    p.setBorder(divider);
    panel.add(p);

    p = createInfoComponent( //
        "Age", //
        Integer.toString(player.getAge()));
    p.setBorder(divider);
    panel.add(p);

    p = createInfoComponent( //
        "Gender", //
        player.getGender().toString());
    panel.add(p);

    return panel;
  }

  private JComponent createInfoComponent(String title, String info) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 14));
    panel.add(titleLabel, BorderLayout.NORTH);

    JLabel infoLabel = new JLabel(info, SwingConstants.CENTER);
    infoLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    panel.add(infoLabel, BorderLayout.CENTER);

    return panel;
  }

  private Component createAttributesComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel("ATTRIBUTES", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    panel.add(titleLabel, BorderLayout.NORTH);

    panel.add(createAttributeParent(), BorderLayout.CENTER);

    return panel;
  }

  private Component createAttributeParent() {
    JPanel panel = new JPanel(new GridLayout(0, 4));
    panel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

    JComponent c;
    int borderWidth = 2;

    // Row 1
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.INTELLIGENCE), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.REFLEXES), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.TECHNICAL_ABILITY), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    panel.add(new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.COOL), //
        BorderLayout.WEST));

    // Row 2
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.ATTRACTIVENESS), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.LUCK), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.MOVEMENT_ALLOWANCE), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.BODY_TYPE), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, 0, Color.BLACK));
    panel.add(c);

    // Row 3
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.EMPATHY), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = createDerivedAttributeContainer( //
        player.getAttribute(CyberpunkAttribute.RUN));
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = createDerivedAttributeContainer( //
        player.getAttribute(CyberpunkAttribute.LEAP));
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = createDerivedAttributeContainer( //
        player.getAttribute(CyberpunkAttribute.CARRY));
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, 0, Color.BLACK));
    panel.add(c);

    return panel;
  }

  private JComponent createDerivedAttributeContainer(Attribute attribute) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel(attribute.getName());
    titleLabel.setFont(AttributePane.DEFAULT_FONT);
    titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(titleLabel, BorderLayout.WEST);

    JLabel valueLabel = new JLabel(Integer.toString(attribute.getModifier()));
    valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(valueLabel, BorderLayout.CENTER);

    return panel;
  }

  private Component createBackgroundComponent() {
    JPanel panel = new JPanel();

    // TODO (Coul Greer): Add content to this component

    return panel;
  }

}
