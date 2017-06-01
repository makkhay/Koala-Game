package koalaGame.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Exit extends JComponent implements Runnable{
    static JFrame window;
    BufferedImage background = null, win = null;
    private Thread thread;

    public static void init(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(640, 480);
        window.setLocationRelativeTo(null);
        window.getContentPane().add(new Exit());
        window.setResizable(false);
        window.setVisible(true);
    }

    public void paint(Graphics g) {
        try {
            background = ImageIO.read(getClass().getResource("resources/Background.png"));
            win = ImageIO.read(getClass().getResource("resources/Congratulation.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(background,0,0,this);
        g.drawImage(win,80,0,this);
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
