package dev.noire.snake.entities;

import java.awt.Graphics;

public abstract class Entity {

	protected int x, y, width, height;
	
	public Entity(int x, int y, int tileSize) {
		this.x = x;
		this.y = y;
		width = tileSize;
		height = tileSize;
	}
	
	public abstract void paint(Graphics g);
	
	public int getX() {return x;}
	public int getY() {return y;}
}
