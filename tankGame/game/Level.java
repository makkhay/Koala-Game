package tankGame.game;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * This method parses a rectangular (or square) file and forms a 2D matrix from the
 * integer values of the file. 
 * @author makkhay
 */

public class Level {
    int start;
    Integer position;
    BufferedReader level;
    String filename;
    int w, h;

    public Level(String name){
        String line;
        try {
            filename = name;
            level = new BufferedReader(new InputStreamReader(getClass().getResource(filename).openStream()));
            line = level.readLine();
            w = line.length();
            h=0;
            while(line!=null){
                h++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void load(){
        GameWorld game = GameWorld.getInstance();
        String line;
        try {
            level = new BufferedReader(new InputStreamReader(GameWorld.class.getResource(filename).openStream()));
            line = level.readLine();
            w = line.length();
            h=0;
            while(line!=null){
                for(int i = 0, n = line.length() ; i < n ; i++) {
                    char c = line.charAt(i);

                    if(c=='1'){
                        game.addSprites(game.getSprites().get("wall1"),i*32,h*32,0,1,"wall"); //*32 = size of each img
                    }

                    if(c=='2'){
                        game.addBrWall(game.getSprites().get("wall2"), i * 32, h * 32); //*32 = size of each img
                    }

                    if(c=='3'){
                        game.addPlayer1(game.getSprites().get("player1"),i*32,h*32); //*32 = size of each img
                    }
                    if(c=='4'){
                        game.addPlayer2(game.getSprites().get("player2"),i*32,h*32); //*32 = size of each img
                    }               
                }
                h++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
