module com.kaveesh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.kaveesh to javafx.fxml;
    exports com.kaveesh;
}
