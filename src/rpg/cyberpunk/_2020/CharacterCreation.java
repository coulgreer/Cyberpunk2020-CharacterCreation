package rpg.cyberpunk._2020;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rpg.cyberpunk._2020.gui.CharacterDisplay;
import rpg.cyberpunk._2020.gui.MainMenuBar;
import rpg.cyberpunk._2020.gui.NewCharacterDialog;

public class CharacterCreation {
  public static final int WINDOW_WIDTH = 1280;
  public static final int WINDOW_HEIGHT = 720;
  public static final String MAIN_MENU = "Main Menu";
  public static final String PLAYER_DISPLAY = "Player Display";

  public static void main(String arg[]) {
    JFrame frame = new JFrame("Cyberpunk 2020 Character Creator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setJMenuBar(new MainMenuBar());
    frame.setContentPane(createCards(frame));

    frame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private static Container createCards(JFrame frame) {
    JPanel panel = new JPanel(new CardLayout());

    panel.add(createButtonComponent(frame, panel), MAIN_MENU);

    return panel;
  }

  private static JComponent createButtonComponent(JFrame frame, Container parent) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add(Box.createVerticalGlue());
    JButton newButton = new JButton("New Character");
    newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    newButton.addActionListener(evt -> {
      Player player = new Player();
      JDialog dialog = new NewCharacterDialog(frame, "LIFEPATH.", true, player);
      dialog.pack();
      dialog.setLocationRelativeTo(frame);
      dialog.setVisible(true);

      parent.add(new CharacterDisplay(player), PLAYER_DISPLAY);
      CardLayout layout = (CardLayout) parent.getLayout();
      layout.show(parent, PLAYER_DISPLAY);
    });
    panel.add(newButton);

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    JButton loadButton = new JButton("Load Character");
    loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    loadButton.addActionListener(evt -> {
      // TODO (Coul Greer): When loading make sure to create a new instance of Player so that data
      // may be loaded from a file into the object. Also, make a CharacterDisplay using the newly
      // populated Player object.
    });
    panel.add(loadButton);
    panel.add(Box.createVerticalGlue());

    return panel;
  }

}
