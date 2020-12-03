package controller;

import java.util.Iterator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafxapplication1.GameManager;
import org.json.JSONObject; 
import ws.client.*;
import view.ViewFactory;

public class JoinController extends BaseController{

    @FXML
    private Label joinLabel;
    
    @FXML
    private TextArea userList;

    public JoinController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void getUsersBtnAction() {
        
        GameEngineService service = new GameEngineService();
        final GameEngine gmEngine = service.getGameEnginePort();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
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
        //viewFactory.showUsers();
        System.out.println(list);
        //viewFactory.closeStage(stage);
    }
    
    
    @FXML
    void backBtnAction() {
        viewFactory.showRole();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void forwardBtnAction() {
        viewFactory.showRoomPage();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void helpBtnAction() {

        viewFactory.showHelp();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
