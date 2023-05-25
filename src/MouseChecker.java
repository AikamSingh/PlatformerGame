import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class MouseChecker implements MouseListener {

    GamePanel panel;

    public MouseChecker(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
