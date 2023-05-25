import java.awt.*;

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

    public Wall(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        startX = x;
        this.width = width;
        this.height = height;

        hitBox = new Rectangle(x, y, width, height);
    }


    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x, y, width, height);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(x + 1, y + 1, width - 2, height - 2);
    }
}
