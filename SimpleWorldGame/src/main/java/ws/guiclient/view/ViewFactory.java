package ws.guiclient.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ws.guiclient.GameManager;
import ws.guiclient.controller.*;

import java.io.IOException;

public class ViewFactory {

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
        BaseController baseController = new RoomController(gameManager, this, "RoomPage.fxml");

        initializeStage(baseController, "css/room.css");
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
}
