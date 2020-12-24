package view;

import controller.JoinController;
import controller.HostController;
import controller.MainMenuController;
import controller.BaseController;
import controller.HelpController;
import controller.RoleController;
import controller.RoomController;
import controller.BoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Iterator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.json.JSONObject;
//import ws.client.*;
import edu.bilkent.cs.simpleworldgame.*;

public class ViewFactory {
    
    @FXML
    private TextArea userList;
    
    private Stage currStage;
            
    private Stage currentStage;
    
    private Stage hostOrJoin;
    
    private final GameEngine game;
    
    public ViewFactory() {
        GameEngineService service = new GameEngineService();
        game = service.getGameEnginePort();
    }

    public void showMainMenu(){
        BaseController baseController = new MainMenuController(game, this, "MainMenu.fxml");
        initializeStage(baseController, "css/style.css");
        currentStage = currStage;
    }

    public void showHelp(){
        BaseController baseController = new HelpController( game, this, "Help.fxml");

        initializeStage(baseController, "css/help.css");
    }

    public void showUsers(){
        BaseController baseController = new JoinController(game,  this, "JoinPage.fxml");

        initializeStage(baseController, "css/join.css");
        currentStage = currStage;
    }
    
    public void showRole(){
        BaseController baseController = new RoleController(game, this, "RoleChose.fxml");

        initializeStage(baseController, "css/role.css");
        currentStage = currStage;
    }

    public void showHostPage(){
        BaseController baseController = new HostController(game, this, "HostPage.fxml");

        initializeStage(baseController, "css/host.css");
        currentStage = currStage;
        hostOrJoin = currStage;
    }

    public void showJoinPage(){
        BaseController baseController = new JoinController(game, this, "JoinPage.fxml");

        initializeStage(baseController, "css/join.css");
        currentStage = currStage;
        hostOrJoin = currStage;
    }

    public void showRoomPage(String userName, Integer userId){
        BaseController baseController = new RoomController(game, userName, userId, this, "RoomPage.fxml");
        initializeStage(baseController, "css/room.css");
        currentStage = currStage;
    }

   public void showBoard(String userName, Integer userId){
        BaseController baseController = new BoardController(game, userName, userId, this, "GamePage.fxml");
        initializeStage(baseController, "css/board.css");
        currentStage = currStage;
    }

    public void initializeStage(BaseController controller, String fxmlName){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent;

        try{
            parent = fxmlLoader.load();
        } catch (IOException exception){
            exception.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        scene.getStylesheets().addAll(this.getClass().getResource(fxmlName).toExternalForm());
        stage.setScene(scene);
        
        currStage = stage;
        
        stage.show();
    }

    public void closeStage(Stage stage){
        
        stage.close();
    }
    
    public Stage getCurrentStage() {
        
        return currentStage;
    }
    
    public Stage getHostOrJoin() {
        
        return hostOrJoin;
    }
    
    public String retrieveName(String input){
        String userName = "";
        userName = input.substring(input.indexOf(":") + 2);
        userName = userName.substring(0, userName.indexOf('\"'));
        

        return userName;
    }
}
