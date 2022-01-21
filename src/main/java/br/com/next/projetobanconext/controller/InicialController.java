package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.model.TipoConta;
import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.BancoDeDados;
import br.com.next.projetobanconext.utils.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class InicialController implements Initializable {

    public Label cpf;
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
        resultado.setText("");
        Application.changeScene("registrar");
    }

    @FXML
    protected void onBtLoginCorrenteAction(){
        Conta conta;
        conta = BancoDeDados.findContaByCPF(campoLogin.getText(), TipoConta.CORRENTE);
        if(conta == null){
            Alerts.showAlertError("CPF Inexistente", null, "CPF não cadastrado");
            return;
        }
        /*else if(!cbContaCorrente.isSelected() && cbContaPoupanca.isSelected()){
            conta = BancoDeDados.findContaByCPF(campoLogin.getText(), TipoConta.POUPANÇA);
            if(conta == null){
                Alerts.showAlertError("CPF Inexistente", null, "CPF não cadastrado");
                return;
            }
        }*/
        if(conta.getCliente().getCpf().equals(campoLogin.getText()) && conta.getSenha().equals(campoSenha.getText())){
            Application.setConta(conta);
            resultado.setText("");
            Application.changeScene("transferencia");
        }else{
            recusado();
            return;
        }
    }

    @FXML
    protected void onBtLoginPoupancaAction(){
        Conta conta;
        conta = BancoDeDados.findContaByCPF(campoLogin.getText(), TipoConta.CORRENTE);
        conta = BancoDeDados.findContaByCPF(campoLogin.getText(), TipoConta.POUPANCA);
        if(conta == null){
            Alerts.showAlertError("CPF Inexistente", null, "CPF não cadastrado");
            return;
        }
        if(conta.getCliente().getCpf().equals(campoLogin.getText()) && conta.getSenha().equals(campoSenha.getText())){
            Application.setConta(conta);
            resultado.setText("");
            Application.changeScene("transferencia");
        }else{
            recusado();
            return;
        }
    }

    @FXML
    protected void leOqueTaEscrito(){
        if(campoLogin.getText().equals("Arthur") && campoSenha.getText().equals("1234567")){
        }else{
            this.recusado();
        }
    }

    @FXML
    protected void recusado(){
        resultado.setText("Login inválido");
        Color color = Color.web("#952929");
        resultado.setTextFill(color);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Constraints.setTextFieldInteger(campoLogin);
        Constraints.setTextFieldMaxLength(campoLogin, 11);
        Constraints.setTextFieldMaxLength(campoSenha, 16);
    }
}