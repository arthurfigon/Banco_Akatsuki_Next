package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.Apolice;
import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.model.Seguro;
import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ApolicesController implements Initializable {

    public ComboBox<Seguro> idCbSeguros;
    public TextField idTxfAnos;
    public AnchorPane idPane;
    public Label idLbSeguro;
    public Label idLbValor;
    public Label idLbRegras;
    private List<Seguro> listApolices = new ArrayList<>();
    private ObservableList<Seguro> observableList;
    private Seguro seguroSelecionado;

    public void voltar(){
        Application.changeScene("cartoes");
    }

    public void selecionarNaLista(){
        this.seguroSelecionado = idCbSeguros.getSelectionModel().getSelectedItem();
        idLbSeguro.setText("Seguro: "+seguroSelecionado.getNome());
        idLbValor.setText("Valor por ano: "+String.valueOf(seguroSelecionado.getValorAno()));
        idLbRegras.setText("Regras: "+seguroSelecionado.getRegras());
        idPane.setVisible(true);

    }

    public void atualizarLista(){
        observableList = FXCollections.observableList(listApolices);
        idCbSeguros.setItems(observableList);
    }

    public void confirmarSeguro(){
        Calendar cal = Calendar.getInstance();
        int anosContratacao = Integer.valueOf(idTxfAnos.getText()); // dar get.text em textfield Anos a serem contratados
        cal.add(Calendar.YEAR,
                anosContratacao);

        Apolice apolice = new Apolice(
                String.valueOf(listApolices.size()),
                seguroSelecionado.getValorAno() * anosContratacao,
                seguroSelecionado.getRegras(),
                seguroSelecionado,
                Calendar.getInstance().getTime(),
                cal.getTime());

        if (Application.getConta().getSaldo() < seguroSelecionado.getValorAno() * anosContratacao){
            Alerts.showAlertError("Saldo na Contratação", null, "Saldo Insuficiente para Contratação...");
            return;
        }

        Application.getConta().getCartaoCredito().addApolice(apolice);
        Alerts.showAlertConfirmation("Seguro Contratado", null, "Seguro contratado com Sucesso!");
        idPane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Seguro seguro1 = new Seguro(
                "01",
                "Seguro de Vida Next",
                "Regra: Tem mais de 18 anos",
                2000.00);
                Calendar cal = Calendar.getInstance();
                cal.add(10, Calendar.YEAR);

        listApolices.add(seguro1);


        Seguro seguro2 = new Seguro(
                "02",
                "Seguro de Carro Next",
                "Regra: Tem mais de 18 anos e ter um Carro",
                3000.00);

        listApolices.add(seguro2);


        observableList = FXCollections.observableList(listApolices);
        idCbSeguros.setItems(observableList);
        idPane.setVisible(false);
    }

}
