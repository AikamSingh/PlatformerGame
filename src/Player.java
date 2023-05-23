import java.awt.*;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class Player {

    GamePanel panel;
    int x;
    int y;
    int width;
    int height;
    double xspeed;
    double yspeed;
    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;


    public Player(int x, int y, GamePanel panel){
        this.panel =  panel;
        this.x = x;
        this.y = y;
        width = 50;
        height = 100;
        hitBox = new Rectangle(x, y, width, height);
    }

    public void set(){
        if(keyLeft && keyRight || !keyLeft && !keyRight){
            xspeed *= 0.8;
        }
        else if(keyLeft && !keyRight){
            xspeed --;
        }
        else if(!keyLeft && keyRight){
            xspeed ++;
        }

        if(xspeed > 0 && xspeed < 0.75){
            xspeed = 0;
        }
        if(xspeed < 0 && xspeed > -0.75){
            xspeed = 0;
        }
        if(xspeed > 7){
            xspeed = 7;
        }
        if(xspeed < -7){
            xspeed = -7;
        }

        x += xspeed;
        y += yspeed;

        hitBox.x = x;
        hitBox.y = y;
    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x, y, width, height);
    }


}