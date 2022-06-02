package com.example.proyecto_admin_redes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

public class Controller_Login {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text errorUser;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;


    @FXML
    private void btnLogin(ActionEvent event) {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

            if(user.equals("") && pass.equals("")){
                errorUser.setVisible(true);
            }else{
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/redes","root","");

                    pst = con.prepareStatement("select * from usuarios where usuario=? and password=?");

                    pst.setString(1,user);
                    pst.setString(2,pass);

                    rs = pst.executeQuery();

                    if(rs.next()){
                        System.out.println("Logeo exitoso");
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        FXMLLoader loadProgram = new FXMLLoader(AlfaNetwork.class.getResource("admin.fxml"));
                        Parent program = (Parent) loadProgram.load(); //FXMLLoader.load(getClass().getResource("login.fxml"));
                        Stage stageprogram = new Stage();
                        stage.setResizable(false);
                        stageprogram.setResizable(false);
                        stageprogram.setScene(new Scene(program));
                        stageprogram.initModality(Modality.APPLICATION_MODAL);
                        stageprogram.getIcons().add( new Image(getClass().getResourceAsStream("/img/logo_AN.png")));
                        stageprogram.show();
                    }
                    else{
                        System.out.println("Logeo exitoso");
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        FXMLLoader loadProgram = new FXMLLoader(AlfaNetwork.class.getResource("usuario.fxml"));
                        Parent program = (Parent) loadProgram.load(); //FXMLLoader.load(getClass().getResource("login.fxml"));
                        Stage stageprogram = new Stage();
                        stageprogram.setScene(new Scene(program));
                        stage.setResizable(false);
                        stageprogram.setResizable(false);
                        stageprogram.initModality(Modality.APPLICATION_MODAL);
                        stageprogram.getIcons().add( new Image(getClass().getResourceAsStream("/img/logo_AN.png")));
                        stageprogram.show();
                        System.out.println("Usuarios incorrectos");
                        txtUsuario.setText("");
                        txtPassword.setText("");
                    }


                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(txtPassword == null){
                    
                }


            }
        }


    }

