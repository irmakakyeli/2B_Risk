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
import javafx.stage.Stage;
import javafxapplication1.GameManager;

import java.io.IOException;
import java.util.Iterator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.json.JSONObject;
import ws.client.GameEngine;
import ws.client.GameEngineService;
import ws.client.*;

public class ViewFactory {
    
    @FXML
    private TextArea userList;

    private GameManager gameManager;

    public ViewFactory(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void showMainMenu(){
        BaseController baseController = new MainMenuController(gameManager, this, "MainMenu.fxml");
        initializeStage(baseController, "css/style.css");
    }

    public void showHelp(){
        BaseController baseController = new HelpController(gameManager, this, "Help.fxml");

        initializeStage(baseController, "css/help.css");
    }

    public void showUsers(){
        BaseController baseController = new JoinController(gameManager, this, "JoinPage.fxml");

        initializeStage(baseController, "css/join.css");
    }
    
    public void showRole(){
        BaseController baseController = new RoleController(gameManager, this, "RoleChose.fxml");

        initializeStage(baseController, "css/role.css");
    }

    public void showHostPage(){
        BaseController baseController = new HostController(gameManager, this, "HostPage.fxml");

        initializeStage(baseController, "css/host.css");
    }

    public void showJoinPage(){
        BaseController baseController = new JoinController(gameManager, this, "JoinPage.fxml");

        initializeStage(baseController, "css/join.css");
    }

    public void showRoomPage(){
        RoomController controller = new RoomController(gameManager, this, "RoomPage.fxml");
        

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
        getUsersBtnAction(stage, controller);

        scene.getStylesheets().addAll(this.getClass().getResource("css/room.css").toExternalForm());
        stage.setScene(scene);

        stage.show();
    }

    public void showBoard(){
        BaseController baseController = new BoardController(gameManager, this, "GamePage.fxml");
        initializeStage(baseController, "css/board.css");
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

        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
    
    void getUsersBtnAction(Stage stage, RoomController controller) {
        
        GameEngineService service = new GameEngineService();
        final GameEngine gmEngine = service.getGameEnginePort();
        controller.getUserList().clear();
        String response = gmEngine.getPLayers();

        JSONObject json =  new JSONObject(response);
        Iterator<String> keys = json.keys();
        String list = "";
        while (keys.hasNext()) {
            String key = keys.next();
            String user = json.get(key).toString();
            user = retrieveName(user);
            list += key + " - " + user + "\n";

        }

        controller.getUserList().setText(list);
        //viewFactory.showUsers();
        System.out.println(list);
        //viewFactory.closeStage(stage);
    }
    
    public String retrieveName(String input){
        String userName = "";
        userName = input.substring(input.indexOf(":") + 2);
        userName = userName.substring(0, userName.indexOf('\"'));
        

        return userName;
    }
    
}
