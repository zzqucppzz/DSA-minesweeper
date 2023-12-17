package Entity;

import java.util.Random;

import java.awt.event.MouseEvent;

public class Mine {

    private int[][] mines;
    private int[][] neighbors;
    private boolean[][] revealed;
    private boolean[][] flagged;
    private int row;
    private int column;


    private int firstX;
    private int firstY;    
    private int count;

    Board board;

    public Mine(int column, int row) {
        this.row = row;
        this.column = column;
        mines = new int[column][row];
        neighbors = new int[column][row];
        revealed = new boolean[column][row];
        flagged = new boolean[column][row];
    
        count = 0;
    }

    public void firstClick(int x, int y){

                
    }

    public void reset(){

    }    

    public void mouseMoved(MouseEvent e){
         
    }

    public void mouseClicked(MouseEvent e){
        if (board.inBoxX() != -1 && board.inBoxY() != -1){
            if (count == 0){
                firstX = board.inBoxX();
                firstY = board.inBoxY();
                firstClick(firstX, firstY);
                count++;
                
            }
        }
    }

    public void emptyCell(int i, int j){

    }

    public int totalMines(){
        int total = 0;
        for(int i = 0; i < column; i++){
            for(int j = 0; j < row; j++){
                if (mines[i][j] == 1){
                    total++;
                }
            }
        }
        return total;
    }

    public int totalBoxesRevealed(){
        int total = 0;
        for(int i = 0; i < column; i++){
            for(int j = 0; j < row; j++){
                if (revealed[i][j]){
                    total++;
                }
            }
        }
        return total;
    }

    public boolean isN(int mX, int mY, int cX, int cY){
        if (mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mines[cX][cY] == 1 && (mX != cX || mY != cY)){
            return true;
        }

        return false;
    }

    public void setBoard(Board value){
        this.board = value;
    }

    public int[][] getMines(){
        return mines;
    }

    public int[][] getNeighbors(){
        return neighbors;
    }

    public boolean[][] getRevealed(){
        return revealed;
    }

    public boolean[][] getFlagged(){
        return flagged;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }


}