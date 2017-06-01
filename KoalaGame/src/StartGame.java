import koalaGame.game.KoalaWorld;
import tankGame.game.GameWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartGame extends JComponent implements Runnable{
    static JFrame window;
    static String []arg;
    MouseControl mouse;
    BufferedImage background = null, title = null, start=null, quit=null;
    private Thread thread;
    public static void main(String[] args){
        arg = args;
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(640, 480);
        window.setLocationRelativeTo(null);
        window.getContentPane().add(new StartGame());
        window.setResizable(false);
        window.setVisible(true);
        GameWorld.sound.playLoop("resources/Music.wav");
    }

    public void paint(Graphics g) {
        mouse = new MouseControl();
        addMouseListener(mouse);
        try {
            background = ImageIO.read(getClass().getResource("resources/Background.png"));
            title = ImageIO.read(getClass().getResource("resources/Title.png"));
            start = ImageIO.read(getClass().getResource("resources/Button_start.png"));
            quit = ImageIO.read(getClass().getResource("resources/Button_quit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(background,0,0,this);
        g.drawImage(title,80,0,this);
        g.drawImage(start,40,350, this);
        g.drawImage(quit,500,350, this);
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

    public class MouseControl extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            //start
            if(e.getX()>=40 && e.getX() <= 40+start.getWidth() && e.getY()>=350 && e.getY()<=350+start.getHeight()) {
                window.dispose();
                removeMouseListener(mouse);
                KoalaWorld.init(0);
            }
            //quit
            if(e.getX()>=500 && e.getX() <= 500+start.getWidth() && e.getY()>=350 && e.getY()<=350+start.getHeight()) {
                System.exit(1);
            }
        }
    }
}

