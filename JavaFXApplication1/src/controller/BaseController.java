package controller;


import view.ViewFactory;
import ws.client.GameEngine;

public abstract class BaseController {

    protected GameEngine game;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(GameEngine game, ViewFactory viewFactory, String fxmlName) {
        this.game = game;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
