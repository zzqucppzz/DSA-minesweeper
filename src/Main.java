package main2.Main;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.event.MouseEvent;

public class Main extends JFrame implements MouseListener {
	public class Main() {
		
	}
	public static void main(String [] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Minesweeper");
		
		GamePanel gamePanel = new GamePanel();
		window.getContentPane().add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExcited(MouseEvent e) {
		
	}
	

}
