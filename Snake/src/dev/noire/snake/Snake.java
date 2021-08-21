package dev.noire.snake;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Snake extends JFrame {

	public static void main(String[] args) {
		new Snake();
	}
	
	public Snake() {
		this.setTitle("Snake Demo");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 1, 0, 0));
		this.add(new GamePanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
