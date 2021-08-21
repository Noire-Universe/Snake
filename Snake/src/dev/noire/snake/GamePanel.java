package dev.noire.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dev.noire.snake.entities.Entity;
import dev.noire.snake.entities.Food;
import dev.noire.snake.entities.SnakePart;

public class GamePanel extends JPanel implements Runnable {
	
	public static final int WIDTH = 300, HEIGHT = 300;
	
	private boolean running, right, left, up, down;
	private int x, y, tile, size, count, speed, tick;
	
	private Thread thread;
	private Key k;
	
	private ArrayList<Entity>snake;
	private ArrayList<Entity>apples;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		k = new Key();
		this.addKeyListener(k);
		init();
		snake = new ArrayList<Entity>();
		apples = new ArrayList<Entity>();
		start();
	}
	
	private void init() {
		running = false;
		right = true;
		left = false;
		up = false;
		down = false;
		x = 10;
		y = 10;
		tile = 10;
		size = 5;
		count = 0;
		speed = 1500000;
		tick = 0;
	}
	
	public void update() {
		
		if(snake.size()==0)
			snake.add(new SnakePart(x, y, tile));
		
		if(apples.size()==0) {
			int xApple = (int)(Math.random()*(WIDTH/10)+1)-1;
			int yApple = (int)(Math.random()*(HEIGHT/10)+1)-1;
			apples.add(new Food(xApple, yApple, tile));
		}
		
		tick++;
		if(tick > speed) {
			if(right)x++;
			if(left)x--;
			if(up)y--;
			if(down)y++;
			snake.add(new SnakePart(x, y, tile));
			if(snake.size() > size)
				snake.remove(0);
			
			for(int i=0; i<apples.size(); i++) {
				if(x==apples.get(i).getX() && y==apples.get(i).getY()) {
					size++;
					count++;
					speed = speed - 50000;
					apples.remove(i);
					i--;
				}
			}
			
			for(int i=0; i<snake.size(); i++) {
				if(x==snake.get(i).getX() && y==snake.get(i).getY()) {
					if(i != snake.size()-1)
						stop();
				}
			}
			
			if(x<0||y<0||x>(WIDTH/10)-1 ||y>(HEIGHT/10)-1)
				stop();
			
			tick=0;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		for(int i=0; i<WIDTH/10; i++)
			g.drawLine(i*10, 0, i*10, HEIGHT);
		for(int i=0; i<HEIGHT/10; i++)
			g.drawLine(0, i*10, WIDTH, i*10);
		for(int i=0; i<snake.size(); i++)
			snake.get(i).paint(g);
		for(int i=0; i<apples.size(); i++)
			apples.get(i).paint(g);
				
	}
	
	public void run() {
		while(running) {
			update();
			repaint();
		}
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		JOptionPane.showMessageDialog(GamePanel.this, "Score: "+count, "GAME OVER!!!", JOptionPane.INFORMATION_MESSAGE);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class Key implements KeyListener{
		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			if(k==KeyEvent.VK_RIGHT && !left) {
				up = false;
				down = false;
				right = true;
			}
			if(k==KeyEvent.VK_LEFT && !right) {
				up = false;
				down = false;
				left = true;
			}
			if(k==KeyEvent.VK_UP && !down) {
				right = false;
				left = false;
				up = true;
			}
			if(k==KeyEvent.VK_DOWN && !up) {
				right = false;
				left = false;
				down = true;
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}
