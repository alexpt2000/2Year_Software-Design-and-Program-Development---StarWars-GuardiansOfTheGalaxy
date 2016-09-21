package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Enemy {

	private Image imageEnemy;
	private int x, y;
	private int height; // Altura para os Enemy
	private int width; // Largura para os Enemy
	private boolean isVisible;
	private static int count = 0;

	// Set the screen size for the missile does not extend the size
	private static final int sizeWindows = 600;  
												 
	// Speed that the missile will go
	private static final int speedMissile = 1; 

	// Enemy positions
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		
		ImageIcon reference;
		
		//Creates alternately enemies
		if(count++ % 3 == 0){
		
			// set the image for enemies
			reference = new ImageIcon("res\\enemy_4.png");

			
		} else {
			
			// set the image for enemies
			reference = new ImageIcon("res\\enemy_3.png");
			
		}
		
		imageEnemy = reference.getImage();
		
		// set the size of the enemy
		this.width = imageEnemy.getWidth(null);
		this.height = imageEnemy.getHeight(null);
		
		isVisible = true;
		
	}
// **************************************************************
	// set when the enemy moves on the screen
	public void move() {
		
		if(this.y > 600){
			this.y = -100;
		} else {
			this.y += speedMissile;
		}
	}

	// Alt+S+R create Gets e Sets
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Limit size enemy
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Image getImageEnemy() {
		return imageEnemy;
	}

}
