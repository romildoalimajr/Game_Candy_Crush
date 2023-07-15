package net.kalangos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 288, HEIGHT = 288;
	public static final int SCALE = 2;
	
	public static final int FPS = 1000/60;
	
	public Tabuleiro tabuleiro;
	
	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		tabuleiro = new Tabuleiro();
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
	
	public void update() {
		System.out.println("Rodando");
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		//inicio redenrização do jogo
		g.setColor(Color.CYAN);
		g.fillRect(0,0,WIDTH, HEIGHT);
		tabuleiro.render(g);
		//final
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bs.show();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			update();
			render();
			try {
				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
