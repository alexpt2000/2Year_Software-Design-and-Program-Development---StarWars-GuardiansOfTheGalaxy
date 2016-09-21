package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * 
 * @author Alex
 *
 */

public class Spaceship {

	private int x, y;
	private int dx, dy;
	private int height; // Altura para os Spaceship
	private int width; // Largura para os Spaceship
	private Image imageSpaceship;
	private List<Missile> missile; // Lista para Missil
	private boolean isVisible;

	// Constructor
	public Spaceship() {
		ImageIcon reference = new ImageIcon("res\\spaceship8.png");
		imageSpaceship = reference.getImage();

		// Assign the height of the spaceship
		height = imageSpaceship.getHeight(null);
		width = imageSpaceship.getWidth(null);

		// Instantiate the list of Missile
		missile = new ArrayList<Missile>();

		// Spaceship position
		this.x = 330;
		this.y = 500;
	}

	// Methods define position for spaceship
	public void move() {
		x += dx; // 1 to 560
		y += dy; // 1 to 540

		if (this.x < 1) {
			x = 1;
		}

		if (this.x > 510) {
			x = 510;
		}

		if (this.y < 1) {
			y = 1;
		}

		if (this.y > 480) {
			y = 480;
		}
	}

	// Gets and Sets
	public List<Missile> getMissile() {
		return missile;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImageSpaceship() {
		return imageSpaceship;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	// creating a new object each time and fired
	// one in the missile and centralizing the missile in proportion to the size
	// of the ship
	public void shootMissile() {
		this.missile.add(new Missile(x + 14, y -30));
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	// "Y" or "DY" is equal UP and Down
	// "X" or "DX2 is equal Left and Right

	// Went press the buttons, spaceship moves
	public void keyPressed(KeyEvent key) {
		int codeKey = key.getKeyCode();

		// Adding a new event for when fire a new missile
		if (codeKey == KeyEvent.VK_SPACE) {
			shootMissile();
		}

		if (codeKey == KeyEvent.VK_UP) {
			dy = -1;
		}

		if (codeKey == KeyEvent.VK_DOWN) {
			dy = 1;
		}

		if (codeKey == KeyEvent.VK_LEFT) {
			dx = -1;
		}

		if (codeKey == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
	}

	// went released the button the spaceship stop moves
	public void keyReleased(KeyEvent key) {
		int codeKey = key.getKeyCode();

		if (codeKey == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codeKey == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codeKey == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (codeKey == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

}
