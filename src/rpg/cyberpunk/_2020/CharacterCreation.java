package rpg.cyberpunk._2020;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rpg.cyberpunk._2020.gui.CharacterDisplay;
import rpg.cyberpunk._2020.gui.MainMenuBar;
import rpg.cyberpunk._2020.gui.NewCharacterDialog;

public class CharacterCreation {
  public static final String MAIN_MENU = "Main Menu";
  public static final String PLAYER_DISPLAY = "Player Display";

  public static void main(String arg[]) {
    Player player = new Player();

    JFrame frame = new JFrame("Cyberpunk 2020 Character Creator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setJMenuBar(new MainMenuBar());
    frame.setContentPane(createCards(frame, player));

    frame.setSize(new Dimension(1280, 720));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private static Container createCards(JFrame frame, Player player) {
    JPanel panel = new JPanel(new CardLayout());

    panel.add(createButtonComponent(frame, player, panel), MAIN_MENU);
    panel.add(new CharacterDisplay(player), PLAYER_DISPLAY);

    return panel;
  }

  private static Component createButtonComponent(JFrame frame, Player player, Container parent) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add(Box.createVerticalGlue());
    JButton newButton = new JButton("New Character");
    newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    newButton.addActionListener(evt -> {
      JDialog dialog = new NewCharacterDialog(frame, "LIFEPATH.", true, player);
      dialog.pack();
      dialog.setLocationRelativeTo(frame);
      dialog.setVisible(true);

      CardLayout layout = (CardLayout) parent.getLayout();
      layout.show(parent, PLAYER_DISPLAY);
    });
    panel.add(newButton);

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    JButton loadButton = new JButton("Load Character");
    loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(loadButton);
    panel.add(Box.createVerticalGlue());

    return panel;
  }

}
