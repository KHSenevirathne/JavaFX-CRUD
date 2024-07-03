module com.kaveesh {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.kaveesh to javafx.fxml;
    exports com.kaveesh;
}
