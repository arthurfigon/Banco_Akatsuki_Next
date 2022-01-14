package br.com.next.projetobanconext.controller;


import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.utils.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransferenciaController implements Initializable {

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
    public void onBtAtualizarAction(){
        listConta = BancoDeDados.returnTodasContas();
        observableList = FXCollections.observableList(listConta);
        contaComboBox.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Criado");
        listConta = BancoDeDados.returnTodasContas();
        observableList = FXCollections.observableList(listConta);
        contaComboBox.setItems(observableList);
    }
}
