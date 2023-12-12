package GUI;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GUI extends JFrame{

    public boolean resetter = false;

    public boolean flagger = false;

    Date startDate = new Date();
    Date endDate;

    int spacing = 5;
    int width = 16*50 + (16 + 1)*5;
    int height = 10*50 + 10*5;
    int dimension = 50;
    int row = 16;
    int column = 9;

    int neighs = 0;

    String vicMes = "Nothing yes!";

    public int mx = -100;
    public int my = -100;

    public int smileyX = (width - dimension)/2;
    public int smileyY = 5 / 2;

    public int smileyCenterX = smileyX + 25;
    public int smileyCenterY = smileyY + 25;

    public int flaggerX = 345;
    public int flaggerY = 5 / 2;

    public int flaggerCenterX = flaggerX + 25;
    public int flaggerCenterY = flaggerY + 25;

    public int timeX = 800;
    public int timeY = 5;

    public int vicMesX = 500;
    public int vicMesY = -200;

    public int sec = 0;

    public boolean happiness = true;

    public boolean victory = false;

    public boolean defeat = false;

    Random rand = new Random();

    int[][] mines = new int[row][column];
    int[][] neighbors = new int[row][column];
    boolean[][] revealed = new boolean[row][column];
    boolean[][] flagged = new boolean[row][column];

    public GUI(){
        this.setTitle("MineSweeper");
        this.setSize(width + 13, height + 29 + 8);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if (rand.nextInt(100) < 20){
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
            }
        }
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                neighs = 0;
                for(int m = 0; m < row; m++){
                    for(int n = 0; n < column; n++){
                        if (isN(i, j, m, n) == true){
                            neighs++;
                        }
                    }
                }
                neighbors[i][j] = neighs;
            }
        }

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);


    }

    public class Board extends JPanel {
        public void paintComponent(Graphics g){
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, width, height);

            for(int i = 0; i < row; i++){
                for(int j = 0; j < column; j++){
                    g.setColor(Color.gray);
                    if (mines[i][j] == 1){
                        g.setColor(Color.yellow);
                    }
                    if (revealed[i][j] == true){
                        g.setColor(Color.white);
                        if (mines[i][j] == 1){
                            g.setColor(Color.red);
                        }
                    }
                    if (mx >= spacing + i*(dimension + spacing) && mx <= spacing + i*(dimension + spacing) + dimension && my >= (j + 1)*(dimension + spacing) && my <= (j + 1)*(dimension + spacing) + dimension){
                        g.setColor(Color.lightGray);
                    }
                    g.fillRect(spacing + i*(dimension + spacing), (j + 1)*(dimension + spacing),dimension,dimension);
                    
                    if (revealed[i][j] == true){
                        g.setColor(Color.black);
                        if (mines[i][j] == 0 && neighbors[i][j] != 0){
                            switch (neighbors[i][j]) {
                                case 1:
                                    g.setColor(Color.blue);
                                    break;
                                case 2:
                                    g.setColor(Color.green);
                                    break;
                                case 3:
                                    g.setColor(Color.red);
                                    break;               
                                case 4:
                                    g.setColor(new Color(0,0,128));
                                    break;
                                case 5:
                                    g.setColor(new Color(178,34,34));
                                    break;               
                                case 6:
                                    g.setColor(new Color(72,209,204));
                                    break;  
                                case 7:
                                    g.setColor(Color.darkGray);
                                    break;                                                        
                                default:
                                    break;
                            }
                            g.setFont(new Font("Tahoma", Font.BOLD, 40));
                            g.drawString(Integer.toString(neighbors[i][j]),spacing + dimension/4 + i*(dimension + spacing), 4*dimension/5 + (j + 1)*(dimension + spacing));
                        }
                        else if (mines[i][j] == 1) {
                            g.fillRect(spacing + dimension/5 + 5 + i*(dimension + spacing),dimension/5 - 5 + (j + 1)*(dimension + spacing), 20 , 40 );
                            g.fillRect(spacing + dimension/5 - 5 + i*(dimension + spacing),dimension/5 + 5 + (j + 1)*(dimension + spacing), 40 , 20 );
                            g.fillRect(spacing + dimension/5 + i*(dimension + spacing),dimension/5 + (j + 1)*(dimension + spacing), 30 , 30 );
                        }
                    }

                    if (flagged[i][j]){
                        g.setColor(Color.black);
                        g.fillRect(spacing + 2*dimension/5 + i*(dimension + spacing),(j + 1)*(dimension + spacing) + dimension/5, 5, 30);
                        g.fillRect(spacing + 2*dimension/5 + i*(dimension + spacing) - 12, (j + 1)*(dimension + spacing) + dimension/5 + 25, 30, 10);
                        g.setColor(Color.red);
                        g.fillRect(spacing + 2*dimension/5 + i*(dimension + spacing) - 10, (j + 1)*(dimension + spacing) + dimension/5, 10, 10);                         
                    }
                }
            }

            // smiley painting

            g.setColor(Color.yellow);
            g.fillOval(smileyX,smileyY, 50, 50);
            g.setColor(Color.black);
            g.fillOval(smileyX + 10,smileyY + 15, 5, 5);
            g.fillOval(smileyX + 35,smileyY + 15, 5, 5);

            if (happiness == true){
                g.fillRect(smileyX + 15,smileyY + 35,20,5);
                g.fillRect(smileyX + 15 - 5,smileyY + 35 - 5,5,5);
                g.fillRect(smileyX + 15 + 20,smileyY + 35 - 5,5,5);
            } else{
                g.fillRect(smileyX + 15,smileyY + 35,20,5);
                g.fillRect(smileyX + 15 - 5,smileyY + 35 + 5,5,5);
                g.fillRect(smileyX + 15 + 20,smileyY + 35 + 5,5,5);
            }

            // time counter painting
            g.setColor(Color.black);
            g.fillRect(timeX, timeY,80,50);
            if (!defeat && !victory){
                sec = (int) (new Date().getTime() - startDate.getTime())/1000;
            }
            if (sec > 999){
                sec = 999;
            }
            g.setColor(Color.white);
            if (victory){
                g.setColor(Color.green);
            }
            else if (defeat) {
                g.setColor(Color.red);
            }
            g.setFont(new Font("Tahoma", Font.BOLD, 40));

            g.drawString(Integer.toString(sec),timeX,timeY + 40);

            //victory message painting
            if (victory){
                g.setColor(Color.green);
                vicMes = "YOU WIN";
            } else if (defeat){
                g.setColor(Color.red);
                vicMes = "YOU LOSE";
            }

            if (victory || defeat){
                vicMesY = - 50 + (int) (new Date().getTime() - endDate.getTime())/10;
                if (vicMesY > 70){
                    vicMesY = 70;
                }
                g.setFont(new Font("Tahoma", Font.PLAIN, 70));
                g.drawString(vicMes, vicMesX, vicMesY);
            }

            //flagger painting
            g.setColor(Color.black);
            g.fillRect(flaggerX + 22,flaggerY + 5, 5, 30);
            g.fillRect(flaggerX + 10, flaggerY + 30, 30, 10);
            g.setColor(Color.red);
            g.fillRect(flaggerX + 12, flaggerY + 5, 10, 10);  
            g.setColor(Color.black);
            if (flagger == true){
                g.setColor(Color.red);
            }
            g.drawOval(flaggerX ,flaggerY, 50, 50);        
        }
    }

    public class Move implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX() - 7;
            my = e.getY() - 30;
            //System.out.println("X:" + mx + " Y:" + my);
        }
        
    }

    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            mx = e.getX() - 7;
            my = e.getY() - 30;

            if (inBoxX() != -1 && inBoxY() != -1){
                System.out.println("The mouse is in X:" + inBoxX() + " Y:" + inBoxY() + " Number of neighs: " + neighbors[inBoxX()][inBoxY()]);
                if (flagger && !revealed[inBoxX()][inBoxY()]){
                    if (!flagged[inBoxX()][inBoxY()])
                        flagged[inBoxX()][inBoxY()] = true;
                    else
                        flagged[inBoxX()][inBoxY()] = false;
                } else {
                    if (!flagged[inBoxX()][inBoxY()])
                        revealed[inBoxX()][inBoxY()] = true;
                }
            }
            else {
                System.out.println("Not in any box");
            }

            if (isSmiley()){
                resetAll();
            }

            if (isFlagger()){
                if (!flagger){
                    flagger = true;
                }else{
                    flagger = false;
                }
            }


        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
        }
        
    }

    public void checkVictoryStatus(){

        if (!defeat){
            for(int i = 0; i < row; i++){
                for(int j = 0; j < column; j++){
                    if (revealed[i][j] && mines[i][j] == 1){
                        defeat = true;
                        happiness = false;
                        endDate = new Date();
                    }
                }
            }
        }

        if (totalBoxesRevealed() >= row*column - totalMines() && !victory){
            victory = true;
            endDate = new Date();
        }
    }

    public int totalMines(){
        int total = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if (mines[i][j] == 1){
                    total++;
                }
            }
        }
        return total;
    }

    public int totalBoxesRevealed(){
        int total = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if (revealed[i][j]){
                    total++;
                }
            }
        }
        return total;
    }

    public void resetAll(){
        resetter = true;

        flagger = false;

        startDate = new Date();

        vicMesY = -200;

        happiness = true;
        victory = false;
        defeat = false;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if (rand.nextInt(100) < 20){
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
                flagged[i][j] = false;
            }
        }
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                neighs = 0;
                for(int m = 0; m < row; m++){
                    for(int n = 0; n < column; n++){
                        if (isN(i, j, m, n) == true){
                            neighs++;
                        }
                    }
                }
                neighbors[i][j] = neighs;
            }
        }

        resetter = false;
    }

    public boolean isSmiley(){
        int dif = (int) Math.sqrt(Math.abs(mx - smileyCenterX)*Math.abs(mx - smileyCenterX) + Math.abs(my - smileyCenterY)*Math.abs(my - smileyCenterY)); 
        if (dif < 25){
            return true;
        }
        return false;
    }

    public boolean isFlagger(){
        int dif = (int) Math.sqrt(Math.abs(mx - flaggerCenterX)*Math.abs(mx - flaggerCenterX) + Math.abs(my - flaggerCenterY)*Math.abs(my - flaggerCenterY)); 
        if (dif < 25){
            return true;
        }
        return false;
    }

    public int inBoxX(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if (mx >= spacing + i*(dimension + spacing) && mx <= spacing + i*(dimension + spacing) + dimension && my >= (j + 1)*(dimension + spacing) && my <= (j + 1)*(dimension + spacing) + dimension){
                    return i;
                }
            }
        }
        return -1;
    }

    public int inBoxY(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if (mx >= spacing + i*(dimension + spacing) && mx <= spacing + i*(dimension + spacing) + dimension && my >= (j + 1)*(dimension + spacing) && my <= (j + 1)*(dimension + spacing) + dimension){
                    return j;
                }
            }
        }
        return -1;
    }

    public boolean isN(int mX, int mY, int cX, int cY){
        if (mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mines[cX][cY] == 1 && (mX != cX || mY != cY)){
            return true;
        }

        return false;
    }

}
