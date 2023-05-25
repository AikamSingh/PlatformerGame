import java.awt.*;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class MainFrame extends javax.swing.JFrame{


    public MainFrame(){
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
