package view_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Game;
import model.PlayerTank;

public class SpaceInvadersGUI extends Application{

	private GridPane pane;
	private Game game;
	private PlayerTank tank;
	
	private Label scoreLabel;
	private Label score; 
	private Label livesLabel;
	private Label gameOverLabel; 
	private Label playAgainLabel;
	
	private Label line;
	private ImageView tankImgView;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) {
		pane = new GridPane();
		game = new Game();
		tank = new PlayerTank(450,350);

		layoutPane();
		stylePane();
		
		stage.setTitle("SPACE INVADERS");
		Scene scene = new Scene(pane, 800, 600);
		stage.setScene(scene);

		stage.show();
	}
	
	private void layoutPane() {
		pane.setHgap(1);
		pane.setVgap(1);
		
		scoreLabel = new Label("SCORE ");
		score = new Label("" + game.getScore());
		
		pane.add(scoreLabel, 10, 10, 5, 1);
		pane.add(score, 15, 10, 5, 1);
		
		livesLabel = new Label("LIVES ");
		pane.add(livesLabel, 250, 10, 5, 1);
		
		line = new Label("____________________________________________");
		pane.add(line, 1, 400, 300, 1);
		
		tankImgView = new ImageView(tank.getImage());
		tankImgView.setFitWidth(75);
		tankImgView.setPreserveRatio(true);
		pane.add(tankImgView, 240, 390, 20, 10);
		
		gameOverLabel = new Label("GAME OVER");
		playAgainLabel = new Label("PLAY AGAIN?");
		

	}
	
	private void stylePane() {
		pane.setStyle("-fx-background-color: Black;");
		
		Font font = Font.font("Monospaced", FontWeight.BOLD, 30);
		scoreLabel.setFont(font);
		livesLabel.setFont(font);
		score.setFont(font);
		
		scoreLabel.setStyle("-fx-text-fill: White;");
		livesLabel.setStyle("-fx-text-fill: White;");
		score.setStyle("-fx-text-fill: Chartreuse;");
		
		line.setStyle("-fx-text-fill: Chartreuse;");
		line.setFont(font);

	}


}
