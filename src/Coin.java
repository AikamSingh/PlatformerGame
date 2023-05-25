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

    private Image image;

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
        hitBox = new Rectangle(x, y, width, height);
        image = Toolkit.getDefaultToolkit().getImage("Assets/Coin.png");
    }

    /**
     * draws coin
     * @param gtd graphics variable
     */
    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x, y, width, height);
        gtd.setColor(Color.YELLOW);
        gtd.fillRect(x + 1, y + 1, width - 2, height - 2);
    }


}
