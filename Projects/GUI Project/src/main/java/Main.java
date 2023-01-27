import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;

public class Main extends Application {

    private Button inputGameInfo = new Button("Input game");   // input new game information
    private Button viewGame = new Button("View past games");   // view past game information
    public static ObservableList<String> games = FXCollections.observableArrayList();
    public static ObservableList<Game> gameObjects = FXCollections.observableArrayList();


    /**The Main Menu for the GUI
     * Click Input Game to access what's coded in input scene and input games
     * Click View Past Games to access what's coded in Game History scene and view previous game details
     * @param startingStage the main menu stage
     */
    @Override
    public void start(Stage startingStage) { //Method that starts application

        InputScene inputScene = new InputScene(); //Creates game input scene
        GameHistoryScene gameHistoryScene = new GameHistoryScene(startingStage); //Creates game history scene


        inputGameInfo.setOnAction(e -> {           //Functionality of button from MAIN MENU to inputScene
            startingStage.setScene(inputScene);
        });

        viewGame.setOnAction(e -> {                //Functionality of button from MAIN MENU to gameHistoryScene
            startingStage.setScene(gameHistoryScene);
        });

        startingStage.setTitle("Rock Paper Scissors Tracker"); //Sets stage title text
        BorderPane stageLayout = new BorderPane();             //Creates new BorderPane
        VBox options = new VBox(50);                        //Vbox containing both navigation buttons

        options.getChildren().addAll(inputGameInfo, viewGame);

        options.setAlignment(Pos.CENTER);

        stageLayout.setCenter(options);

        Scene scene = new Scene(stageLayout, 200, 300); //Creation of MAIN MENU Scene
        startingStage.setScene(scene);
        startingStage.show();

        inputScene.setBackButtonTargets(startingStage, scene);
        gameHistoryScene.setBackButtonTargets(startingStage, scene);
    }


    /** Launches the application
     * @param args
     */
    public static void main(String[] args) {launch();}
}
