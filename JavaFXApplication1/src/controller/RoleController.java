package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.ViewFactory;
import ws.client.*;
import edu.bilkent.cs.simpleworldgame.*;

public class RoleController extends BaseController {

    @FXML
    private Label roleLabel;

    public RoleController( GameEngine game,  ViewFactory viewFactory, String fxmlName) {
        super( game, viewFactory, fxmlName);
    }

    @FXML
    void helpBtnAction() {
        viewFactory.showHelp();
        Stage stage = (Stage) roleLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void hostBtnAction() {
        viewFactory.showHostPage();
        Stage stage = (Stage) roleLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void joinBtnAction() {
        viewFactory.showJoinPage();
        Stage stage = (Stage) roleLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}


