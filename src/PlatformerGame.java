import javax.swing.*;
import java.awt.*;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class PlatformerGame {

    /**
     * main function for the game
     * controls the window attributes
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        frame.setSize(725, 700);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int) (screenSize.getHeight()/2 - frame.getHeight()/2));

        frame.setResizable(false);
        frame.setTitle("Platformer Game");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
