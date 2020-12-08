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
import static javafx.application.Application.launch;
import org.json.JSONObject; 
import ws.guiclient.view.ViewFactory;

/**
 *
 * @author
 */
public class MainGUI extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ViewFactory viewFactory = new ViewFactory(new GameManager());
        viewFactory.showMainMenu();

    }
    
}
