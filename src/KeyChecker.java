import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class KeyChecker extends KeyAdapter {
    GamePanel panel;

    /**
     * @param panel panel to listen for key events in
     */
    public KeyChecker(GamePanel panel) {
        this.panel = panel;
    }

    /**
     * listen for key events
     * @param e key pressed event
     */
    @Override
    public void keyPressed(KeyEvent e){
        panel.keyPressed(e);
    }

    /**
     * listen for key events
     * @param e key pressed event
     */
    @Override
    public void keyReleased(KeyEvent e){
        panel.keyReleased(e);
    }
}
