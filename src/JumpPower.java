import javax.swing.*;
import java.awt.*;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/26/23
 */

public class JumpPower {
    int x;
    int y;
    int height;
    int width;
    Rectangle hitBox;

    private String image = "/Assets/JumpPower.png";

    /**
     * constructor for coin class
     *
     * @param x      x pos
     * @param y      y pos
     * @param height height of coin
     * @param width  width of coin
     */
    public JumpPower(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        hitBox = new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * draws coin with coin image
     *
     * @param gtd graphics variable
     */
    public void draw(Graphics2D gtd) {
        gtd.drawImage(getCoinImage(), x, y, width, height, null);
    }

    public Image getCoinImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(image));
        return i.getImage();

    }
}