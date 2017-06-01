package tankGame.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends JPanel{
    private BufferedImage ground;
    private int width, height; //width and height

    public Background(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void initBackground(BufferedImage img){
        ground = img;
    }

    public void drawBackground(Graphics g){
        int TileWidth = ground.getWidth(this);
        int TileHeight = ground.getHeight(this);
        int NumberX = width / TileWidth;
        int NumberY = height / TileHeight;
        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g.drawImage(ground, j * TileWidth, i * TileHeight, TileWidth, TileHeight, this);
            }
        }
    }
}
