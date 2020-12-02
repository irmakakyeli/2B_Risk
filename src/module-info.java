module Risk {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.swt;

    opens sample;
    opens sample.view;
    opens sample.controller;
}