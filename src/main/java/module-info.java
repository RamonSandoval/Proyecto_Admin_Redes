module com.example.proyecto_admin_redes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyecto_admin_redes to javafx.fxml;
    exports com.example.proyecto_admin_redes;
}