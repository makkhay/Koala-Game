package tankGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/*
* @author Prakash Gurung
*/

public class BreakableWall extends BackgoundObject {
    int timer = 350;
    // making constructor to copy image and int values
    public BreakableWall(BufferedImage img, int x, int y) {
        super(img, x, y);
        setAccess(false);
        visible = true;
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {
        if (!visible) {
            this.timer--;
            if (this.timer < 0) {
                this.timer = 350;
                this.visible = true;   // Wall will be rebuilt after 3.5 secs.
            }
        } else {
            super.draw(g, obs);
        }
    }
}
