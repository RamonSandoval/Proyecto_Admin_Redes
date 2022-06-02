package com.example.proyecto_admin_redes;

import javax.swing.JOptionPane;
import Base.db.ConexionBD;
import Base.db.Crud;
import Base.db.Ips;
import Base.db.ListaEnlazadaDirecciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;



import java.io.IOException;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AlfaNetworkController {

    private ListaEnlazadaDirecciones list = new ListaEnlazadaDirecciones();
    private Ips direccion_ip = new Ips();

    ConexionBD conexion = new ConexionBD();
    Crud crud = new Crud();
    PreparedStatement ps;
    ResultSet rs;
    public static final String green = "\u001B[32m";
    @FXML
    private Label welcomeText;
    @FXML
    private TextField direccionIp;
    @FXML
    private Text errorIP;
    @FXML
    private Pane validIP;
    @FXML
    private Text textValidIP;
    @FXML
    private Text textScann;
    @FXML
    private Pane invalidIP;
    @FXML
    private Text textInvalidIP;
    @FXML
    private TextFlow listIP;
    @FXML
    private TextField newIP;
    @FXML
    private  TextField range1;
    @FXML
    private TextField range2;

    private boolean validarIP(String ip) {
        boolean val = false;
        String regex = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
        val = Pattern.matches(regex, ip);
        return val;
    }

    @FXML
    protected void onSearchClick(ActionEvent event) {
        InetAddress ping;
        String ip = direccionIp.getText(); // Direccion IP de la RD

        textValidIP.setText("");
        listIP.getChildren().clear();
        textInvalidIP.setText("");
        Text area_verde = new Text("■");
        area_verde.setStyle("-fx-fill: #47ED04 ;-fx-font-size: 20px");
        Text area_gris = new Text("■");
        area_gris.setStyle("-fx-fill: #7f7f7f;-fx-font-size: 20px ");
        if (direccionIp.getText() == ("")) {
            errorIP.setText("Ingrese una direccion IP");
            errorIP.setVisible(true);
        } else
            direccion_ip.setIp(ip);
            try {
                ping = InetAddress.getByName(ip);
                if (ping.isReachable(3000)) {
                    direccionIp.setText("");
                    Text valido = new Text("\t  "+ip+"\t\t\t\t");
                    listIP.getChildren().addAll(valido,area_verde);
                    direccion_ip.setEstatus("Activo");
                    crud.create(direccion_ip);
                } else {
                    direccionIp.setText("");
                    Text invalido = new Text("\t  "+ip+"\t\t\t\t");
                    listIP.getChildren().addAll(invalido,area_gris);

                    direccion_ip.setEstatus("No Activo");
                    crud.create(direccion_ip);
                }
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mensaje de Error");
                alert.setHeaderText(null);
                alert.setContentText("Ingrese datos correctos, porfavor!");
                alert.showAndWait();
            }
    }

    @FXML
    protected void Scann(ActionEvent event) throws UnknownHostException {
        Text clearField = new Text("");
        listIP.getChildren().add(clearField);

        int i;
        String seg= newIP.getText();
        String inicio = range1.getText();
        String limite = range2.getText();
        InetAddress ip;
        try{
            for(i=Integer.valueOf(inicio);i<=Integer.valueOf(limite); i++){
                ip=InetAddress.getByName(seg+i);
                if(ip.isReachable(2050)){
                    Text valido = new Text("\t   "+ip.toString().replaceAll("/","")+"\t\t\t\t");
                    //ip.toString().replaceAll("/","");
                    Text area_verde = new Text("■\n");
                    area_verde.setStyle("-fx-fill: #47ED04 ;-fx-font-size: 20px");
                    listIP.getChildren().addAll(valido,area_verde);
                }else{
                    Text invalido = new Text("\t   "+ip.toString().replaceAll("/","")+"\t\t\t\t");
                    Text area_gris = new Text("■\n");
                    area_gris.setStyle("-fx-fill: #7f7f7f;-fx-font-size: 20px ");
                    listIP.getChildren().addAll(invalido,area_gris);
                }
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensaje de Error");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese datos correctos, porfavor!");
            alert.showAndWait();
        }
        /*
        String item = "";
        listIP.setText("");
        list.eliminarLista();

        list = crud.listDirecciones();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            direccion_ip = list.getNodoDeLista(i);
            item += direccion_ip.getEstatus() + "\t" + direccion_ip.getIp() ;
        }
        listIP.setText(item);
        welcomeText.setText("Se Actualizó La Lista!!");*/

    }

    @FXML
    protected void exitApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void clearIP(ActionEvent event) {
        listIP.getChildren().clear();
        newIP.setText("");
        range1.setText("");
        range2.setText("");
        direccionIp.setText("");
        errorIP.setVisible(false);

    }
    @FXML
    protected void acercaDe(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca del Programa");
        alert.setHeaderText(null);
        alert.setContentText("Programa elaborado por:\n\nGallegos Cabrera Luisa Aylin\n" +
                "Sandoval Pérez Ramón\nRomero Galván Orlando\nRuíz Rodríguez Efraín");
        alert.showAndWait();
    }


    @FXML
    protected void setIP (ActionEvent event){
        System.exit(0);
    }
}
