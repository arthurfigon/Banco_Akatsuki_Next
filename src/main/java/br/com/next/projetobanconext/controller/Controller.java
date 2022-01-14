package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.Constraints;
import br.com.next.projetobanconext.utils.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField campoSenha;
    @FXML
    private TextField campoLogin;
    @FXML
    private Label resultado;
    @FXML
    private Button btRegistrar;

    @FXML
    protected void onBtRegistrarAction(){
        Alerts.showAlertConfirmation("Registro Título", "Registro", "Registrado com Sucesso");
    }

    @FXML
    protected void leOqueTaEscrito(){
        if(campoLogin.getText().equals("Arthur") && campoSenha.getText().equals("1234567")){
            this.confirmado();
        }else{
            this.recusado();
        }
    }
    @FXML
    protected void confirmado(){
        resultado.setText("Aprovado!");
        resultado.setTextFill(Color.GREEN);
    }

    @FXML
    protected void recusado(){
        resultado.setText("Recusado!");
        resultado.setTextFill(Color.RED);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Oi");
        Constraints.setTextFieldInteger(campoLogin);
        Constraints.setTextFieldDouble(campoSenha);
    }
    public void checkInvalid(){
        Constraints.setTextFieldInteger(campoLogin);
    }
}