package ws.guiclient.controller;

import ws.guiclient.GameManager;
import ws.guiclient.view.ViewFactory;

public abstract class BaseController {

    protected GameManager gameManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        this.gameManager = gameManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
