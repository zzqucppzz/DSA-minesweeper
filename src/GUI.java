import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    int spacing = 5;
    int neighs = 0;

    public int mx = -100;
    public int my = -100;

    Random rand = new Random();

    int [] [] mines = new int[16][9];
    int [][] neighbours = new int [16][9]; //up to 8 neighbour boxes
    boolean[][] revealed = new boolean[16][9];
    boolean[][] flagged = new boolean[16][9];
    public GUI(){
        this.setTitle("Minesweeper");
        this.setSize(1286, 829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setResizable(false);
        for (int i = 0; i< 16; i++){
            for (int j = 0; j < 9; j++){
                if (rand.nextInt(100) < 20){
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;

            }
        }
        for (int i = 0; i< 16; i++){
            for (int j = 0; j < 9; j++){
                neighs = 0;
                for (int m = 0; m< 16; m++){
                    for (int n = 0; n < 9; n++){
                        if(!(m==i && n ==j)) {
                            if (isN(i, j, m, n) == true)
                                neighs++;
                        }
                    }
                }
                neighbours[i][j] = neighs;

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
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 1280, 800);
            g.setColor(Color.gray);
            for (int i = 0; i< 16; i++){
                for (int j = 0; j < 9; j++){
                    g.setColor(Color.gray);
                    if (mines[i][j] == 1){
                        g.setColor(Color.yellow);
                    }
                    if (mx >= spacing+i*80 && mx < spacing+i*80+80-spacing && my >= spacing +j*80+80+26 && my < spacing +j*80+80+80 - 2*spacing){
                        g.setColor(Color.red);
                    }
                    g.fillRect(spacing+i*80,spacing +j*80+80, 80 - 2*spacing, 80 - 2*spacing);

                }
            }

        }
    }
    public class Move implements MouseMotionListener{
        @Override
        public void mouseDragged (MouseEvent arg0){
            // TODO auto-generated method stub
        }

        @Override
        public void mouseMoved(MouseEvent e){
            mx = e.getX();
            my = e.getY();
            /*
            System.out.println("The mouse is moving!");
            mx = e.getX();
            my = e.getY();
            System.out.println("X: " + mx + "; Y:" + my);

             */
        }
    }
    public class Click implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent arg0){
            if(inBoxX() != -1 && inBoxY() != -1){
                revealed[inBoxX()][inBoxY()] = true;
            }
            if (inBoxX() != -1 && inBoxY() != -1){
                System.out.println("The mouse is in the [" + inBoxX() + ", "+ inBoxY() +"], Number of mine neighbours: "+ neighbours[inBoxX()][inBoxY()]);
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO auto-generated method stub
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO auto-generated method stub
        }
        @Override
        public void mouseReleased(MouseEvent e){
            // TODO auto-generated method stub
        }
        @Override
        public void mousePressed(MouseEvent e){
            // TODO auto-generated method stub
        }
    }

    public int inBoxX(){
        for (int i = 0; i< 16; i++){
            for (int j = 0; j < 9; j++){
                if (mx >= spacing+i*80 && mx < spacing+i*80+80-spacing && my >= spacing +j*80+80+26 && my < spacing +j*80+80+80 - 2*spacing){
                    return i;
                }
            }
        }
        return -1;

    }

    public int inBoxY(){
        for (int i = 0; i< 16; i++){
            for (int j = 0; j < 9; j++){
                if (mx >= spacing+i*80 && mx < spacing+i*80+80-spacing && my >= spacing +j*80+80+26 && my < spacing +j*80+80+80 - 2*spacing){
                    return j;
                }
            }
        }
        return -1;
    }

    public boolean isN(int mX, int mY, int cX, int cY ){
        if (mX - cX < 2 && mX - cX > -2 && mY - cY <2 && mY - cY >-2 && mines[cX][cY]==1 ){ //2 boxes are next to each other or they are neighbors
            return true;
        }
        return false;

    }


}