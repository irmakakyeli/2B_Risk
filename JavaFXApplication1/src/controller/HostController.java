package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxapplication1.GameManager;
import view.ViewFactory;

public class HostController extends BaseController{

    @FXML
    private Label hostLabel;
    boolean isOtomatic;

    public HostController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void backBtnAction() {
        viewFactory.showRole();
        Stage stage = (Stage) hostLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void forwardBtnAction() {
        viewFactory.showRoomPage();
        Stage stage = (Stage) hostLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void helpBtnAction() {

        viewFactory.showHelp();
        Stage stage = (Stage) hostLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
    
    @FXML
    void toggleBtnOn() {
        
         isOtomatic = true;
    }
    
    @FXML
    void toggleBtnOff() {
        
         isOtomatic = false;
    }
    
    public boolean getConfig() {
        
        return isOtomatic;
    }
    
    
}
