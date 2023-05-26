import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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

    private String image = "/Assets/Enemy.png";

    int boundaryLeft = 30;
    int boundaryRight = 670;
    double xspeed;
    double yspeed;
    Rectangle hitBox;
    GamePanel panel;

    /**
     * constructor for the class enemy
     * @param x x pos
     * @param y y pos
     * @param height height of enemy
     * @param width width of enemy
     * @param panel panel which the enemy will appear in
     */
    public Enemy(int x, int y, int height, int width, GamePanel panel) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.panel = panel;
        hitBox = new Rectangle(x, y, width, height);
        startDir = (int) (Math.random() * 2) + 1;
    }

    /**
     * controls logic for collision and movement of the enemy
     */
    public void set(){
        //collision with floor
        yspeed += 0.45;

        //randomly chooses left or right
        xspeed += (startDir == 1 ? 1: -1);

        //sets boundaries for speed
        if(xspeed > 0 && xspeed < 0.75){
            xspeed = 0;
        }
        if(xspeed < 0 && xspeed > -0.75){
            xspeed = 0;
        }
        if(panel.time >= 30000){
            if(xspeed > 5){
                xspeed = 5;
            }
            if(xspeed < -5){
                xspeed = -5;
            }
        }
        else if(panel.time <  30000){
            if(xspeed > 8){
                xspeed = 8;
            }
            if(xspeed < -8){
                xspeed = -8;
            }
        }
        else if(panel.time <  15000){
            if(xspeed > 10){
                xspeed = 10;
            }
            if(xspeed < -10){
                xspeed = -10;
            }
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

        if(y > 800){
            panel.enemies.clear();
            panel.spawnEnemies();
        }
    }

    /**
     * draws the enemy
     * @param gtd graphics variable
     */
    public void draw(Graphics2D gtd){
        gtd.drawImage(getEnemyImage(), x, y, width, height, null);
    }

    public Image getEnemyImage() {
        ImageIcon i = new ImageIcon(Objects.requireNonNull(getClass().getResource(image)));
        return i.getImage();

    }
}
