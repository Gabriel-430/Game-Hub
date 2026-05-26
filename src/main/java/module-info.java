module gamehub {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires transitive java.sql;
    requires java.base;

    opens gamehub to javafx.fxml;
    opens gamehub.controladores to javafx.fxml;
    opens gamehub.dto to javafx.base;

    exports gamehub;
    exports gamehub.controladores;
    exports gamehub.dto;
}