package com.example.proyecto_admin_redes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AlfaNetwork extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AlfaNetwork.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // stage.getIcons().add(new Image("/img/logo_AN.png"));
        stage.setTitle("Alfa Network Administrator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}