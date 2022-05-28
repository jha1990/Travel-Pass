module com.gui.mytisystem1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.gui.mytisystem1 to javafx.fxml;
    exports gui;
    opens gui to javafx.fxml;
}