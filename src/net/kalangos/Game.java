package net.kalangos;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 288, HEIGHT = 288;
	public static final int SCALE = 2;
	
	public static final int FPS = 1000/60;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	}
	
	public  static void main(String[] args) {
		JFrame frame = new JFrame("Candy Crush - Kalango's.net");
		Game game = new Game();
		frame.add(game);
		frame.setResizable(false);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
