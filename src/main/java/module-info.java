module edu.bilkent.cs.risk2b {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens edu.bilkent.cs.risk2b to javafx.fxml;
    exports edu.bilkent.cs.risk2b;
}
