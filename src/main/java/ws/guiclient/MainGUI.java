/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.guiclient;





import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.*;

import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.Stage; 
import javafx.geometry.*; 
import javafx.event.*;
import javafx.scene.paint.*;

import ws.guiclient.stubs.*;
import java.util.Iterator;
import org.json.JSONObject; 

/**
 *
 * @author nedim.alpdemir
 */
public class MainGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       

        GameEngineService service = new GameEngineService();
        final GameEngine gmEngine = service.getGameEnginePort();
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(65);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(15);
        grid.getColumnConstraints().addAll(column1, column2, column3); 
        Scene scene = new Scene(grid, 800, 600);
        Text scenetitle = new Text("World Game Entry Screen");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Enter User Name:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        Button usrButton = new Button("Add User");
        grid.add(usrButton, 2, 1);

        final TextArea userList = new TextArea();
        grid.add(userList, 1, 2);
        Button getUsrsButton = new Button("Get Users");
        grid.add(getUsrsButton, 2, 2);
        
        Button getWorldButton = new Button("Get WorldInfo");
        grid.add(getWorldButton, 2, 3);
        
        Label statusLbl = new Label("Status : ");
        grid.add(statusLbl, 0, 3);
        
        final Label statusMsg = new Label("Message will come here");
        grid.add(statusMsg, 1, 3);
        
        usrButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                statusMsg.setTextFill(Color.FIREBRICK);
                
                if (userTextField.getText().isEmpty()) {
                    statusMsg.setText("Add User text field is empty, please enter a name  ... ");
                } else {                   
                    gmEngine.registerPLayer(userTextField.getText());
                    statusMsg.setText("Add User message has been sent to the GameEngine Web Service ... ");
                }
                
            }
        });
        
        getUsrsButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                statusMsg.setTextFill(Color.FIREBRICK);
                statusMsg.setText("Getting all users from GameEngine Web Service ... ");
                userList.clear();
                String response = gmEngine.getPLayers();
                
                JSONObject json =  new JSONObject(response);
                Iterator<String> keys = json.keys();
                String list = "";
                while (keys.hasNext()) {
                    String key = keys.next();
                    String user = json.get(key).toString();
                    list += key + " - " + user + "\n";

                }

                userList.setText(list);
            }
        });
        
        getWorldButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                statusMsg.setTextFill(Color.FIREBRICK);
                statusMsg.setText("Getting World Information from GameEngine Web Service ... ");
                userList.clear();
                String response = gmEngine.getWorld();
                //String response = "";
                JSONObject json =  new JSONObject(response);
                Iterator<String> keys = json.keys();
                String list = "";
                while (keys.hasNext()) {
                    String key = keys.next();
                    String user = json.get(key).toString();
                    list += key + " - " + user + "\n";

                }

                userList.setText(list);
            }
        });
        
        primaryStage.setTitle("Main Game Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
