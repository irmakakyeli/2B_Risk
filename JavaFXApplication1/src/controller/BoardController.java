package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ViewFactory;

public class BoardController extends BaseController{
    public BoardController( ViewFactory viewFactory, String fxmlName) {
        super( viewFactory, fxmlName);
    }

    

@FXML
    void regionBtnAction(final ActionEvent event) {
        
        //String popUp = "";
        //if("alaska".equals(((Button)event.getSource()).getText()) == true) {
            //popUp = "Alaska";
        //}
       
        Stage popupwindow=new Stage();
      
    popupwindow.initModality(Modality.APPLICATION_MODAL);
    popupwindow.setTitle("This is a pop up window");
      
      
    Label label1= new Label(((Button)event.getSource()).getText());
      
     
    Label label2= new Label("Belongs to: -Player-");
     
     
    //button1.setOnAction(e -> popupwindow.close());
     
     

    VBox layout= new VBox(10);
     
      
    layout.getChildren().addAll(label1, label2);
      
    layout.setAlignment(Pos.CENTER);
      
    Scene scene1= new Scene(layout, 300, 250);
      
    popupwindow.setScene(scene1);
      
    popupwindow.showAndWait();
        
    }
}