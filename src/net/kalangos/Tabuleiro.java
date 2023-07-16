package net.kalangos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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

	public void update() {
		ArrayList<Candy> combos = new ArrayList<Candy>();
		for (int yy = 0; yy < HEIGHT; yy++) {
			if (combos.size() == 3) {
				// Tenho um combo!
				for (int i = 0; i < combos.size(); i++) {
					int xtemp = combos.get(i).x;
					int ytemp = combos.get(i).y;
					TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
				}
				combos.clear();
				System.out.println("pontuei");
				return;
			}
			combos.clear();
			for (int xx = 0; xx < WIDTH; xx++) {
				int cor = TABULEIRO[xx][yy];
				if (combos.size() == 3) {
					for (int i = 0; i < combos.size(); i++) {
						int xtemp = combos.get(i).x;
						int ytemp = combos.get(i).y;
						TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
					}
					combos.clear();
					System.out.println("pontuei");
					return;
				}
				if (combos.size() == 0) {
					combos.add(new Candy(xx, yy, cor));
				} else if (combos.size() > 0) {
					if (combos.get(combos.size() - 1).CANDY_TYPE == cor) {
						combos.add(new Candy(xx, yy, cor));
					} else {
						combos.clear();
						combos.add(new Candy(xx, yy, cor));
					}
				}
			}
		}
		
		combos = new ArrayList<Candy>();
		for (int xx = 0; xx < WIDTH; xx++) {
			if (combos.size() == 3) {
				// Tenho um combo!
				for (int i = 0; i < combos.size(); i++) {
					int xtemp = combos.get(i).x;
					int ytemp = combos.get(i).y;
					TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
				}
				combos.clear();
				System.out.println("pontuei");
				return;
			}
			combos.clear();
			for (int yy = 0; yy < HEIGHT; yy++) {
				//int cor = TABULEIRO[xx][yy];
				if (combos.size() == 3) {
					for (int i = 0; i < combos.size(); i++) {
						int xtemp = combos.get(i).x;
						int ytemp = combos.get(i).y;
						TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
					}
					combos.clear();
					System.out.println("pontuei");
					return;
				}
				if (combos.size() == 0) {
					combos.add(new Candy(xx, yy, cor));
				} else if (combos.size() > 0) {
					if (combos.get(combos.size() - 1).CANDY_TYPE == cor) {
						combos.add(new Candy(xx, yy, cor));
					} else {
						combos.clear();
						combos.add(new Candy(xx, yy, cor));
					}
				}
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
				if (Game.selected) {
					int posX = Game.previousx / 48;
					int posY = Game.previousy / 48;
					g.setColor(Color.black);
					g.drawRect(posX * 48, posY * 48, 48, 48);
				}
			}
		}

	}
}
