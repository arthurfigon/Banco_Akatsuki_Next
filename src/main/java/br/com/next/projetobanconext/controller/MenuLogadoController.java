package br.com.next.projetobanconext.controller;


import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.model.ContaBO;
import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuLogadoController implements Initializable {

    @FXML
    public Label labelSaldo;
    public TextField txtValor;
    public Label labelValor;
    public ImageView btAplicar;
    public Label labelContaDestino;
    public ImageView btAtualizar;
    public Label idNomeCartao;
    public Label idNumeroCartao;
    public Label idTipoCliente;
    public Label idTipoConta;
    private boolean depositar = true;

    List<Conta> listConta;

    @FXML
    private ComboBox<Conta> contaComboBox;

    private ObservableList<Conta> observableList;

    private Conta contaSelecionada;

    @FXML
    public void onContaComboBoxAction(){
        this.contaSelecionada = contaComboBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void onBtDepositarAction(){
        labelValor.setVisible(true);
        btAplicar.setVisible(true);
        txtValor.setVisible(true);
        labelContaDestino.setVisible(false);
        btAtualizar.setVisible(false);
        contaComboBox.setVisible(false);
        atualizaDados();
        depositar = true;
    }

    @FXML
    public void onBtTransferenciaAction(){
        txtValor.setVisible(true);
        labelValor.setVisible(true);
        btAplicar.setVisible(true);
        labelContaDestino.setVisible(true);
        btAtualizar.setVisible(true);
        contaComboBox.setVisible(true);
        atualizaDados();
        depositar = false;
    }
    
    @FXML
    public void onBtAplicarAction(){
        ContaBO contaBO = new ContaBO(Application.getConta());
        ContaBO contaBOExterna = new ContaBO(contaSelecionada);
        if(depositar == true){
            contaBO.depositar(Double.valueOf(txtValor.getText()));
        }else{
            if(contaSelecionada == null){
                Alerts.showAlertError("Conta Não Selecionada",null, "Por favor, selecione uma conta...");
                return;
            }
            if(!contaBO.transferir(Double.valueOf(txtValor.getText()), contaBOExterna)){
                Alerts.showAlertError("Saldo Indisponível...", null, "Você não tem saldo suficiente para realizar essa operação...");
            }
        }
        atualizaDados();
        txtValor.setVisible(false);
        labelValor.setVisible(false);
        btAplicar.setVisible(false);
        labelContaDestino.setVisible(false);
        btAtualizar.setVisible(false);
        contaComboBox.setVisible(false);
        depositar = false;
    }

    public void atualizaDados(){
        idNomeCartao.setText(Application.getConta().getCliente().getNome());
        idNumeroCartao.setText(Application.getConta().getNumero());
        labelSaldo.setText("R$ "+String.valueOf(Application.getConta().getSaldo()));
        idTipoCliente.setText(Application.getConta().getCliente().getTipo().name());
        idTipoConta.setText("Conta "+Application.getConta().getTipoConta().name());
    }

    public void deslogar(){
        Application.changeScene("main");
    }

    public void aplicarTaxasContas() {
        ArrayList<Conta> contas = BancoDeDados.returnTodasContas();
        for (Conta conta : contas) {
            ContaBO continha= new ContaBO(conta);
            continha.aplicaTaxa();
        }
        Alerts.showAlertConfirmation("Taxas",null, "Taxas aplicadas a todas as contas...");
    }

    @FXML
    public void onBtAtualizarAction(){
        listConta = BancoDeDados.returnTodasContas();
        observableList = FXCollections.observableList(listConta);
        contaComboBox.setItems(observableList);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listConta = BancoDeDados.returnTodasContas();
        observableList = FXCollections.observableList(listConta);
        contaComboBox.setItems(observableList);
    }

    @FXML
    public void onBtAreaPixAction() {
        Application.changeScene("pix");
    }

    @FXML
    public void onBtCartoesAction() {
        Application.changeScene("cartoes");
    }
}
