package dev.noire.snake.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Food extends Entity{

	public Food(int x, int y, int tileSize) {
		super(x, y, tileSize);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(x*width, y*height, width, height);
	}
	
}
