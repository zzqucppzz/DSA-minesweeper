package Entity;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class Board {

    private int width;
    private int height;
    private int spacing;
    private int dimension;
    
    private Mine mine;
    private Flagger flagger;


    private int mx = 0;
    private int my = 0;

    private Stack gameSteps = new Stack();

    public Board(int width, int height,int spacing, int dimension ,Mine mine, Flagger flagger){
        this.width = width;
        this.height = height;
        this.spacing = spacing;
        this.dimension = dimension;
        this.mine = mine;
        this.flagger = flagger;

        this.mine.setBoard(this);
    }

    public void draw(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, width, height);
        
        for(int i = 0; i < mine.getColumn(); i++){
            for(int j = 0; j < mine.getRow(); j++){
                g.setColor(Color.gray);
                if (mine.getMines()[i][j] == 1){
                    g.setColor(Color.yellow);
                }
                if (mine.getRevealed()[i][j] == true){
                    g.setColor(Color.white);
                    if (mine.getMines()[i][j] == 1){
                        g.setColor(Color.red);
                    }
                }
                if (mx >= spacing + i*(dimension + spacing) && mx <= spacing + i*(dimension + spacing) + dimension && my >= (j + 1)*(dimension + spacing) && my <= (j + 1)*(dimension + spacing) + dimension){
                    g.setColor(Color.lightGray);
                }
                g.fillRect(spacing + i*(dimension + spacing), (j + 1)*(dimension + spacing),dimension,dimension);                

            }
        }

    }

    public void update(){
      
    }

    public void mouseMoved(MouseEvent e){
         
    }

    public void mouseClicked(MouseEvent e){
       
    }

    public  int[] popStack(){
        int[] a = {-1,-1};
        int num = (Integer) gameSteps.pop();
        int max = Math.max(mine.getColumn(), mine.getRow());
        if (num >= Math.max(mine.getColumn(), mine.getRow())*(Math.max(mine.getColumn(), mine.getRow()) + 1)){
            num -= Math.max(mine.getColumn(), mine.getRow())*(Math.max(mine.getColumn(), mine.getRow()) + 1);
        }
        for(int i = 0; i < mine.getColumn(); i++){
            for(int j = 0; j < mine.getRow(); j++){
                if (num == (max*i + j)){
                    a[0] = i;
                    a[1] = j;
                }
            }
        }

        return a;

    }

    public void pushStask(int column, int row, int value){

    }

    public void clearStack(){

    }

    public int inBoxX(){
        for(int i = 0; i < mine.getColumn(); i++){
            for(int j = 0; j < mine.getRow(); j++){
                if (mx >= spacing + i*(dimension + spacing) && mx <= spacing + i*(dimension + spacing) + dimension && my >= (j + 1)*(dimension + spacing) && my <= (j + 1)*(dimension + spacing) + dimension){
                    return i;
                }
            }
        }
        return -1;
    }

    public int inBoxY(){
        for(int i = 0; i < mine.getColumn(); i++){
            for(int j = 0; j < mine.getRow(); j++){
                if (mx >= spacing + i*(dimension + spacing) && mx <= spacing + i*(dimension + spacing) + dimension && my >= (j + 1)*(dimension + spacing) && my <= (j + 1)*(dimension + spacing) + dimension){
                    return j;
                }
            }
        }
        return -1;
    }
}

