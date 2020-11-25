package sample;

import sample.Dice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;




public class Main extends Application implements EventHandler<ActionEvent> {

    //Image background;
    Button startGame;
    Button quit;
    Button help;
    Pane pane1;
    Pane pane2;
    Label riskLabel;
    Scene mainMenu;
    Scene hostJoin;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //stage > scene > container > node


        Button startGame = new Button("PLAY");
        startGame.setLayoutX(410.0);
        startGame.setLayoutY(225.0);
        startGame.setOnAction(this);
        Button quit = new Button("QUIT");;
        quit.setLayoutX(410.0);
        quit.setLayoutY(280.0);
        //quit.setOnAction(this);
        Button help = new Button("?");
        //help.setOnAction(this);
        pane1 = new Pane();
        pane2 = new Pane();
        mainMenu = new Scene(pane1, 900, 450);
        hostJoin = new Scene(pane2, 900, 450);


        //background = new Image("997100.jpg");
        riskLabel = new Label("RISK");
        riskLabel.setStyle("-fx-font-size: 100; -fx-text-fill: #c40707;");
        riskLabel.setLayoutX(340.0);
        riskLabel.setLayoutY(90.0);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //ImageView iv = new ImageView();
        //iv.setImage(background);
        pane1.getChildren().add(startGame);
        pane1.getChildren().add(quit);
        pane1.getChildren().add(help);
        pane1.getChildren().add(riskLabel);
        //pane1.getChildren().add(root);
        primaryStage.setTitle("RISK DEMO");
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void handle(ActionEvent event) {

    }
}

