package view_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuPane extends GridPane{

	private Label titleLabel, howLabel, howToLabel, diffLabel, scoreboardLabel;
	private Label score1Label, score2Label, score3Label;
	private ObservableList<RadioButton> radios;
	private ToggleGroup tg;
	private RadioButton easyButton, medButton, hardButton;
	private Button startButton;
	
	//public MenuPane(int[] scores) {};
	public MenuPane(SpaceInvadersGUI gui) {
		createNodes();
		styleMenu();
		layoutMenu();
		setHandler(gui);
	}
	
	private void createNodes() {
		titleLabel = new Label("SPACE INVADERS");
		howLabel = new Label("\tHow to play:");
		howToLabel = new Label("Destroy all the aliens before it is too late!\n"
				+ "Use <- and -> to move, SPACE to shoot");
		diffLabel = new Label("CHOOSE YOUR DIFFICULTY: ");
		scoreboardLabel = new Label("SCOREBOARD");
		
		easyButton = new RadioButton("easy");
		medButton = new RadioButton("medium");
		hardButton = new RadioButton("hard");

		radios = FXCollections.observableArrayList();
		radios.add(easyButton);
		radios.add(medButton);
		radios.add(hardButton);

		tg = new ToggleGroup();
		tg.getToggles().addAll(radios);
		score1Label = new Label("0");
		score2Label = new Label("0");
		score3Label = new Label("0");
		
		startButton = new Button("START");
		
	}
	
	private void layoutMenu(){
		this.setHgap(10);
		this.setVgap(1);
		
		this.add(titleLabel, 10, 2, 30, 2);
		this.add(howLabel, 12, 10, 10, 1);
		this.add(howToLabel, 11, 15, 30, 3);
		
		this.add(diffLabel, 5, 70, 25, 2);
		this.add(scoreboardLabel, 25, 70, 15, 2);
		
		this.add(easyButton, 5, 75,10, 1);
		this.add(medButton, 5, 80, 10, 1);
		this.add(hardButton, 5, 85, 10, 1);
		
		this.add(score1Label, 25, 75, 5, 1);
		this.add(score2Label, 25, 80, 5, 1);
		this.add(score3Label, 25, 85, 5, 1);

		this.add(startButton, 21, 180, 20, 10);
	
	}
	
	private void styleMenu() {
		this.setStyle("-fx-background-color: Black");
		
		Font titleFont = Font.font("Monospaced", FontWeight.BOLD, 70);
		Font headingFont = Font.font("Monospaced", FontWeight.BOLD, 30);
		Font infoFont = Font.font("Monospaced", FontWeight.BOLD, 20);

		titleLabel.setFont(titleFont);
		howLabel.setFont(headingFont);
		diffLabel.setFont(headingFont);
		scoreboardLabel.setFont(headingFont);
		howToLabel.setFont(infoFont);
		easyButton.setFont(infoFont);
		medButton.setFont(infoFont);
		hardButton.setFont(infoFont);
		score1Label.setFont(infoFont);
		score2Label.setFont(infoFont);
		score3Label.setFont(infoFont);
		
		titleLabel.setStyle("-fx-text-fill: Chartreuse;");
		howLabel.setStyle("-fx-text-fill: White;");
		diffLabel.setStyle("-fx-text-fill: White;");
		scoreboardLabel.setStyle("-fx-text-fill: White;");
		easyButton.setStyle("-fx-text-fill: White;"
				+ "-fx-color: Gray;");
		medButton.setStyle("-fx-text-fill: White;"
				+ "-fx-color: Gray;");
		hardButton.setStyle("-fx-text-fill: White;"
				+ "-fx-color: Gray;");

		howToLabel.setStyle("-fx-text-fill: White;");
		score1Label.setStyle("-fx-text-fill: White;");
		score2Label.setStyle("-fx-text-fill: White;");
		score3Label.setStyle("-fx-text-fill: White;");
		
		startButton.setFont(headingFont);
		startButton.setStyle("-fx-background-color: Chartreuse;"
				+ "-fx-text-fill: Black;");
		startButton.setPrefWidth(300);
		startButton.setPrefHeight(100);

		
	}
	
	private void setHandler(SpaceInvadersGUI gui){
		startButton.setOnAction(event -> {
			if(tg.getSelectedToggle()==null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Select a difficulty level");
				alert.show();
				return;
			}
			int diff = 0;
			if(tg.getSelectedToggle().equals(easyButton)) {
				diff = 1;
			}
			else if(tg.getSelectedToggle().equals(medButton)) {
				diff = 2;
			}
			else if(tg.getSelectedToggle().equals(hardButton)) {
				diff = 3;
			}
			

			gui.startGame(diff);
		});
	}
}
