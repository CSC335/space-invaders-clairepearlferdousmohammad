package view_controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 
 * Game over GUI pane for Space Invaders. Includes methods for layout and
 * setting handlers.
 * 
 * @author Claire Lodermeier
 * 
 */

public class GameOverPane extends GridPane {

	private Label gameOverLabel, nameLabel, scoreLabel;
	private Button playAgainButton, saveButton;
	private TextField nameField;

	private SpaceInvadersGUI gui;

	private String name;

	public GameOverPane(SpaceInvadersGUI gui) {

		this.gui = gui;
		createNodes();
		styleMenu();
		layoutMenu();
		setHandler(gui);

	}

	/**
	 * Determines if the current players score is the all-time high score in the
	 * game
	 * 
	 * @return a boolean for whether the current score is the highest score
	 */
	private boolean highScore() {
		int score = gui.getCurrentScore();
		System.out.println("current score is " + score);
		try {
			System.out.println("cutoff is " + gui.getScores().get(2));

			return score > gui.getScores().get(2);
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Creates the javafx nodes the gameOver pane.
	 * 
	 */
	private void createNodes() {
		gameOverLabel = new Label("GAME OVER");
		playAgainButton = new Button("PLAY AGAIN?");
		nameLabel = new Label("Enter your name to \n save your score: ");
		nameField = new TextField();
		saveButton = new Button("Save score");
		scoreLabel = new Label("" + gui.getCurrentScore());

	}

	/**
	 * Sets up the GUI for the menu that displays after a game ends.
	 * 
	 */
	private void layoutMenu() {

		this.setHgap(1);
		this.setVgap(0.5);

		this.add(gameOverLabel, 100, 100, 800, 50);

		if (highScore()) {
			this.add(nameLabel, 50, 300, 300, 5);
			this.add(nameField, 300, 300, 200, 1);
			this.add(scoreLabel, 550, 300, 30, 1);
			this.add(saveButton, 300, 350, 200, 15);
		}

		this.add(playAgainButton, 220, 500, 300, 100);

	}

	/**
	 * Sets the styling of the menu pane.
	 * 
	 */
	private void styleMenu() {
		this.setStyle("-fx-background-color: Black");

		Font titleFont = Font.font("Monospaced", FontWeight.BOLD, 110);
		Font headingFont = Font.font("Monospaced", FontWeight.BOLD, 30);

		Font infoFont = Font.font("Monospaced", FontWeight.BOLD, 20);

		gameOverLabel.setFont(titleFont);

		gameOverLabel.setStyle("-fx-text-fill: Chartreuse;");

		playAgainButton.setStyle("-fx-text-fill: Black;" + "-fx-color: Chartreuse;");

		nameLabel.setStyle("-fx-text-fill: White;");
		scoreLabel.setStyle("-fx-text-fill: White;");

		nameLabel.setFont(infoFont);
		scoreLabel.setFont(infoFont);
		nameField.setFont(infoFont);
		saveButton.setFont(infoFont);

		gameOverLabel.setFont(titleFont);
		playAgainButton.setFont(headingFont);

		gameOverLabel.setPrefWidth(600);
		playAgainButton.setPrefWidth(300);
		playAgainButton.setPrefHeight(100);

	}

	/**
	 * Gets the name of the user for the scoreboard
	 * 
	 * @return this.name, the name given by the user in the name field
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets handler for when the user clicks play again.
	 * 
	 * @param gui The GUI used to display the game.
	 * 
	 */
	private void setHandler(SpaceInvadersGUI gui) {
		playAgainButton.setOnAction(event -> {
			try {
				gui.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		saveButton.setOnAction(event -> {
			this.name = nameField.getText();
			gui.saveScore(name);
			this.scoreLabel.setVisible(false);
			this.nameLabel.setVisible(false);
			this.saveButton.setVisible(false);
			this.nameField.setVisible(false);
		});

	}
}
