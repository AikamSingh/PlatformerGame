import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class Wall {

    int x;
    int y;
    int width;
    int height;
    int startX;

    Rectangle hitBox;

    private String image = "/Assets/BrickWallCropped.png";

    /**
     * constructor for the wall class
     * @param x x pos
     * @param y y pos
     * @param width width of wall
     * @param height height of wall
     */
    public Wall(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        startX = x;
        this.width = width;
        this.height = height;

        hitBox = new Rectangle(x, y, width, height);
    }


    /**
     * draws the wall
     * @param gtd graphics variable
     */
    public void draw(Graphics2D gtd) {
        gtd.drawImage(getWallImg(), x, y, width, height, null);
    }
    public Image getWallImg() {
        ImageIcon i = new ImageIcon(Objects.requireNonNull(getClass().getResource(image)));
        return i.getImage();

    }
}
