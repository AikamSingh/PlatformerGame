import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class MainFrame extends javax.swing.JFrame{


    public MainFrame() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        GamePanel gamePanel = new GamePanel();
        //HomePanel homePanel = new HomePanel();


        gamePanel.setLocation(0,0);
        gamePanel.setSize(this.getSize());
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setVisible(true);
        this.add(gamePanel);


        addKeyListener(new KeyChecker(gamePanel));
        addMouseListener(new MouseChecker(gamePanel));
    }




}
