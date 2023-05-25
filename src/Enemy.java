import java.awt.*;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/24/23
 */

public class Enemy {

    int x;
    int y;
    int height;
    int width;

    int startDir;

    boolean dir = true;

    int boundaryLeft = 30;
    int boundaryRight = 670;
    double xspeed;
    double yspeed;
    Rectangle hitBox;
    GamePanel panel;

    public Enemy(int x, int y, int height, int width, GamePanel panel) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.panel = panel;
        hitBox = new Rectangle(x, y, width, height);
        startDir = (int) (Math.random() * 2) + 1;
    }

    public void set(){
        //collision with floor
        yspeed += 0.45;

        //randomly chooses left or right
        xspeed += (startDir == 1 ? 1: -1);

        System.out.println("X: " + x);

        //sets boundaries for speed
        if(xspeed > 0 && xspeed < 0.75){
            xspeed = 0;
        }
        if(xspeed < 0 && xspeed > -0.75){
            xspeed = 0;
        }
        if(xspeed > 3){
            xspeed = 3;
        }
        if(xspeed < -3){
            xspeed = -3;
        }


        hitBox.x += xspeed;
        for(Wall wall : panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.x -= xspeed;
                while(!wall.hitBox.intersects(hitBox)){
                    hitBox.x += (Math.signum(xspeed));
                }
                dir = !dir;
                hitBox.x = x;
            }
        }

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

        if(dir){
            x += xspeed;
        }
        else{
            x -= xspeed;
        }


        y += yspeed;

        hitBox.x = x;
        hitBox.y = y;
    }

    public void moveLeft(){
        xspeed--;
    }

    public void moveRight(){
        xspeed++;
    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.RED);
        gtd.fillRect(x, y, width, height);
    }
}
