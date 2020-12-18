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
import ws.client.GameEngine;
import ws.client.GameEngineService;
import ws.client.*;

public class ViewFactory {
    
    @FXML
    private TextArea userList;
    
    private Stage currStage;
            
    private Stage currentStage;
    
    private Stage hostOrJoin;

    public ViewFactory() {
        
    }

    public void showMainMenu(){
        BaseController baseController = new MainMenuController( this, "MainMenu.fxml");
        initializeStage(baseController, "css/style.css");
        currentStage = currStage;
    }

    public void showHelp(){
        BaseController baseController = new HelpController( this, "Help.fxml");

        initializeStage(baseController, "css/help.css");
    }

    public void showUsers(){
        BaseController baseController = new JoinController( this, "JoinPage.fxml");

        initializeStage(baseController, "css/join.css");
        currentStage = currStage;
    }
    
    public void showRole(){
        BaseController baseController = new RoleController(this, "RoleChose.fxml");

        initializeStage(baseController, "css/role.css");
        currentStage = currStage;
    }

    public void showHostPage(){
        BaseController baseController = new HostController(this, "HostPage.fxml");

        initializeStage(baseController, "css/host.css");
        currentStage = currStage;
        hostOrJoin = currStage;
    }

    public void showJoinPage(){
        BaseController baseController = new JoinController(this, "JoinPage.fxml");

        initializeStage(baseController, "css/join.css");
        currentStage = currStage;
        hostOrJoin = currStage;
    }

    public void showRoomPage(){
        RoomController controller = new RoomController(this, "RoomPage.fxml");
        

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
        currentStage = stage;
        stage.show();
    }

   public void showBoard(){
        BaseController baseController = new BoardController( this, "GamePage.fxml");
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
