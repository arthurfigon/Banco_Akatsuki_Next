package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.ClienteBO;
import br.com.next.projetobanconext.model.ContaBO;
import br.com.next.projetobanconext.model.Endereco;
import br.com.next.projetobanconext.model.TipoConta;
import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.BancoDeDados;
import br.com.next.projetobanconext.utils.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistrarController implements Initializable {

    @FXML
    public CheckBox cbContaCorrente;
    public TextField textNome;
    public TextField textCPF;
    public TextField textNascimento;
    public TextField textBairro;
    public TextField textNumero;
    public TextField textLogradouro;
    public TextField textCEP;
    public TextField textCidade;
    public TextField textEstado;
    public TextField textSenha;
    public CheckBox cbContaPoupanca;

    @FXML
    public void onBtCadastrarAction(){
        try{
            Endereco endereco = new Endereco(textLogradouro.getText(),textBairro.getText(),textCidade.getText(),
                    textEstado.getText(),textNumero.getText(),textCEP.getText());
            if(!ClienteBO.checkCPF(textCPF.getText())){
                Alerts.showAlertError("Erro no CPF", "CPF Inválido", "Por favor, insira um CPF Válido...");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;
            try {
                data = sdf.parse(textNascimento.getText().strip());
            }catch (ParseException e) {
                Alerts.showAlertError("Erro na Data de Nascimento", "Data de Nascimento Inválida", "Por favor, insira uma Data Válida...");
                return;
            }
            ClienteBO clienteBO = new ClienteBO(textCPF.getText(),textNome.getText(),data,endereco);
            if(!ContaBO.checkSenhaValida(textSenha.getText())){
                Alerts.showAlertError("Erro na Senha", "Senha Inválida", "A senha deve possuir de 6 a 16 caracteres...");
                return;
            }
            if (cbContaCorrente.isSelected()){
                if(BancoDeDados.findContaByCPF(textCPF.getText(), TipoConta.CORRENTE) == null) {
                    ContaBO contaBO = new ContaBO(textSenha.getText(), clienteBO.getCliente(), TipoConta.CORRENTE);
                }else{
                    Alerts.showAlertError("CPF já cadastrado", null, "Esse CPF já foi cadastrado para Corrente...");
                    return;
                }
            }
            if(cbContaPoupanca.isSelected()){
                if(BancoDeDados.findContaByCPF(textCPF.getText(), TipoConta.POUPANCA) == null) {
                    ContaBO contaBO = new ContaBO(textSenha.getText(), clienteBO.getCliente(), TipoConta.POUPANCA);
                }else{
                    Alerts.showAlertError("CPF já cadastrado", null, "Esse CPF já foi cadastrado para Poupança...");
                    return;
                }
            }
            if (!cbContaCorrente.isSelected() && !cbContaPoupanca.isSelected()){
                Alerts.showAlertError("Nenhuma Conta Selecionada", "Nenhuma Conta Selecionada", "Favor selecionar o tipo de Conta...");
                return;
            }
            limpaCampos();
            Application.changeScene("main");
        }catch (Exception e){
            Alerts.showAlertError("Erro", "Erro", "Ocorreu um erro na inserção de algum dado...");
        }
    }

    public void limpaCampos(){
        cbContaCorrente.setSelected(false);
        textNome.setText("");
        textCPF.setText("");
        textNascimento.setText("");
        textBairro.setText("");
        textNumero.setText("");
        textLogradouro.setText("");
        textCEP.setText("");
        textCidade.setText("");
        textEstado.setText("");
        textSenha.setText("");
        cbContaPoupanca.setSelected(false);
    }


    @FXML
    public void onBtVoltarAction(){
        limpaCampos();
        Application.changeScene("main");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Constraints.setTextFieldLetters(textNome);
        Constraints.setTextFieldInteger(textCPF);
        Constraints.setTextFieldMaxLength(textCPF,11);
        Constraints.setTextFieldMaxLength(textSenha, 16);
        Constraints.setTextFieldDate(textNascimento);
        Constraints.setTextFieldMaxLength(textNascimento,10);
    }
}
