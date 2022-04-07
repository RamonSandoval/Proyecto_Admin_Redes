package com.example.proyecto_admin_redes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetAddress;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txt_direccionIp;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {

        InetAddress ping;
        String ip = txt_direccionIp.getText(); // Ip de la máquina remota
        try {
            ping = InetAddress.getByName(ip);
            if(ping.isReachable(3000)){
                System.out.println(ip+" - Activo");
            }else {
                System.out.println(ip+" - no Activo");
            }
        } catch (IOException ex) { System.out.println(ex); }

    }
}

