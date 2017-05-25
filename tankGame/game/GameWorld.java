package tankGame.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameWorld extends JPanel implements Runnable{
   //the images for a split screen
    private BufferedImage bimg, p1view,p2view;
    //basic data for applet
    private Thread thread;
    // To draw string
    Graphics g1;
    Graphics2D g2;
    protected int width,height,scoreP1=0,scoreP2=0; //width and height
    private Background background;
    // to hold boolean value of gameover
    boolean gameOver = false;
    protected ArrayList<Player> players;
    protected ArrayList<Sprites> sp;
    ArrayList<BreakableWall> walls2;
   //game events
    private GameEvents gameEvents;
    KeyControl key;
    private static final GameWorld game = new GameWorld();
    public static HashMap<String, BufferedImage> sprites;
    private static Level level;
    BufferedImage minimap;
    Point mapSize;
    public static final GameSounds sound = new GameSounds();

    private void initGameWorld(){
        setFocusable(true); //set focus to use the keylistener
        sprites = new HashMap<String, BufferedImage>();
        loadSprites();
        players = new ArrayList<>();
        sp = new ArrayList<>();
        walls2 = new ArrayList<>();
        // map file to be parsed
        level = new Level("resources/level.txt");
        level.load();
        mapSize = new Point(level.w * 32, level.h * 32);
        //background
        background = new Background(mapSize.x,mapSize.y);
        background.initBackground(sprites.get("background"));
        gameEvents = new GameEvents();
        key = new KeyControl();
        addKeyListener(key);
    }

    /**
     * Load everything into map. 
     */
    
    public void loadSprites(){
        sprites.put("background", getSprite("resources/Background.png"));
        sprites.put("player1", getSprite("resources/tank1_blue.png"));
        sprites.put("player2", getSprite("resources/Tank_red_light_strip60.png"));
        sprites.put("wall1", getSprite("resources/Wall1.png"));
        sprites.put("wall2", getSprite("resources/wall2.png"));
        sprites.put("bullet", getSprite("resources/bullet.png"));
        sprites.put("smallExplosion", getSprite("resources/small_explosion6.png"));
       
    }
    public BufferedImage getSprite(String name){
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public class KeyControl extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            gameEvents.pressed(e,players);
        }
        public void keyReleased(KeyEvent e) {
            gameEvents.released(e, players);
        }

    }

    public void addSprites(BufferedImage img, int x, int y, int sprite, int total, String type){
        sp.add(new Sprites(img, x, y,sprite,total,type));
    }
    public void addBrWall(BufferedImage img, int x, int y){
        walls2.add(new BreakableWall(img, x, y));
    }
    public void addPlayer1(BufferedImage img, int x, int y){
        players.add(new Player(img, x, y,0,60));
    }
    public void addPlayer2(BufferedImage img, int x, int y){
        players.add(new Player(img, x, y, 30, 60));
    }

    @Override
    public void paint(Graphics g){
        if(bimg == null) {
            bimg = (BufferedImage) createImage(mapSize.x, mapSize.y);
            g2 = bimg.createGraphics();
            minimap = bimg;
        }
        draw();
        // Creating minimap 
        Image mini = minimap.getScaledInstance(this.getHeight() / 5,minimap.getWidth()*this.getHeight() / (5*minimap.getHeight()), BufferedImage.SCALE_FAST);
        Dimension windowSize = getSize();
        int p1x = players.get(0).getX() - windowSize.width / 4 > 0 ? players.get(0).getX() - windowSize.width / 4 : 0;
        int p1y = players.get(0).getY() - windowSize.height / 2 > 0 ? players.get(0).getY() - windowSize.height / 2 : 0;
        // Game map initializer 
        if (p1x > mapSize.x - windowSize.width / 2) {
            p1x = mapSize.x - windowSize.width / 2;
        }
        if (p1y > mapSize.y - windowSize.height) {
            p1y = mapSize.y - windowSize.height;
        }

        int p2x = players.get(1).getX() - windowSize.width / 4 > 0 ? players.get(1).getX() - windowSize.width / 4 : 0;
        int p2y = players.get(1).getY() - windowSize.height / 2 > 0 ? players.get(1).getY() - windowSize.height / 2 : 0;

        if (p2x > mapSize.x - windowSize.width / 2) {
            p2x = mapSize.x - windowSize.width / 2;
        }
        if (p2y > mapSize.y - windowSize.height) {
            p2y = mapSize.y - windowSize.height;
        }
        p1view = bimg.getSubimage(p1x, p1y, windowSize.width / 2, windowSize.height);
        p2view = bimg.getSubimage(p2x, p2y, windowSize.width / 2, windowSize.height);
        g.drawImage(p1view, 0, 0, this);
        g.drawImage(p2view, windowSize.width / 2, 0, this);
        g.drawRect(windowSize.width / 2 - 1, 0, 1, windowSize.height);
        g.drawImage(mini, windowSize.width/2-75, 450, this);
        Font f = new Font("BOLD", Font.BOLD, 25);
        //set font
        g.setFont(f);
        // set color font
        g.setColor(Color.YELLOW);
        if(players.get(0).life>40){
        	g.setColor(Color.BLUE);
        }
        int x=0, y =0;
        g.fillRect(x+2, y-25, (int) Math.round(players.get(0).life*1.1), 20);
    
        /**
         * Display player info such as health and score.
         */
        //player 1 graphics life 
        g.setColor(Color.gray);
        g.fillRect(130,648, 50, 25);
        
        g.setColor(Color.green);
        g.fillRect(130,648, players.get(0).life , 25);
        
        g.setColor(Color.white);
        g.drawRect(130, 648, 50, 25);
        
        //player 2 graphics life 
        g.setColor(Color.gray);
        g.fillRect(945,648, 50, 25);
        
        g.setColor(Color.green);
        g.fillRect(945,648, players.get(1).life , 25);
        
        g.setColor(Color.green);
        g.fillRect(945,648, players.get(1).life , 25);
        
        g.setColor(Color.white);
        g.drawRect(945, 648, 50, 25);
        g.drawString(" Player 1", getWidth() -160, getHeight() -180);
        g.drawString("Player 2", x + 30, getHeight() -180);
        g.drawString("Life: " + players.get(0).life, 32, getHeight() - 60);  
        g.drawString("Life: " + players.get(1).life, getWidth() - 150, getHeight() - 60);
        g.drawString("Score: " + scoreP1, 32, getHeight() - 120);
        g.drawString("Score: " + scoreP2, getWidth() - 150, getHeight() - 120);
        /**
         * display who won
         */
        if(players.get(0).life == 1)
        {
            g.drawString(" Player 1 Win", getWidth() -200, getHeight() -280);
        }
        else if (players.get(0).life ==1 )
        {
            g.drawString(" Player 2 Win", getWidth() -200, getHeight() -280);
        }
        
        }
        
    private void draw(){
        background.drawBackground(g2);
        gameEvents.process(players, sp, walls2);
        ArrayList<Bullet> b;

       for(int i=0; i<sp.size();i++) {
            sp.get(i).draw(g2, this);
        }
        for(int i=0; i<walls2.size(); i++){
            walls2.get(i).draw(g2, this);
        }

        for(int i=0; i<players.size(); i++) {
            b=players.get(i).getBullets();
            for (Bullet bu : b) {
                if(bu.visible) {
                    bu.draw(g2, this);
                    bu.update();
                }
            }
            if(players.get(i).show)
                players.get(i).draw(g2, this);
        }

        for(int i=0; i<players.size(); i++){

            for (int j=0; j<players.get(i).explosions.size();j++){
                Explosion exp = players.get(i).explosions.get(j);
                if(!exp.finish()) {
                    exp.drawSmallExplosion();
                } else {
                    players.get(i).explosions.remove(exp);
                }
            }
        }
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
    // setting screen dimensions
    public void setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public static GameWorld getInstance() {
        return game;
    }
    public HashMap<String, BufferedImage> getSprites() {
        return sprites;
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    /**
     * method is called when a player dies
     */
    public void gameOver(){
        gameOver=false;
        removeKeyListener(key);
        game.initGameWorld();
        
        
    }
   // main method to run the game
    public static void main(String argv[]) {
        final GameWorld game = GameWorld.getInstance();
        JFrame f = new JFrame("Tank Battle");
        f.addWindowListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                game.requestFocusInWindow();
            }
        });
        f.getContentPane().add("Center", game);
        f.pack();
        f.setSize(new Dimension(1000, 750));
        f.setLocationRelativeTo(null); // game opens in the middle of the screen
        game.setDimensions(800, 600);
        game.initGameWorld();
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameWorld.sound.playLoop("resources/true.wav");
        game.start();
        
        
        
    }
}
