package net.kalangos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tabuleiro {

	public static final int WIDTH = 6;
	public static final int HEIGHT = 6;

	public static int[][] TABULEIRO;

	public static int DOCE_0 = 0;
	public static int DOCE_1 = 1;
	public static int DOCE_2 = 2;

	public Tabuleiro() {
		TABULEIRO = new int[WIDTH][HEIGHT];

		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				TABULEIRO[x][y] = new Random().nextInt(3);
			}
		}
	}

	public void render(Graphics g) {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				g.setColor(Color.WHITE);
				g.drawRect(x * 48, y * 48, 48, 48);
				int doce = TABULEIRO[x][y];
				if (doce == DOCE_0) {
					g.setColor(Color.RED);
					g.fillRect(x * 48 + 12, y * 48 + 12, 25, 25);
				}
				if (doce == DOCE_1) {
					g.setColor(Color.GREEN);
					g.fillRect(x * 48 + 12, y * 48 + 12, 25, 25);
				}
				if (doce == DOCE_2) {
					g.setColor(Color.YELLOW);
					g.fillRect(x * 48 + 12, y * 48 + 12, 25, 25);
				}
			}
		}
	}
}
