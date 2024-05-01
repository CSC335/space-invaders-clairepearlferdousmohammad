package view_controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.image.Image;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
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
import model.TankDestroy;
import model.scoreBooster;
import model.fastBullet;

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

	private ImageView life1, life2, life3, booster, faster;
	private ImageView nextLife;

	private GraphicsContext gc;
	private Canvas canvas;

	private ArrayList<Bullet> alienBullets;
	private ArrayList<Bullet> tankBullets;

	private Scene scene;

	private ArrayList<Integer> scores;
	private ArrayList<String> names;
		
	private String scoreFileName = "scores.ser";
	private String nameFileName = "names.ser";

	
	private SoundManager soundM;
	
	private int diffi;
	
	private int indextime = 0;
	
	private AnimateStarter as;
	private TankDestroy td;
	
	private PlayerTank extraLife;

	private scoreBooster sb;

	private int extraScore = 0;
	private Boolean boost = false;

	private fastBullet fb;

	private int fasterBullet = 0;
	private Boolean fast = false;

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
		readNames();

		all = new BorderPane();
		headPane = new GridPane();
		game = new Game();
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();
		
		soundM = new SoundManager();

		tank = new PlayerTank(350, 450, 50, 40);
		aliens = new AlienCollection(gc, 800, 300, 600, 11, 8);

		sb = new scoreBooster(tank.getY2() - 40);
		fb = new fastBullet(tank.getY2() - 40);

		tankBullets = new ArrayList<Bullet>();
		alienBullets = new ArrayList<Bullet>();

		menu = new MenuPane(this);
		all.setCenter(menu);
		
		layoutPane();
		stylePane();
		setupCanvas();
		
		nextLife = life3;

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
			FileInputStream rawBytes = new FileInputStream(scoreFileName);
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
	 * Reads the serialized names from the serializable 
	 * file to add them to the scoreboard
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	private void readNames() throws Exception {

		try {
			FileInputStream rawBytes = new FileInputStream(nameFileName);
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			names = (ArrayList<String>) inFile.readObject();
			inFile.close();
		} catch (IOException io) {
			names = new ArrayList<String>(scores.size());
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

		Image boosterImage = new Image(getClass().getResourceAsStream("booster.png"));
		booster = new ImageView(boosterImage);
		booster.setFitWidth(40);
		booster.setPreserveRatio(true);
		booster.setVisible(false);

		Image fasterImage = new Image(getClass().getResourceAsStream("fast.png"));
		faster = new ImageView(fasterImage);
		faster.setFitWidth(40);
		faster.setPreserveRatio(true);
		faster.setVisible(false);
		
		headPane.add(life1, 260, 10, 5, 1);
		headPane.add(life2, 280, 10, 5, 1);
		headPane.add(life3, 300, 10, 5, 1);

		headPane.add(booster, 320, 10, 5, 1);
		headPane.add(faster, 340, 10, 5, 1);

	}

	/**
	 * With the score booster, increases the score at a 
	 * faster rate than otherwise. 
	 * 
	 * @return the score boost, whether or not it's happening
	 */
	private int scoreBoost() {
		extraScore++;

		if (extraScore > 5) {
			boost = false;
			booster.setVisible(false);
			extraScore = 0;
		}

		if (boost) {
			return 5;
		} else {
			return 0;
		}	
	}

	/**
	 * With the bullet speed booster, increases the speed at a 
	 * faster rate than otherwise. 
	 * 
	 * @return the bullet speed boost, whether or not it's happening
	 */
	private float fastBoost() {
		fasterBullet++;

		if (fasterBullet > 100) {
			fast = false;
			faster.setVisible(false);
			fasterBullet = 0;
		}

		if (fast) {
			return (float) 1.3;
		} else {
			return 1;
		}	
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
		tank.draw(gc);
		aliens.draw();

		sb.draw(gc);
		fb.draw(gc);

		if (sb.isActivated) {
			if (sb.doesHit(tank)) {
				boost = true;
				booster.setVisible(true);
			}
		}

		if (fb.isActivated) {
			if (fb.doesHit(tank)) {
				fast = true;
				faster.setVisible(true);
			}
		}
				

		if(extraLife!=null) {
			if(!(extraLife.isRespawning())) {
				extraLife = null;
			}
			else {
				extraLife.draw(gc);
				// if tank is to the right
				if(tank.getX1() > extraLife.getX1()) {
					if(tank.getX1()<extraLife.getX2()) {
						extraLife = null;
						game.incrementLives();
						
						// update life display
						if(nextLife==life2) {
							life3.setVisible(true);
							nextLife = life3;  
						}
						else if(nextLife==life1) {
							life2.setVisible(true);
							nextLife = life2;
						}
						
					}
				}
			}
		}
		as = null;
		td = null;
		for (int i=0;i<tankBullets.size();i++) {
			Bullet b = tankBullets.get(i);
			b.draw(gc);
			// int alienType = aliens.doesHit(b.getXPosition1(), b.getXPosition2(), b.getYPosition1()); 
			as = aliens.doesHit(b.getXPosition1(), b.getXPosition2(), b.getYPosition1()); 
			int alienType = 0;
			if (as != null) {
				alienType = as.getAlienType();
			}
			
			if (alienType!=0) {
				tankBullets.remove(b);

				// increment score for hitting alien
				if(alienType==1) {
					game.incrementScore(10 + scoreBoost());
				}
				else if(alienType==2) {
					game.incrementScore(20 + scoreBoost());
				}
				else if(alienType==3) {
					game.incrementScore(40 + scoreBoost());
				}

				// update score on the gui
				score.setText(""+game.getScore());
			}
			// remove bullets once off the screen
			else if(b.getYPosition2() < 0) {
				tankBullets.remove(b);
			}		
		}
		
		for (int i=0;i<alienBullets.size();i++) {
			Bullet b = alienBullets.get(i);
			b.draw(gc);
			// int alienType = aliens.doesHit(b.getXPosition1(), b.getXPosition2(), b.getYPosition1()); 
			td = tank.doesHit(b.getXPosition1(), b.getXPosition2(), b.getYPosition2());
			if(td!=null) {
				game.decrementLives();
					hitTank(b);
			}
			// remove bullets once off the screen
			else if(b.getYPosition1() > 580) {
				alienBullets.remove(b);
			}		
		}
	}
	
	/**
	 * Behavior for when the tank is hit with an alien bullet. 	 
	 * 
	 * @param b the Bullet that hit the tank
	 * 
	 */
	private void hitTank(Bullet b) {
		nextLife.setVisible(false);
		if(nextLife==life3) {
			nextLife=life2;
		}
		else {
			nextLife=life1;
		}
		alienBullets.remove(b);
		tank.respawn(20);
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
				if(!(tankBullets.contains(tank.getCurrentBullet()))) {
					soundM.playSound("Shooting");
					tankBullets.add(tank.shoot());
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
			if(all.getCenter()==canvas) {
				timeline.stop();
				if(scores.size()<3 || getCurrentScore() > scores.get(2)) {
					Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to save your score?");
					
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						overPane = new GameOverPane(this);
						all.setCenter(overPane);
						event.consume();
					}
					
				}
			}
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
			// aliens = new AlienCollection(gc, 800, 300, 600, 11, 8);
			aliens.fillWithAliens(5);
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
		ArrayList<TankDestroy> tdList = new ArrayList<TankDestroy>();

		timeline = new Timeline(new KeyFrame(Duration.millis(80), event -> {
			this.indextime ++;
			if (game.getGameOver()) {
				endGame();
				return;
			}
			noMoreAliens();
			
			if(indextime%7==0) {
				if ((indextime) % (7 - diffi) == 0) {
					Bullet alienBullet = aliens.shootRandom();
					if(alienBullet!=null) {
						alienBullets.add(alienBullet);
					}
				
				}
			}
			
			// spawn lives
			if(indextime % 250 == 49 && game.getNumLives()<3) {
				spawnLife();
			}

			if(indextime % 200 == 60) {
				sb.activate();
			}

			if (indextime % 200 == 130) {
				fb.activate();
			}
			
			aliens.moveAliens(1 + diffi/2);

			if(hitBottom()) {
				endGame();
			}
			
			for (Bullet b : alienBullets) {
				b.move(30 * fastBoost());
			}
			for (Bullet b : tankBullets) {
				b.move(30);
			}
			soundM.playBackgroundMusic();
			setupCanvas();
			if (as != null) {
				asList.add(as);
			}
			if(td!=null) {
				tdList.add(td);
			}
			// call handle for all as in asList
			for (AnimateStarter a : asList) {
				a.handle(gc);
			}
			for (TankDestroy t : tdList) {
				t.handle(gc);
			}
			
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	
	/**
	 * spawns an extra life to be picked up by the playerTank every 50 milliseconds
	 */
	private void spawnLife() {
		extraLife = new PlayerTank((float) (Math.random()*530), tank.getY1() + 30, (tank.getX2()-tank.getX1())/2, (tank.getY2()-tank.getY1())/2);
		extraLife.respawn(50);
	}
	
	/**
	 * Determines whether the aliens hit the bottom of the screen.
	 *
	 */
	public boolean hitBottom() {
		return (aliens.getLowestY() >= 500);
	
	}

	/**
	 * Ends a game of Space invaders.
	 *
	 */
	public void endGame() {
		timeline.stop();
		setupCanvas();
		disableHandlers();

		overPane = new GameOverPane(this);
		all.setCenter(overPane);
		soundM.stopBackgroundMusic();
		soundM.playSound("Death");

	}
	
	
	
	/**
	 * Saves the score, if one of the top 3. 
	 * 
	 */
	public void saveScore(String name) {
		if (scores.size() == 0) {
			scores.add(0, game.getScore());
			names.add(0, name);
		}
		else if (scores.size() == 1 || game.getScore() > scores.get(1)) {
			if (game.getScore() > scores.get(0)) {
				scores.add(0, game.getScore());
				names.add(0, name);

			}
			else {
				scores.add(1, game.getScore());
				names.add(1, name);


			}
		}
		else if (scores.size() == 2 || game.getScore() > scores.get(2)) {
			if (game.getScore() > scores.get(0)) {
				scores.add(0, game.getScore());
				names.add(0, name);

			}
			else if (game.getScore() > scores.get(1)) {
				scores.add(1, game.getScore());
				names.add(1, name);

			}
			else {
				scores.add(2, game.getScore());
				names.add(2, name);

			}
		}
		
		while(scores.size()>3) {
			scores.remove(scores.size()-1);
			names.remove(scores.size()-1);
			
		}
		

		try {
			FileOutputStream bytesToDisk = new FileOutputStream(scoreFileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(scores);
			outFile.close();
		} catch (IOException io) {
			System.out.println("Writing scores failed\n" + io);
		}
		

		try {
			FileOutputStream bytesToDisk = new FileOutputStream(nameFileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(names);
			outFile.close();
		} catch (IOException io) {
			System.out.println("Writing names failed\n" + io);
		}

	}

	/**
	 * 
	 * @return ArrayList of integers that returns the serialized scores
	 */
	public ArrayList<Integer> getScores() {
		return scores;
	}
	
	/**
	 * 
	 * @return ArrayList of integers that returns the serialized names
	 */
	public ArrayList<String> getNames(){
		return names;
	}
	
	/**
	 * Gets the current players score in the game
	 * 
	 * @return int, the current players score in the game
	 */
	public int getCurrentScore() {
		return game.getScore();
	}

}
