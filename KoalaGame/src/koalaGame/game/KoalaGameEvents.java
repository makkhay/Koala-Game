package koalaGame.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import tankGame.game.*;

public class KoalaGameEvents {
    int[] p1,p2,p3;
    int rescued = 0;
    KoalaWorld game = KoalaWorld.getInstance();

    public void process(ArrayList<koalaPlayer> players, ArrayList<Sprites> sp){
        move(players);
        checkColision(players, sp);
    }

    public void pressed(KeyEvent e, ArrayList<koalaPlayer> players) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            for(int i=0; i<players.size();i++) {
                players.get(i).keys[0] = true;
                players.get(i).sprite = 25;
                
                
            }
            

            
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            for(int i=0; i<players.size(); i++) {
                players.get(i).keys[1] = true;
                players.get(i).sprite = 0;
                
             
            }
            
     
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            for(int i=0; i<players.size(); i++) {
                players.get(i).keys[2] = true;
                players.get(i).sprite = 8;
                
                
                
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            for(int i=0; i<players.size(); i++) {
                players.get(i).keys[3] = true;
                players.get(i).sprite = 16;
                
               
            }
            
        }
    }

    public void released(KeyEvent e, ArrayList<koalaPlayer> players){
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            for(int i=0; i<players.size(); i++)
                players.get(i).keys[0] = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            for(int i=0; i<players.size(); i++)
                players.get(i).keys[1] = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            for(int i=0; i<players.size(); i++)
                players.get(i).keys[2] = false;
        if(e.getKeyCode() == KeyEvent.VK_UP)
            for(int i=0; i<players.size(); i++)
                players.get(i).keys[3] = false;
        
        
        
        
        
    }

    public void clicked(MouseEvent e){
        if(e.getX()>=530 && e.getX() <= 530+game.getSprites().get("restart").getWidth() && e.getY()>=5 && e.getY()<=5+game.getSprites().get("restart").getHeight())
            game.restart();
    }

    public void move(ArrayList<koalaPlayer> players){
        p1 = new int[2];
        p2 = new int[2];
        p3 = new int[2];
        p1[0] = players.get(0).getX();
        p1[1] = players.get(0).getY();
        p2[0] = players.get(1).getX();
        p2[1] = players.get(1).getY();
        p3[0] = players.get(2).getX();
        p3[1] = players.get(2).getY();
        for(int i=0; i<players.size(); i++)
            players.get(i).move();
    }
   
    public void checkColision(ArrayList<koalaPlayer> players, ArrayList<Sprites> sp){
        Rectangle boxP1 = new Rectangle(players.get(0).getX(), players.get(0).getY(),35,35);
        Rectangle boxP2 = new Rectangle(players.get(1).getX(), players.get(1).getY(),35,35);
        Rectangle boxP3 = new Rectangle(players.get(2).getX(), players.get(2).getY(),35,35);
       
        
          
        //check colision with unbreakable wall
        for(int i=0; i<sp.size(); i++){
            Rectangle sprite = new Rectangle(sp.get(i).getX(), sp.get(i).getY(),
                    sp.get(i).getImg().getWidth()/2,sp.get(i).getImg().getHeight()/2);
            if(boxP1.intersects(sprite) && players.get(0).show){
                if("exit".equals(sp.get(i).getType())){
                    rescued++;
                    GameWorld.sound.play("resources/Saved.wav");
                    players.get(0).show=false;
                    game.rescued.add(new Sprites(game.getSprites().get("koalaRescued"),150,5,0,1,"koalarescued"));
                }
                 if("exit2".equals(sp.get(i).getType())){
                    rescued++;
                    GameWorld.sound.play("resources/Saved.wav");
                    players.get(0).show=false;
                    game.rescued.add(new Sprites(game.getSprites().get("koalaRescued"),150,5,0,1,"koalarescued"));
                     sp.get(i).sprite=1;
                     removeExit2(sp);
                }
                else if("key1".equals(sp.get(i).getType())){
                   
                     GameWorld.sound.play("resources/Lock.wav");
                     players.get(0).show=true;
                     sp.get(i).sprite=1;
                     key(sp);
                     openLock(sp);
                 
                } 
                if("tnt".equals(sp.get(i).getType())){
                    GameWorld.sound.play("resources/Explosion_small.wav");
                    players.get(0).show=false;
                      game.gameOver();
                 
                   
                    
                }
                 if("saw".equals(sp.get(i).getType())){
                    GameWorld.sound.play("resources/Saw.wav");
                    players.get(0).show=false;
                      game.gameOver();
                   
                }
                
                if("detonator".equals(sp.get(i).getType())){
                    sp.get(i).sprite=1;
                    removeTnt(sp);
                }
                

                 
                players.get(0).setX(p1[0]);
                players.get(0).setY(p1[1]);
            }
            if(boxP2.intersects(sprite) && players.get(1).show){
                if("exit".equals(sp.get(i).getType())){
                    rescued++;
                    GameWorld.sound.play("resources/Saved.wav");
                    players.get(1).show=false;
                    game.rescued.add(new Sprites(game.getSprites().get("koalaRescued"),200,5,0,1,"koalarescued"));
                }
              
                 if("exit2".equals(sp.get(i).getType())){
                    rescued++;
                    GameWorld.sound.play("resources/Saved.wav");
                    players.get(1).show=false;
                    game.rescued.add(new Sprites(game.getSprites().get("koalaRescued"),150,5,0,1,"koalarescued"));
                     sp.get(i).sprite=1;
                     removeExit2(sp);
                 }
                 
                  else if("key1".equals(sp.get(i).getType())){
                   
                     GameWorld.sound.play("resources/Lock.wav");
                     players.get(1).show=true;
                     sp.get(i).sprite=1;
                     key(sp);
                     openLock(sp);
                 
                }
          
                
                if("tnt".equals(sp.get(i).getType())){
                    GameWorld.sound.play("resources/Explosion_small.wav");
                    players.get(1).show=false;
                      game.gameOver();
                }
                 if("saw".equals(sp.get(i).getType())){
                    GameWorld.sound.play("resources/Saw.wav");
                    players.get(1).show=false;
                    game.gameOver();
                }
                if("detonator".equals(sp.get(i).getType())){
                    sp.get(i).sprite=1;
                    removeTnt(sp);
                }
                players.get(1).setX(p2[0]);
                players.get(1).setY(p2[1]);
            }
            if(boxP3.intersects(sprite) && players.get(2).show){
                if("exit".equals(sp.get(i).getType())){
                    rescued++;
                    GameWorld.sound.play("resources/Saved.wav");
                    players.get(2).show=false;
                    game.rescued.add(new Sprites(game.getSprites().get("koalaRescued"),250,5,0,1,"koalarescued"));
                }
                
                if("exit2".equals(sp.get(i).getType())){
                    rescued++;
                    GameWorld.sound.play("resources/Saved.wav");
                    players.get(2).show=false;
                    game.rescued.add(new Sprites(game.getSprites().get("koalaRescued"),150,5,0,1,"koalarescued"));
                     sp.get(i).sprite=1;
                     removeExit2(sp);
                }
                
                 else if("key1".equals(sp.get(i).getType())){
                   
                     GameWorld.sound.play("resources/Lock.wav");
                     players.get(2).show=true;
                     sp.get(i).sprite=1;
                     key(sp);
                     openLock(sp);
                 
                }
 
                if("tnt".equals(sp.get(i).getType())){
                    GameWorld.sound.play("resources/Explosion_small.wav");
                    players.get(2).show=false;
                    game.gameOver();
                     
                }
                 if("saw".equals(sp.get(i).getType())){
                    GameWorld.sound.play("resources/Saw.wav");
                    players.get(2).show=false;
                    game.gameOver();
                }
                if("detonator".equals(sp.get(i).getType())){
                    sp.get(i).sprite=1;
                    removeTnt(sp);
                }
                players.get(2).setX(p3[0]);
                players.get(2).setY(p3[1]);
            }
            if(boxP1.intersects(boxP2)){
                players.get(0).setX(p1[0]);
                players.get(0).setY(p1[1]);
            }
            if(boxP2.intersects(boxP3)){
                players.get(1).setX(p2[0]);
                players.get(1).setY(p2[1]);
            }
            if(rescued == 3) {
                rescued=0;
                game.nextLevel();
            }
           
       
         
        }
        
        
    }
    
       
    
    public void removeTnt(ArrayList<Sprites>sp){
        for(int i=0; i<sp.size();i++){
            if("tnt".equals(sp.get(i).getType())){
                sp.remove(i);
            }
            
            
        }
    }
        public void removeExit2(ArrayList<Sprites>sp)
        {
              for(int i=0; i<sp.size();i++)
              {
                  if("exit2".equals(sp.get(i).getType()))
                  {
                      sp.remove(i);
                  }
              }
        }
        public void key(ArrayList<Sprites>sp)
        {
              for(int i=0; i<sp.size();i++)
              {
                  if("key1".equals(sp.get(i).getType()))
                  {
                      sp.remove(i);
                  }
              }
        }
        
         public void openLock(ArrayList<Sprites>sp)
        {
              for(int i=0; i<sp.size();i++)
              {
                  if("lock1".equals(sp.get(i).getType()))
                  {
                      sp.remove(i);
                  }
              }
        }
        
    
    }



