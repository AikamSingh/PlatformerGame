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
        width = 25;
        height = 50;
        hitBox = new Rectangle(x, y, width, height);
    }

    public void set(){
        //allows player to move left and right
        if(keyLeft && keyRight || !keyLeft && !keyRight){
            xspeed *= 0.8;
        }
        else if(keyLeft && !keyRight){
            xspeed --;
        }
        else if(!keyLeft && keyRight){
            xspeed ++;
        }


        //set limits for speed in horizontal direction
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

        //jumping/gravity
        if (keyUp) {
            //checks collision w ground
            hitBox.y++;
            for(Wall wall : panel.walls){
                if(wall.hitBox.intersects(hitBox)){
                    yspeed = -10;
                }
            }
            hitBox.y--;
        }
        yspeed += 0.45;

        //horizontal collision
        hitBox.x += xspeed;
        for(Wall wall : panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.x -= xspeed;
                while(!wall.hitBox.intersects(hitBox)){
                     hitBox.x += Math.signum(xspeed);
                }
                hitBox.x -= Math.signum(xspeed);
                xspeed = 0;
                hitBox.x = x;
            }
        }

        //vertical collision
        hitBox.y += yspeed;
        for(Wall wall : panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.y -= yspeed;
                while(!wall.hitBox.intersects(hitBox)){
                    hitBox.y += Math.signum(yspeed);
                }
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }

        x += xspeed;
        y += yspeed;

        hitBox.x = x;
        hitBox.y = y;

        for(Coin coin : panel.coins){
            if(hitBox.intersects(coin.hitBox)){
                panel.points++;
                panel.coins.clear();
                panel.spawnCoins();
            }
        }

        if(y > 800){
            panel.lives--;
            panel.reset();
        }
    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x, y, width, height);
    }

    public void setColor(Graphics2D gtd, Color color){
        gtd.setColor(color);
    }


}
