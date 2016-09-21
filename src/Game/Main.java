  package Game;

import javax.swing.JFrame;

public class Main extends JFrame {

	// Constructor Creating Windows
	public Main() {

		// start the game
		add(new Stage());

		// Creating e define windows
		setTitle("Guardians Of The Galaxy - Alexander Souza - G00317835");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {

		// Instantiating Windows
		new Main();

	}

}
