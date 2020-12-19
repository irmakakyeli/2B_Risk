package controller;

import java.util.Iterator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.json.JSONObject; 
import ws.client.*;
import view.ViewFactory;

public class JoinController extends BaseController{

    @FXML
    private Label joinLabel;
    
    

    public JoinController( ViewFactory viewFactory, String fxmlName) {
        super( viewFactory, fxmlName);
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
