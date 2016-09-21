package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Sound.Sound;
import Sound.SoundCollisions;
import Sound.SoundCollisions2;
import Sound.SoundEndGame;
import Sound.SoundMissile;

// implements ActionListener to pass key pressed
public class Stage extends JPanel implements ActionListener {

	private Image backgound;
	private Spaceship spaceship;
	private Timer timer, backgroundSpeed;
	private Missile missile;
	private boolean inGame, firstStart;
	private List<Enemy> enemy;
	private int backgroundMove;
	public Sound sound;
	// private SoundMissile soundMissile;

	Random rand = new Random();
	Sound musicBackground = new Sound();

	private int[][] coordinatesEnemy = { // coordinates X and Y
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 },
			{ rand.nextInt((540 - 60) + 1) + 60, rand.nextInt((0 - -600) + 1) + -600 }, };

	// Constructor
	public Stage() {

		// Define the windows be in focus
		setFocusable(true);

		// avoid lines across the windows
		setDoubleBuffered(true);

		addKeyListener(new keyboard());

		ImageIcon reference = new ImageIcon("res\\background.jpg");
		backgound = reference.getImage();

		spaceship = new Spaceship();

		inGame = false;
		firstStart = true;

		startEnemy(); // Start Enemy

		backgroundSpeed = new Timer(18, new BackgroundSpeed());
		backgroundSpeed.start();

		timer = new Timer(5, this);
		timer.start();

		// Play Music Background
		musicBackground = new Sound();
		musicBackground.main(null);
		musicBackground.setLoop(true);
	}

	public void startEnemy() {
		enemy = new ArrayList<Enemy>();

		// Adds the new enemy object
		for (int i = 0; i < coordinatesEnemy.length; i++) {
			enemy.add(new Enemy(coordinatesEnemy[i][0], coordinatesEnemy[i][1]));
		}
	}

	// Draw image into my windows
	public void paint(Graphics g) {
		Graphics2D graphic = (Graphics2D) g;
		graphic.drawImage(backgound, 0, backgroundMove, null);
		graphic.drawImage(backgound, 0, backgroundMove - 601, null);

		if (inGame) {
			graphic.drawImage(spaceship.getImageSpaceship(), spaceship.getX(), spaceship.getY(), this);

			List<Missile> missile = spaceship.getMissile();

			// Paint the Missile Windows
			for (int i = 0; i < missile.size(); i++) {
				Missile m = (Missile) missile.get(i);
				graphic.drawImage(m.getImageMissile(), m.getX(), m.getY(), this);
			}

			// Paint the Enemy Windows
			for (int i = 0; i < enemy.size(); i++) {

				Enemy en = (Enemy) enemy.get(i);
				graphic.drawImage(en.getImageEnemy(), en.getX(), en.getY(), this);
			}

			// Print the total Enemy
			graphic.setColor(Color.white);
			graphic.drawString("Enemy: " + enemy.size(), 5, 15);
		}

		else if (firstStart) {
			ImageIcon firstGame = new ImageIcon("res\\game_start.jpg");
			graphic.drawImage(firstGame.getImage(), 0, 0, null);
		}

		else {
			ImageIcon endGame = new ImageIcon("res\\game_over1.jpg");
			graphic.drawImage(endGame.getImage(), 0, 0, null);
		}

		// clean the screew after new spaceship position
		g.dispose();
		this.repaint();

	}

	// Implement Methods "Shortcut ALT+S+V"
	// This methods controls the movement spaceship screw
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// Verify if all Enemy was kill
		if (enemy.size() == 0) {
			inGame = false;
			firstStart = true;
		}

		// Method to move the Missiles
		List<Missile> missile = spaceship.getMissile();

		for (int i = 0; i < missile.size(); i++) {

			Missile m = (Missile) missile.get(i);

			if (m.isVisible()) {
				m.move();
			} else {
				missile.remove(i);
			}
		}

		// Method to move the Enemy
		for (int i = 0; i < enemy.size(); i++) {

			Enemy em = (Enemy) enemy.get(i);

			if (em.isVisible()) {
				em.move();
			} else {
				enemy.remove(i);
			}
		}
		spaceship.move();
		checkCollisions(); // Verify if exist Collisions
	}

	public void checkCollisions() {

		Rectangle shapeSpaceship = spaceship.getBounds();
		Rectangle shapeEnemy;
		Rectangle shapeMissile;

		for (int i = 0; i < enemy.size(); i++) {

			Enemy tempEnemy = enemy.get(i);
			shapeEnemy = tempEnemy.getBounds();

			if (shapeSpaceship.intersects(shapeEnemy)) {

				spaceship.setVisible(false);
				tempEnemy.setVisible(false);
				inGame = false;
				firstStart = false;

				// Add sound
				SoundCollisions2 soundCollisions2 = new SoundCollisions2();
				soundCollisions2.main(null);
			}
		}

		List<Missile> missiles = spaceship.getMissile();

		for (int i = 0; i < missiles.size(); i++) {

			Missile tempMissile = missiles.get(i);
			shapeMissile = tempMissile.getBounds();

			for (int j = 0; j < enemy.size(); j++) {

				Enemy tempEnemy = enemy.get(j);
				shapeEnemy = tempEnemy.getBounds();

				if (shapeMissile.intersects(shapeEnemy)) {

					tempEnemy.setVisible(false);
					tempMissile.setVisible(false);

					// Add sound
					SoundCollisions soundCollisions = new SoundCollisions();
					soundCollisions.main(null);
				}

			}
		}

	}

	// Return value with key was pressed and pass to class Spaceship to method
	// keyPressed and keyReleased
	private class keyboard extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Add sound
				SoundEndGame soundEndGame = new SoundEndGame();
				SoundEndGame.main(null);

				timer.start();
				inGame = true;
				firstStart = false;
				spaceship = new Spaceship();
				startEnemy();
			}

			spaceship.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			spaceship.keyReleased(e);
		}
	}

	// Define Background Movements
	private class BackgroundSpeed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (backgroundMove < 600) {
				backgroundMove++;
			} else {
				backgroundMove = 0;
			}
		}
	}
}
