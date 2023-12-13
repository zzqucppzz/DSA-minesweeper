package main2;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GamePanel extends JPanel  implements Runnable{
	final int originalTileSize = 16;
	final int scale = 3;

    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    int FPS = 60;
    Mouse_CR mouse = new Mouse_CR();
    Thread gameThread;
    
    //Set p
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouse);
        this.setFocusable(true);
        
        JLabel label = new JLabel("Position");
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;0
		while(gameThread != null) {
			//System.out.println("Game start!");
			
		}
		
	}

}
