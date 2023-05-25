import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/24/23
 */

public class Coin {
    int x;
    int y;
    int height;
    int width;
    Rectangle hitBox;

    private String image = "/Assets/Coin.png";

    /**
     * constructor for coin class
     * @param x x pos
     * @param y y pos
     * @param height height of coin
     * @param width width of coin
     */
    public Coin(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        hitBox = new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * draws coin with coin image
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
