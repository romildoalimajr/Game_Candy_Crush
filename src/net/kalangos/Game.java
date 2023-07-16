package net.kalangos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 288, HEIGHT = 288;
	public static final int SCALE = 2;

	public static final int FPS = 1000 / 60;

	public Tabuleiro tabuleiro;

	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public static boolean selected = false;
	public static int previousx = 0, previousy = 0;
	public static int nextX = -1, nextY = -1;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		tabuleiro = new Tabuleiro();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Candy Crush - Kalango's.net");
		Game game = new Game();
		frame.add(game);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		new Thread(game).start();
	}

	public void update() {
		if (Game.selected && (Game.nextX != -1 && Game.nextY != -1)) {
			int posX1 = Game.previousx / 48;
			int posY1 = Game.previousy / 48;

			int posX2 = Game.nextX / 48;
			int posY2 = Game.nextY / 48;

			if ((posX2 == posX1 + 1 || posX2 == posX1 - 1) && posY2 == posY1 || posY2 == posY1 - 1
					|| posY2 == posY1 + 1) {
/*
				// evitar movimentos diagonais
				if ((posX2 >= posX1 + 1 || posX2 <= posX1 - 1) && (posY2 >= posY1 + 1 || posY2 <= posY1 - 1)) {
					// System.out.println("não pode mover");
					return;
				}
*/
				int val1 = Tabuleiro.TABULEIRO[posX2][posY2];
				int val2 = Tabuleiro.TABULEIRO[posX1][posY1];

				Tabuleiro.TABULEIRO[posX1][posY1] = val1;
				Tabuleiro.TABULEIRO[posX2][posY2] = val2;

				Game.nextX = -1;
				Game.nextY = -1;
				Game.selected = false;
				System.out.println("moveu");

			} else {
				// System.out.println("não pode mover");
			}
		} else {
			// System.out.println("não pode de jeito nenhum mover");
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		// inicio redenrização do jogo
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		tabuleiro.render(g);
		// final

		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		bs.show();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Game.selected == false) {
			Game.selected = true;
			Game.previousx = e.getX() / 2;
			Game.previousy = e.getY() / 2;
		} else {
			Game.nextX = e.getX() / 2;
			Game.nextY = e.getY() / 2;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
