import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    boolean facingRight = false;
    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    private String image = "/Assets/PlayerLeft.png";

    File musicFile;
    Clip coinSound;
    AudioInputStream audioStream;
    Clip jumpSound;

    Timer jumpTimer;

    /**
     * constructor for the player class
     * @param x x position of player
     * @param y y position of player
     * @param panel panel which the character will appear in
     */
    public Player(int x, int y, GamePanel panel) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        this.panel =  panel;
        this.x = x;
        this.y = y;
        width = 25;
        height = 50;
        hitBox = new Rectangle(x, y, width, height);
        /*
        File jumpSound = new File("/Users/as/Desktop/apcs/IndependentProject/src/Assets/smw_jump.wav");
        audioStream = AudioSystem.getAudioInputStream(jumpSound);
        this.jumpSound = AudioSystem.getClip();
        this.jumpSound.open(audioStream);
         */

    }

    /**
     * contains the logic which determines collision with walls, coins
     * also determines how the player can move
     * controls the amt of lives left of the player based on x position
     */
    public void set() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
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
        /*

         */

        if (keyUp) {
            //checks collision w ground
            //jumpSound.start();
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

        //coin sound effect
        // THIS IS AN ERROR: FOR SOME REASON IT RUNS OUT OF MEMORY
        /*
        File coinSound = new File("/Users/as/Desktop/apcs/IndependentProject/src/Assets/smw_coin.wav");
        audioStream = AudioSystem.getAudioInputStream(coinSound);
        this.coinSound = AudioSystem.getClip();
        this.coinSound.open(audioStream);
         */

        //collision with coin
        for(Coin coin : panel.coins){
            if(hitBox.intersects(coin.hitBox)){
                panel.points += 100;
                //this.coinSound.start();
                //this.coinSound.close();
                panel.coins.clear();
                panel.spawnCoins();
            }
        }

        //dmg taken sound


        //lose life when fall off screen
        if(y > 800){
            panel.lives--;
            panel.reset();
        }

        //enemy collision
        for(Enemy e : panel.enemies){
            if(hitBox.intersects(e.hitBox)){
                panel.lives--;
                panel.reset();
            }
        }
    }

    /**
     * draws the player
     * @param gtd graphics variable
     */
    public void draw(Graphics2D gtd){
        //changes sprite based on direction you are facing
        Image playerimg = getPlayerImage();
        if (xspeed < 0 ) {
            facingRight = true;
        }
        else if (xspeed > 0) {
            facingRight = false;
        }
        if(facingRight){
            gtd.drawImage(playerimg, x, y, width, height, null);
        }
        else {
            gtd.drawImage(playerimg, x+25, y, -width, height, null);
        }

        /*
        USED TO DEBUG HIT BOX:
        gtd.setColor(Color.BLACK);
        gtd.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height); //<-- uses hit box dimensions to draw a black box
         */

    }

    public Image getPlayerImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(image));
        return i.getImage();

    }


}
