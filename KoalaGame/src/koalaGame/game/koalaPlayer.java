package koalaGame.game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tankGame.game.*;

public class koalaPlayer extends Sprites {

    protected ArrayList<Bullet> bullets;
    private final int DEGREES = 6; //degree for each rotation. degree * sprite = angle 360/60sprites = 6
    boolean[] keys = new boolean[4]; //left, down, right, up,fire
    int rotation, initialX,initialY,initialSprite;
    boolean show = true;
    public koalaPlayer(BufferedImage img, int initialX, int initialY, int sprite, int totalSprites){
        super(img, initialX, initialY, sprite, totalSprites,"player");
        for(int i=0; i<=3; i++)
            keys[i]=false;
        bullets = new ArrayList<>();
        rotation = DEGREES*sprite;
        this.initialY = initialY;
        this.initialX = initialX;
        this.initialSprite = sprite;
    }

    public void move(){
        if(keys[0]) moveLeft();
        if(keys[1]) moveDown();
        if(keys[2]) moveRight();
        if(keys[3]) moveUp();
    }

    public void moveLeft(){
        x-=getSpeed();
        sprite++;
        if(sprite>30) sprite=26;
    }
    public void moveRight(){
        x+=getSpeed();
        sprite++;
        if(sprite>15) sprite=8;
    }
    public void moveUp(){
        y -= getSpeed();
        sprite++;
        if(sprite>23) sprite=16;
    }
    public void moveDown(){
        y += getSpeed();
        sprite++;
        if(sprite>7) sprite=0;
    }

   
}
