package com.example.proyecto_admin_redes;

import Base.db.ConexionBD;
import Base.db.Crud;
import Base.db.Ips;
import Base.db.ListaEnlazadaDirecciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class AlfaNetworkController {

    private ListaEnlazadaDirecciones list = new ListaEnlazadaDirecciones();
    private Ips direccionIP = new Ips();

    ConexionBD conexion = new ConexionBD();
    Crud crud = new Crud();
    PreparedStatement ps;
    ResultSet rs;
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
    private TextArea listIP;

    @FXML
    protected void onSearchClick(ActionEvent event) {
        InetAddress ping;
        String ip = direccionIp.getText(); // Direccion IP de la RD
        validIP.setVisible(false);
        invalidIP.setVisible(false);
        textValidIP.setText("");
        textInvalidIP.setText("");

        if (direccionIp.getText() == ("")) {
            errorIP.setText("Ingrese una direccion IP");
        } else
            try {
                ping = InetAddress.getByName(ip);
                if (ping.isReachable(3000)) {
                    validIP.setVisible(true);
                    direccionIp.setText("");
                    listIP.appendText(ip + " - Activo\n");
                } else {
                    listIP.appendText(ip + " - No Activo\n");
                    invalidIP.setVisible(true);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }

    @FXML
    protected void Scann(ActionEvent event) throws UnknownHostException {
        String item = "";
        listIP.setText("");
        list.eliminarLista();

        list = crud.listDirecciones();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            direccionIP = list.getNodoDeLista(i);
            item += direccionIP.getEstatus() + "\t\t\t\t" + direccionIP.getIp() + "\t\t" + direccionIP.getName() + "\n";
        }
        listIP.setText(item);
        // label.setText("Se Actualizó La Lista!!");
    }

    @FXML
    protected void exitApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void clearIP(ActionEvent event) {
        direccionIp.setText("");
        listIP.setText("");

    }

}