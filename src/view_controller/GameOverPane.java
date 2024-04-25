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
	
	private String name;
	private int score;

	public GameOverPane(SpaceInvadersGUI gui) {
		this.score = gui.getCurrentScore();
		createNodes();
		styleMenu();
		layoutMenu();
		setHandler(gui);
	
	}

	/** 
	 * Creates the javafx nodes the gameOver pane.
	 * 
	 */
	private void createNodes() {
		gameOverLabel = new Label("GAME\nOVER");
		playAgainButton = new Button("PLAY AGAIN?");
		nameLabel = new Label("Enter your name to save your score: ");
		nameField = new TextField();
		saveButton = new Button("Save score");
		scoreLabel = new Label(""+ score);

	}

	/**
	 * Sets up the GUI for the menu that displays after a game ends.
	 * 
	 */
	private void layoutMenu() {

		this.setHgap(1);

		this.add(gameOverLabel, 220, 100, 600, 100);
		this.add(playAgainButton, 250, 300, 300, 100);

	}

	/** 
	 * Sets the styling of the menu pane.
	 * 
	 */
	private void styleMenu() {
		this.setStyle("-fx-background-color: Black");

		Font titleFont = Font.font("Monospaced", FontWeight.BOLD, 150);
		Font headingFont = Font.font("Monospaced", FontWeight.BOLD, 30);

		gameOverLabel.setFont(titleFont);

		gameOverLabel.setStyle("-fx-text-fill: Chartreuse;");

		playAgainButton.setStyle("-fx-text-fill: Black;" + "-fx-color: Chartreuse;");

		gameOverLabel.setFont(titleFont);
		playAgainButton.setFont(headingFont);

		gameOverLabel.setPrefWidth(600);
		playAgainButton.setPrefWidth(300);
		playAgainButton.setPrefHeight(100);

	}
	
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
			gui.saveScore();
			
		});
		
		
	}
}
