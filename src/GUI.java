import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    int spacing = 5;
    public GUI(){
        this.setTitle("Minesweeper");
        this.setSize(1286, 829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

    }

    public class Board extends JPanel {
        public void paintComponent(Graphics g){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 1280, 800);
            g.setColor(Color.gray);
            for (int i = 0; i< 16; i++){
                for (int j = 0; j < 9; j++){
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
            System.out.println("The mouse is moving!");
        }
    }


}