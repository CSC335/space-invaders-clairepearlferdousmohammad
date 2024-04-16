package view_controller;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AlienCollection;
import model.Bullet;
import model.Game;
import model.PlayerTank;

/**
 * 
 * Main GUI for Space Invaders. 
 * Includes methods for setting up the GUI and its handlers. 
 * 
 * @author Claire Lodermeier
 * 
 */

public class SpaceInvadersGUI extends Application{

	private BorderPane all;
	private GridPane headPane;
	private Game game;
	private PlayerTank tank;
	private AlienCollection aliens;
	
	private MenuPane menu;
	
	private Label scoreLabel;
	private Label score; 
	private Label livesLabel;
	private Label gameOverLabel; 
	private Label playAgainLabel;
	
	private ImageView life1;
	private ImageView life2;
	private ImageView life3;
	
	private GraphicsContext gc;
	private Canvas canvas;
	
	private Bullet[] bullets;
	
	public static void main(String[] args) {
		launch();
	}
	
	/**
	 * Starts up the GUI for Space Invaders when the application is run	 
	 * @param stage     the Stage used to run the GUI
	 */
	@Override
	public void start(Stage stage) {
		all = new BorderPane();
		headPane = new GridPane();
		game = new Game();
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();
			
		tank = new PlayerTank(450,350);
		aliens = new AlienCollection(gc, 800, 300, 600, 11, 8);
		
		bullets = new Bullet[0];
		
		menu = new MenuPane(this);
		all.setCenter(menu);
		
		layoutPane();
		stylePane();
		setupCanvas();
		
		stage.setTitle("SPACE INVADERS");
		Scene scene = new Scene(all, 800, 600);
		stage.setScene(scene);
		
		setHandlers(scene);

		stage.show();
	}
	
	/**
	 * Sets up the head pane for the GUI. 
	 * 
	 */
	private void layoutPane() {
		headPane.setHgap(1);
		headPane.setVgap(1);
		
		scoreLabel = new Label("SCORE ");
		score = new Label("" + game.getScore());
		
		headPane.add(scoreLabel, 10, 10, 5, 1);
		headPane.add(score, 15, 10, 5, 1);
		
		livesLabel = new Label("LIVES ");
		headPane.add(livesLabel, 250, 10, 5, 1);
		
		life1 = new ImageView(tank.getImage());
		life1.setFitWidth(40);
		life1.setPreserveRatio(true);
		life2 = new ImageView(tank.getImage());
		life2.setFitWidth(40);
		life2.setPreserveRatio(true);
		life3 = new ImageView(tank.getImage());
		life3.setFitWidth(40);
		life3.setPreserveRatio(true);
		headPane.add(life1, 260, 10, 5, 1);
		headPane.add(life2, 280, 10, 5, 1);
		headPane.add(life3, 300, 10, 5, 1);
	
		gameOverLabel = new Label("GAME OVER");
		playAgainLabel = new Label("PLAY AGAIN?");
		

	}
	
	/**
	 * Sets styling for the head pane of the GUI. 
	 */
	private void stylePane() {
		headPane.setStyle("-fx-background-color: Black;");
		
		Font font = Font.font("Monospaced", FontWeight.BOLD, 30);
		scoreLabel.setFont(font);
		livesLabel.setFont(font);
		score.setFont(font);
		
		scoreLabel.setStyle("-fx-text-fill: White;");
		livesLabel.setStyle("-fx-text-fill: White;");
		score.setStyle("-fx-text-fill: Chartreuse;");

	}
	
	/**
	 * Clears the canvas, and draws all the current Objects of the game. 
	 *
	 */
	private void setupCanvas() {
		
		// basic canvas setup
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, 800, 580);
		gc.fillRect(0, 0, 800, 580);
		gc.setStroke(Color.CHARTREUSE);
		gc.setLineWidth(3);
		gc.strokeLine(0, 500, 800, 500);
		
		
		// draw current objects
		//tank.draw(gc);
		//aliens.draw(gc);
		for(Bullet b: bullets) {
			//b.draw(gc);
		}

	}
	

	/**
	 * Sets the handlers for user interaction with the GUI. 
	 * 
	 * @param scene     the scene used by the GUI
	 */
	private void setHandlers(Scene scene) { 
		scene.setOnKeyPressed(event -> {
			// shoot a bullet
			if(event.getCode() == KeyCode.SPACE) {
				// TODO: create a new bullet object with location starting from tank
				// add the bullet to bullets array
			} 
		});
		
	}
	
	/**
	 * Starts a game of Space invaders.
	 *
	 * @param diff     the selected difficulty (1 for easy, 2 for medium, 3 for hard) 
	 */
	 
	public void startGame(int diff) {
		all.setTop(headPane);
		all.setCenter(canvas);
		aliens.fillWithAliens(5);
	    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
	        aliens.moveAliens(2);
	    }));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		
		
	}
	


}
