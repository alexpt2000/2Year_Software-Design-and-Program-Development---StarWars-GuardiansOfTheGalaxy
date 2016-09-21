package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Sound.SoundMissile;

public class Missile {

	private Image imageMissile;
	private int x, y;
	private boolean isVisible;
	private int height; // Altura para os Missile
	private int width; // Largura para os Missile

	// Set the screen size for the missile does not extend the size
	private static final int sizeWindows = 600;

	// Speed that the missile will go
	private static final int speedMissile = 2;
	


	// Possicoes do missel
	public Missile(int x, int y) {
		this.x = x;
		this.y = y;

		// Missile Positions
		ImageIcon reference = new ImageIcon("res\\missile1.png");
		imageMissile = reference.getImage();
		
		// Check the size of the missile and return values
		this.width = imageMissile.getWidth(null);
		this.height = imageMissile.getHeight(null);

		isVisible = true;
		// Add sound
		SoundMissile soundLaser = new SoundMissile();
		soundLaser.main(null);

	}

	// set when the missile moves on the screen
	public void move() {
		this.y -= speedMissile;

		if (this.y > sizeWindows) {
			isVisible = false;
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

	public Image getImageMissile() {
		return imageMissile;
	}

}
