package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class hava extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(hava.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 430, 282);
        // stage.getIcons().add(new Image("/img/logo_AN.png"));
        stage.setTitle("Inicio Sesion");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}