package dev.noire.snake.entities;

import java.awt.Color;
import java.awt.Graphics;

public class SnakePart extends Entity {

	public SnakePart(int x, int y, int tileSize) {
		super(x, y, tileSize);
	
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x*width, y*height, width, height);
		g.setColor(Color.YELLOW);
		g.fillOval((x*width)+2, (y*height)+2, width-4, height-4);
	}
	
}
