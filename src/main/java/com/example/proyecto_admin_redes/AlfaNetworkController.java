package com.example.proyecto_admin_redes;

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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.TextStyle;
import java.util.regex.Pattern;

public class AlfaNetworkController {

    private ListaEnlazadaDirecciones list = new ListaEnlazadaDirecciones();
    private Ips direccion_ip = new Ips();

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
    private TextArea Estatus;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtpassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text errorUser;

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
        validIP.setVisible(false);
        invalidIP.setVisible(false);
        textValidIP.setText("");
        textInvalidIP.setText("");

        //Mostrar estatus de la IP
        Text statValid = new Text("000");



        if (direccionIp.getText() == ("")) {
            errorIP.setText("Ingrese una direccion IP");
        } else
            direccion_ip.setIp(ip);
            try {
                ping = InetAddress.getByName(ip);
                if (ping.isReachable(3000)) {

                    validIP.setVisible(true);
                    direccionIp.setText("");
                    listIP.appendText("\t"+ip.replaceAll("/","")+"\t\t\t\tActivo" +"\t\t\t"+statValid);
                    direccion_ip.setEstatus("Activo");
                    crud.create(direccion_ip);


                } else {
                    listIP.appendText("\t"+ip.replaceAll("/","")+ " \t\t\t\t\tNo activo"+"\t\t\t");
                    invalidIP.setVisible(true);
                    direccion_ip.setEstatus("No Activo");
                    crud.create(direccion_ip);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }




    }

    @FXML
    protected void Scann(ActionEvent event) throws UnknownHostException {
        listIP.setText("");
        Estatus.setText("");
        int con_d=0;
        int con_n=0;
        int i;
        String seg="192.168.1.";
        InetAddress ip;
        try{
            for(i=70;i<80; i++){
                ip=InetAddress.getByName(seg+i);
                if(ip.isReachable(250)){
                    listIP.appendText("\t"+ip+"\t\t\t\t\tActivo\n");
                    con_d++;
                }else{
                    listIP.appendText("\t"+ip+" \t\t\t\t\tNo activo\n");
                    con_n++;
                }
            }
        }catch(Exception e){
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
        direccionIp.setText("");
        listIP.setText("");
        Estatus.setText("");
        invalidIP.setVisible(false);
        validIP.setVisible(false);

    }

    @FXML
    private void btnLogin(ActionEvent event){
        Object evt = event.getSource();
        if(evt.equals(btnLogin)){
            if(!txtUsuario.getText().isEmpty() && !txtpassword.getText().isEmpty()){
                String user = txtUsuario.getText();
                String pass = txtpassword.getText();

            }else{
                errorUser.setVisible(true);

            }

        }


    }




}
