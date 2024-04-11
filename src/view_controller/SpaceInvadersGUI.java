package view_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.AlienCollection;
import model.Bullet;
import model.Game;
import model.PlayerTank;

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
	
	private Label line;
	private ImageView tankImgView;
	private ImageView life1;
	private ImageView life2;
	private ImageView life3;
	
	private GraphicsContext gc;
	private Canvas canvas;

	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) {
		all = new BorderPane();
		headPane = new GridPane();
		game = new Game();
		canvas = new Canvas(800, 580);
		gc = canvas.getGraphicsContext2D();
			
		tank = new PlayerTank(450,350);
		//aliens = new AlienCollection();
		
		menu = new MenuPane(this);
		all.setCenter(menu);
		
		layoutPane();
		//addAliens();
		stylePane();
		
		stage.setTitle("SPACE INVADERS");
		Scene scene = new Scene(all, 800, 600);
		stage.setScene(scene);
		
		setHandlers(scene);

		stage.show();
	}
	
	private void layoutPane() {
		headPane.setHgap(1);
		headPane.setVgap(1);
		
		scoreLabel = new Label("SCORE ");
		score = new Label("" + game.getScore());
		
		headPane.add(scoreLabel, 10, 10, 5, 1);
		headPane.add(score, 15, 10, 5, 1);
		
		livesLabel = new Label("LIVES ");
		headPane.add(livesLabel, 250, 10, 5, 1);
		
		//line = new Label("____________________________________________");
		//pane.add(line, 1, 400, 350, 1);
		
		//tankImgView = new ImageView(tank.getImage());
		//tankImgView.setFitWidth(75);
		//tankImgView.setPreserveRatio(true);
		//pane.add(tankImgView, 240, 390, 20, 10);
		
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
	
	private void addAliens() {
		// pass a grid pane to AlienCollection class
		
	}
	
	private void stylePane() {
		headPane.setStyle("-fx-background-color: Black;");
		
		Font font = Font.font("Monospaced", FontWeight.BOLD, 30);
		scoreLabel.setFont(font);
		livesLabel.setFont(font);
		score.setFont(font);
		
		scoreLabel.setStyle("-fx-text-fill: White;");
		livesLabel.setStyle("-fx-text-fill: White;");
		score.setStyle("-fx-text-fill: Chartreuse;");
		
		//line.setStyle("-fx-text-fill: Chartreuse;");
		//line.setFont(font);

	}
	 
	private void setHandlers(Scene scene) { 
		scene.setOnKeyPressed(event -> {
			// shoot a bullet
			if(event.getCode() == KeyCode.SPACE) {
				Bullet bullet = new Bullet(tank.getX()-208, tank.getY()-150, "bullet1.png");
				ImageView iv = new ImageView(bullet.getImage());
				iv.setFitWidth(70);
				iv.setFitHeight(120);
				//pane.add(iv, bullet.getX(), bullet.getY(), 100, 300);
			} 
		});
		
	}
	
	public void startGame(int diff) {
		all.setTop(headPane);
		all.setCenter(canvas);
	}

	


}
