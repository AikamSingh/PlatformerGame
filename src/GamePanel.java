import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/23/23
 */

public class GamePanel extends javax.swing.JPanel implements ActionListener{
    int points;
    double time;
    int lives;
    Player player;

    boolean dead;
    boolean timeUp;
    boolean pause;
    boolean home;

    Enemy enemy; //will change to arraylist later

    ArrayList<Wall> walls;

    ArrayList<Coin> coins;
    int offset;

    Timer gameTimer;
    Timer countdown;

    Rectangle restartRect;
    Rectangle homeRect;

    double timerDecimals = Math.pow(10, 1); //rounds timer (10, num decimal places)


    Font buttonFont = new Font("Arial", Font.BOLD, 30);
    Font scoreFont = new Font("Arial", Font.BOLD, 15);

    Font endFont = new Font("Arial", Font.BOLD, 45);


    /**
     * constructor for class GamePanel
     * will be main panel in which the game takes place
     */
    public GamePanel() {
        restartRect = new Rectangle(550, 25, 50, 50);
        homeRect = new Rectangle(625, 25, 50, 50);

        player = new Player(400, 300, this); //creates a new player
        enemy = new Enemy(100, 100, 30, 30, this);
        walls = new ArrayList<>(); //creates a list of blocks
        coins = new ArrayList<>(); //creates a list of coins

        dead = false;
        pause = false;
        home = false;
        timeUp = false;


        reset();

        time = 60000;
        lives = 3;

        points = 000;
        spawnCoins();

        gameTimer = new Timer();
        countdown = new Timer();

        gameTimer.schedule(new TimerTask() {

            /**
             * this is the main function that will manage what happens during each frame
             * ticks once per 17 milisec bc that equates to 60 fps
             */
            @Override
            public void run() {
                if(walls.get(walls.size() - 1).x < 800){
                    offset += 700;
                    makeWalls(offset);
                }

                time -= 17;

                player.set();
                enemy.set();

                if(lives <= 0){
                    dead = true;
                    gameTimer.cancel();
                }
                if(time <= 0){
                    timeUp = true;
                    gameTimer.cancel();
                }

                repaint();
            }
        }, 0, 17);
    }

    /**
     * this method manages how the walls are created
     * there is a way to implement more than 1 level built in via the index variable
     * @param offset offset of walls
     */
    public void makeWalls(int offset) {
        int s = 25;
        int index = 0; //will add more levels later

         if(index == 0){
             //bottom walls
             for (int i = 6; i <= 10; i++) {
                 walls.add(new Wall(offset + i * s, 650, s, s));
             }

             for (int i = 30; i <= 33; i++) {
                 walls.add(new Wall(offset + i * s, 650, s, s));
             }


             //left walls
             for(int i = 0; i < 26; i++){
                 walls.add(new Wall(0,i * s, s, s));
             }

             //top walls
             for(int i = 1; i < 27; i++){
                 walls.add(new Wall(offset + i * s, 0, s, s));//there is a bud here
             }
             //manually spawns in the missing blocks in top wall
             walls.add(new Wall(offset + 28 * s, 0, s, s));
             walls.add(new Wall(offset + 27 * s, 0, s, s));

             //right walls
             for(int i = 1; i < 26; i++){
                 walls.add(new Wall(offset + 6*s,i * s, s, s));
             }

             //1st row of floating walls
             for(int i = 14; i < 27; i++){
                 walls.add(new Wall(offset + i * s, 550, s, s));
             }

             //2nd row of floating walls
             for(int i = 2; i < 11; i++){
                 walls.add(new Wall(offset + i * s, 450, s, s));
             }

             //3rd row of floating walls
             for (int i = 14; i <= 18; i++) {
                 walls.add(new Wall(offset + i * s, 350, s, s));
             }

             for (int i = 22; i <= 26; i++) {
                 walls.add(new Wall(offset + i * s, 350, s, s));
             }

             //4th row of walls
             for (int i = 9; i <= 12; i++) {
                 walls.add(new Wall(offset + i * s, 250, s, s));
             }

             for (int i = 18; i <= 22; i++) {
                 walls.add(new Wall(offset + i * s, 250, s, s));
             }

             for (int i = 28; i <= 31; i++) {
                 walls.add(new Wall(offset + i * s, 250, s, s));
             }
         }
    }

    /**
     * manages how the coins are spawned into the game
     * only 1 coin can exist at a time
     */
    public void spawnCoins(){
        int coinX = (int) (Math.random() * 625) + 50;
        int coinY = (int) (Math.random() * 625) + 50;

        while(coinX >= 200 && coinX <= 550){
            System.out.println("in a bad place! X: " + coinX);
            coinX = (int) (Math.random() * 625) + 50;
        }

        while(coinY <= 250){
            coinY = (int) (Math.random() * 625) + 50;
        }

        if(coins.size() == 0){
            coins.add(new Coin(coinX, coinY, 15, 15));
            for(Wall wall: walls){
                if(coins.get(0).hitBox.intersects(wall.hitBox)){
                    System.out.println("INTERSECTS WITH WALL");
                    coins.clear();
                    spawnCoins();
                }
            }
        }
        else{
            coins.remove(0);
        }
    }

    /**
     * this method controls how the game resets itself
     * depending on certain values such as the time left in the game and the lives left, different things get reset
     */
    public void reset(){
        player.x = 350;
        player.y = 450;
        enemy.x = 100;
        enemy.y = 100;
        enemy.xspeed = 0;
        enemy.yspeed = 0;
        player.xspeed = 0;
        player.yspeed = 0;
        walls.clear();
        coins.clear();
        spawnCoins();

        if(lives < 1){
            points = 0;
            time = 60000;
        }

        offset = -150;
        makeWalls(offset);

    }

    /**
     * @param g the <code>Graphics</code> context in which to paint
     * this manages painting all the graphics of the game
     */
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);
        enemy.draw(gtd);


        for(Wall wall: walls){
            wall.draw(gtd);
        }

        for(Coin coin: coins){
            coin.draw(gtd);
        }

        gtd.setColor(Color.BLACK);
        gtd.drawRect(525, 50, 50, 50);
        gtd.drawRect(600, 50, 50, 50);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(526, 50, 50, 50);
        gtd.fillRect(601, 50, 50, 50);
        gtd.setColor(Color.BLACK);
        gtd.setFont(buttonFont);
        gtd.drawString("R", 539, 85);
        gtd.drawString("H", 614, 85);

        gtd.setFont(scoreFont);
        gtd.drawString("Lives: " + lives, 50, 50);
        gtd.drawString("Score: " + points, 120, 50);
        gtd.drawString("Time: " + Math.round((time/1000) * timerDecimals) / timerDecimals, 210, 50);

        if(dead){ //end screen
            walls.clear();
            coins.clear();
            player.x = 800;
            enemy.x = 800;
            this.setBackground(Color.WHITE);
            gtd.setFont(endFont);
            gtd.setColor(Color.RED);
            gtd.drawString("GAME OVER", 220, 350);
            gtd.setColor(Color.BLACK);
            gtd.drawString("Score: " + points, 270, 400);
        }
        if(timeUp){ //end screen
            walls.clear();
            coins.clear();
            player.x = 800;
            enemy.x = 800;
            this.setBackground(Color.WHITE);
            gtd.setFont(endFont);
            gtd.setColor(Color.RED);
            gtd.drawString("TIMES UP!", 220, 350);
            gtd.setColor(Color.BLACK);
            gtd.drawString("Score: " + points, 270, 400);
        }
    }

    /**
     * this method listens for a key pressed
     * @param e event listener variable
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a'){
            player.keyLeft = true;
        }
        if(e.getKeyChar() == 'd'){
            player.keyRight = true;
        }
        if(e.getKeyChar() == 'w'){
            player.keyUp = true;
        }
        if(e.getKeyChar() == 's'){
            player.keyDown = true;
        }
        if(e.getKeyChar() == 'r'){
            reset();
        }
        if(e.getKeyChar() == 'h'){
            home = true;
        }


    }

    /**
     * this method manages when a key is released
     * @param e event listener variable
     */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'a'){
            player.keyLeft = false;
        }
        if(e.getKeyChar() == 'd'){
            player.keyRight = false;
        }
        if(e.getKeyChar() == 'w'){
            player.keyUp = false;
        }
        if(e.getKeyChar() == 's'){
            player.keyDown = false ;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * this method manages when the user clicks on the reset button using their mouse cursor
     * @param e event listener variable
     */
    public void mouseClicked(MouseEvent e) {
        if (restartRect.contains(new Point(e.getPoint().x, e.getPoint().y - 52))) {
            reset();
        }
    }
}