package view_controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import model.AnimateStarter;
import model.Bullet;
import model.Game;
import model.PlayerTank;

/**
 * 
 * Main GUI for Space Invaders. Includes methods for setting up the GUI and its
 * handlers.
 * 
 * @author Claire Lodermeier
 * 
 */

public class SpaceInvadersGUI extends Application {

	private Timeline timeline;

	private BorderPane all;
	private GridPane headPane;
	private Game game;
	private PlayerTank tank;
	private AlienCollection aliens;

	private MenuPane menu;
	private GameOverPane overPane;

	private Label scoreLabel;
	private Label score;
	private Label livesLabel;

	private ImageView life1;
	private ImageView life2;
	private ImageView life3;

	private GraphicsContext gc;
	private Canvas canvas;

	private ArrayList<Bullet> bullets;

	private Scene scene;

	private ArrayList<Integer> scores;
	private String fileName = "scores.ser";
	private SoundManager soundM;
	
	private int diffi;
	
	private int indextime = 0;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts up the GUI for Space Invaders when the application is run
	 * 
	 * @param stage the Stage used to run the GUI
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {

		readScores();

		all = new BorderPane();
		headPane = new GridPane();
		game = new Game();
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();
		
		soundM = new SoundManager();

		tank = new PlayerTank(350, 450, 50, 40);
		aliens = new AlienCollection(gc, 800, 300, 600, 11, 8);

		bullets = new ArrayList<Bullet>();

		menu = new MenuPane(this);
		overPane = new GameOverPane(this);
		all.setCenter(menu);

		layoutPane();
		stylePane();
		setupCanvas();

		stage.setTitle("SPACE INVADERS");
		scene = new Scene(all, 800, 600);
		stage.setScene(scene);

		setHandlers(stage);

		stage.show();
	}

	/**
	 * Reads the top scores from serializable file.
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void readScores() throws Exception {

		try {
			FileInputStream rawBytes = new FileInputStream(fileName);
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);

			scores = (ArrayList<Integer>) inFile.readObject();
			inFile.close();
		} catch (IOException io) {
			scores = new ArrayList<Integer>();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			return;
		}

	}

	
	/**
	 * Sets the difficulty.
	 * 
	 * @param diffi	the int for difficulty
	 */
	public void setDiffi(int diffi) {
		this.diffi = diffi;
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
	private AnimateStarter setupCanvas() {

		// basic canvas setup
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, 800, 580);
		gc.fillRect(0, 0, 800, 580);
		gc.setStroke(Color.CHARTREUSE);
		gc.setLineWidth(3);
		gc.strokeLine(0, 500, 800, 500);

		// draw current objects
		tank.draw(gc);
		aliens.draw();
		AnimateStarter as = null;
		for (int i=0;i<bullets.size();i++) {
			Bullet b = bullets.get(i);
			b.draw(gc);
			// int alienType = aliens.doesHit(b.getXPosition1(), b.getXPosition2(), b.getYPosition1()); 
			as = aliens.doesHit(b.getXPosition1(), b.getXPosition2(), b.getYPosition1()); 
			int alienType = as.getAlienType();
			
			
			if (alienType!=null && alienType!=0) {
			// TODO: alien explosion animation
				bullets.remove(b);

				// increment score for hitting alien
				if(alienType==1) {
					game.incrementScore(10);
				}
				else if(alienType==2) {
					game.incrementScore(20);
				}
				else if(alienType==3) {
					game.incrementScore(40);
				}

				// update score on the gui
				score.setText(""+game.getScore());
			}
			// remove bullets once off the screen
			else if(b.getYPosition2() < 0) {
				bullets.remove(b);
			}		
		}
		return as;

	}

	/**
	 * Sets the handlers for user interaction with the GUI.
	 * 
	 * @param stage the stage used by the GUI
	 */
	private void setHandlers(Stage stage) {
		scene.setOnKeyPressed(event -> {
			// shoot a bullet
			if (event.getCode() == KeyCode.SPACE) {
				// create a new bullet object with location starting from tank
				// add the bullet to bullets array
				if(!(bullets.contains(tank.getCurrentBullet()))) {
					soundM.playSound("Shooting");
					bullets.add(tank.shoot());
				}
				setupCanvas();
			// move tank left
			} else if (event.getCode() == KeyCode.LEFT) {
				tank.moveLeft(8);
				setupCanvas();
			// move tank right
			} else if (event.getCode() == KeyCode.RIGHT) {
				tank.moveRight(8);
				setupCanvas();
			}
		});
		
		// save score when closing
		stage.setOnCloseRequest(event -> {
			saveScore();
		});

	}

	/**
	 * Disables the handlers for user interaction with the GUI.
	 * 
	 */
	private void disableHandlers() {
		scene.setOnKeyPressed(event -> {
			return;
		});

	}
	
	/**
	 * Respawns the alien collection, with increased diffiiculty. 
	 * 
	 */
	private void noMoreAliens() {
		if(aliens.isEmpty()) {
			aliens = new AlienCollection(gc, 800, 300, 600, 11, 8);
			diffi += 1;
		}
	}

	/**
	 * Starts a game of Space invaders.
	 *
	 * @param diffi the selected diffiiculty (1 for easy, 2 for medium, 3 for hard)
	 */
	public void startGame() {
		all.setTop(headPane);
		all.setCenter(canvas);
		aliens.fillWithAliens(5);
		soundM.playSound("GameStart");
		// create an arraylist of AnimateStarter
		ArrayList<AnimateStarter> asList = new ArrayList<AnimateStarter>();
		
		timeline = new Timeline(new KeyFrame(Duration.millis(80), event -> {
			this.indextime ++;
			if (game.getGameOver()) {
				endGame();
				return;
			}
			noMoreAliens();
			
			if(indextime%7==0) {
				if ((indextime) % (11 - diffi) == 0) {
					Bullet alienBullet = aliens.shootRandom();
					if(alienBullet!=null) {
						bullets.add(alienBullet);
					}
				
				}
			}
			
			aliens.moveAliens(2 * diffi);
			for (Bullet b : bullets) {
				b.move(30);
			}
			soundM.playBackgroundMusic();
			AnimateStarter as = setupCanvas();
			if (as != null) {
				asList.add(as);
			}
			// call handle for all as in asList
			for (AnimateStarter a : asList) {
				a.handle(gc);
			}
			
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

	/**
	 * Ends a game of Space invaders.
	 *
	 */
	public void endGame() {
		timeline.stop();
		setupCanvas();
		disableHandlers();

		all.setCenter(overPane);
		soundM.stopBackgroundMusic();
		soundM.playSound("Death");

		// save score
		saveScore();

	}
	
	
	
	/**
	 * Saves the score, if one of the top 3. 
	 * 
	 */
	private void saveScore() {
		if (scores.size() == 0) {
			scores.add(0, game.getScore());
		}
		else if (scores.size() == 1 || game.getScore() > scores.get(1)) {
			if (game.getScore() > scores.get(0)) {
				scores.add(0, game.getScore());
			}
			else {
				scores.add(1, game.getScore());

			}
		}
		else if (scores.size() == 2 || game.getScore() > scores.get(2)) {
			if (game.getScore() > scores.get(0)) {
				scores.add(0, game.getScore());
			}
			else if (game.getScore() > scores.get(1)) {
				scores.add(1, game.getScore());
			}
			else {
				scores.add(2, game.getScore());
			}
		}
		
		while(scores.size()>3) {
			scores.remove(scores.size()-1);
			
		}
		

		try {
			FileOutputStream bytesToDisk = new FileOutputStream(fileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);

			outFile.writeObject(scores);
			outFile.close();
		} catch (IOException io) {
			System.out.println("Writing scores failed\n" + io);
		}

	}

	public ArrayList<Integer> getScores() {
		return scores;
	}

}
