package tankGame.game;

import java.awt.image.BufferedImage;

/**
 * 
 * @author makkhay
 */

public class UnbreakableWall extends BackgoundObject{
    public UnbreakableWall(BufferedImage img, int x, int y) {
        super(img, x, y);
        setAccess(false);
        visible = true;
    }
}
