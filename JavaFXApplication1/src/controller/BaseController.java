package controller;


import view.ViewFactory;
import edu.bilkent.cs.simpleworldgame.*;
import ws.client.*;

public abstract class BaseController {

    protected GameEngine game;
    protected ViewFactory viewFactory;
    private String fxmlName;
    protected String userNameString;
    protected Integer userIdInteger;

    public BaseController(GameEngine game, ViewFactory viewFactory, String fxmlName) {
        this.game = game;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
