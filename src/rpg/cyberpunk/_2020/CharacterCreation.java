package rpg.cyberpunk._2020;

import java.awt.CardLayout;
import java.awt.Dimension;
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

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    JPanel contentPanel = new JPanel(new CardLayout());
    
    contentPanel.add(mainPanel, MAIN_MENU);

    JButton newButton = new JButton("New Character");
    newButton.addActionListener(evt -> {
      JDialog dialog = new NewCharacterDialog(frame, "LIFEPATH.", true, player);
      dialog.pack();
      dialog.setLocationRelativeTo(frame);
      dialog.setVisible(true);
      
      CardLayout layout = (CardLayout) contentPanel.getLayout();
      layout.show(contentPanel, PLAYER_DISPLAY);
    });
    mainPanel.add(newButton);

    JButton loadButton = new JButton("Load Character");
    mainPanel.add(loadButton);

    contentPanel.add(new CharacterDisplay(player), PLAYER_DISPLAY);

    frame.setJMenuBar(new MainMenuBar());
    frame.setContentPane(contentPanel);

    frame.setSize(new Dimension(1280, 720));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

}
