package Entity;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ImageIcon;

public class Smiley {

    private int smileyX;
    private int smileyY;

    private int smileyCenterX;
    private int smileyCenterY;

    private int mx = 0;
    private int my = 0;

    Mine mine;
    Flagger flagger;
    TimeCounter timeCounter;
    GameState gameState;
    
    private boolean happiness = true;

    public Smiley(int smileyX, int smileyY, Mine mine, Flagger flagger, TimeCounter timeCounter, GameState gameState){
        this.smileyX = smileyX;
        this.smileyY = smileyY;
        this.smileyCenterX = smileyX + 25;
        this.smileyCenterY = smileyY + 25;

        this.mine = mine;
        this.flagger = flagger;
        this.timeCounter = timeCounter;
        this.gameState = gameState;
        this.gameState.setSmiley(this);
        smileyIcon = new ImageIcon(graphic2D/bloom.png);
    }

    public void draw(Graphics g){
    
        g.setColor(Color.gray);
        g.fillOval(smileyX,smileyY, 50, 50);
        g.setColor(Color.black);
        g.fillOval(smileyX + 10,smileyY + 15, 5, 5);
        g.fillOval(smileyX + 35,smileyY + 15, 5, 5);
        Image smileyImage = smileyIcon.getImage();
        g.drawImage(smileyImage,smileyX, smileyY, null);
        
        if (happiness){
            g.fillRect(smileyX + 15,smileyY + 35,20,5);
            g.fillRect(smileyX + 15 - 5,smileyY + 35 - 5,5,5);
            g.fillRect(smileyX + 15 + 20,smileyY + 35 - 5,5,5);
        } else{
            g.fillRect(smileyX + 15,smileyY + 35,20,5);
            g.fillRect(smileyX + 15 - 5,smileyY + 35 + 5,5,5);
            g.fillRect(smileyX + 15 + 20,smileyY + 35 + 5,5,5);
        }        
    }

    public void mouseMoved(MouseEvent e){
        mx = e.getX() - 7;
        my = e.getY() - 30;         
    }

    public void mouseClicked(MouseEvent e){
        if (inSmiley()){
            resetAll();
        }
    }

    public void resetAll(){

        gameState.setResetter(true);
        gameState.setVicMesY(-200);
        gameState.setVictory(false);
        gameState.setDefeat(false);
        
        timeCounter.setStartDate(new Date());
        
        flagger.setFlagger(false);
        
        happiness = true;
        
        mine.reset();

        gameState.setResetter(false);
    }

    public void resetUndo(){
        gameState.setResetter(true);
        gameState.setVicMesY(-200);
        gameState.setVictory(false);
        gameState.setDefeat(false);
        
        happiness = true;

        gameState.setResetter(false);        
    }

    public boolean inSmiley(){
        int dif = (int) Math.sqrt(Math.abs(mx - smileyCenterX)*Math.abs(mx - smileyCenterX) + Math.abs(my - smileyCenterY)*Math.abs(my - smileyCenterY)); 
        if (dif < 25){
            return true;
        }
        return false;
    }

    public boolean getHappiness(){
        return happiness;
    }

    public void setHappiness(boolean value){
        this.happiness = value;
    }
}
