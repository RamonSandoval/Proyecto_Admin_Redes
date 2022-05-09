module com.example.proyecto_admin_redes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyecto_admin_redes to javafx.fxml;
    exports com.example.proyecto_admin_redes;
}