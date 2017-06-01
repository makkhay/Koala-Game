package tankGame.game;

import java.awt.image.BufferedImage;

public class Explosion extends Sprites {
    int delay, now;

    public Explosion(BufferedImage img, int initialX, int initialY, int sprite, int totalSprites) {
        super(img, initialX, initialY, sprite, totalSprites, "explosion");
     
        delay = 3;
        now = 0;
    }
    /**
     * to display bullet explosion
     */
    
    public void drawSmallExplosion(){
            draw(game.g2, game);
            now++;
            if(now>delay){
                now=0;
                sprite++;
            }
    }
    /**
     * to display big explosion after destroying a tank
    
    */
    
    public void deathExplosion(){
         draw(game.g2, game);
            now++;
            if(now>delay){
                now=0;
                sprite++;
            }
    }
    
    public boolean finish(){
        return sprite >= totalSprites;
    }
}
