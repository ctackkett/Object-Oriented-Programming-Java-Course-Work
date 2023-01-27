import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Our input scene, under Input Game from the main menu
 * create game records by selecting both moves and hitting submit
 * view them by going back to the main menu and selecting View Past Games
 */
public class InputScene extends Scene {
    //sets up the window layout, elements, and values
    private Button back = new Button("Back");
    private Button submit = new Button("Submit");


    static RadioButton rock = new RadioButton("Rock");
    static RadioButton paper = new RadioButton("Paper");
    RadioButton scissors = new RadioButton("Scissors");
    static RadioButton rock2 = new RadioButton("Rock");
    static RadioButton paper2 = new RadioButton("Paper");
    RadioButton scissors2 = new RadioButton("Scissors");

    ToggleGroup move1 = new ToggleGroup();
    ToggleGroup move2 = new ToggleGroup();

    Game result;
    int idCounter = 0;

    public InputScene() {
        super(new BorderPane());
        BorderPane stageLayout = (BorderPane)this.getRoot();
        Label p1Label = new Label("PLAYER 1");
        Label p2Label = new Label("PLAYER 2");
        VBox move1Layout = new VBox(5);
        VBox move2Layout = new VBox(5);
        HBox submitLayout = new HBox();

        rock.setToggleGroup(move1);
        paper.setToggleGroup(move1);
        scissors.setToggleGroup(move1);
        rock2.setToggleGroup(move2);
        paper2.setToggleGroup(move2);
        scissors2.setToggleGroup(move2);

        move1Layout.setPadding(new Insets(15,15,15,15));
        move2Layout.setPadding(new Insets(15,15,15,15));
        move1Layout.getChildren().addAll(p1Label, rock, paper, scissors);
        move2Layout.getChildren().addAll(p2Label, rock2, paper2, scissors2);
        submitLayout.getChildren().addAll(back, submit);

        move1Layout.setAlignment(Pos.CENTER_LEFT);
        move2Layout.setAlignment(Pos.CENTER_LEFT);
        submitLayout.setAlignment(Pos.CENTER);

        stageLayout.setLeft(move1Layout);
        stageLayout.setRight(move2Layout);
        stageLayout.setBottom(submitLayout);

        //records a game object
        //makes a popup of the game result upon clicking submit
        submit.setOnAction(e -> {
            getMoves();
            Alert resultPopup = new Alert(Alert.AlertType.INFORMATION, result.getGame());
            resultPopup.show();
        });
    }

    /**
     * takes you back to the main menu
     * @param mainStage the current scene, game history page
     * @param targetScene the target scene, in this case the main menu
     */
    public void setBackButtonTargets(Stage mainStage, Scene targetScene) {
        back.setOnAction(e -> mainStage.setScene(targetScene));
    }

    /**
     * the code for inputting and recording a new game object when submitted with the submit button
     * takes input from both radio buttons
     * @return a game object with a result based on the moves given
     */
    public Game getMoves() {
        result = new Game(idCounter, move1.getSelectedToggle(), move2.getSelectedToggle());
        idCounter++;
        Main.games.add(result.getGame());
        Main.gameObjects.add(result);
        System.out.println(result.getGame() + result.getID() + result.getP1ThrowString() + result.getP2ThrowString());
        return result;
    }
}