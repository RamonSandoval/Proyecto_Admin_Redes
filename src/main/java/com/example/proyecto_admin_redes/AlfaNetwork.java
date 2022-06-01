package com.example.proyecto_admin_redes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AlfaNetwork extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
       // FXMLLoader fxmlLoader = new FXMLLoader(AlfaNetwork.class.getResource("admin.fxml"));
        Scene scene = new Scene(root, 600, 400);
        // stage.getIcons().add(new Image("/img/logo_AN.png"));
        stage.setTitle("Alfa Network Administrator");
        stage.getIcons().add( new Image(getClass().getResourceAsStream("/img/logo_AN.png")));
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}