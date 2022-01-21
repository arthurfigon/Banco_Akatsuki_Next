package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.Apolice;
import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.model.Seguro;
import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.BancoDeDados;
import br.com.next.projetobanconext.utils.Constraints;
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
        idPane.setVisible(true);
    }

    public void receberSeguro(){
        for(Apolice apolice: Application.getConta().getCartaoCredito().getApolices()){
            if(apolice.getSeguro().equals(seguroSelecionado)){
                Application.getConta().setSaldo(
                        Application.getConta().getSaldo()
                                + apolice.getValorApolice()*2);
                Application.getConta().getCartaoCredito().getApolices().remove(apolice);
                Alerts.showAlertConfirmation("Seguro resgatado", null, "Seu seguro foi resgatado com sucesso!");
                return;
            }
        }
        Alerts.showAlertError("Apolice não contratada", null, "Apólice não contratada...");
    }

    public void atualizarLista(){
        observableList = FXCollections.observableList(listApolices);
        idCbSeguros.setItems(observableList);
    }

    public void confirmarSeguro(){
        Calendar cal = Calendar.getInstance();
        int anosContratacao = Integer.valueOf(idTxfAnos.getText()); // dar get.text em textfield Anos a serem contratados
        cal.add(Calendar.DAY_OF_MONTH,15);

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
        List<String> regrasSeguroVida = new ArrayList<>();
        List<String> regrasSeguroInvalidez = new ArrayList<>();
        List<String> regrasSeguroDesemprego = new ArrayList<>();

        Constraints.setTextFieldInteger(idTxfAnos);

        regrasSeguroVida.add("Indenização por despseas médico-hospitalares," +
                " ou por perda parcial ou total do funcionmanto ddos membros ou orgãos.");
        regrasSeguroVida.add("Reembolso de custos em diagnósticos de doenças graves," +
                "como infarto, acidente vascular cerebral e câncer.");
        regrasSeguroVida.add("Assistência funreal, para você e a sua família.");


        regrasSeguroInvalidez.add("Invalidez Parcial: Ocorre quando há uma perda parcial das funções." +
                "Por exemplo, uma pessoa que sofre um acidente e perda a visão em um só dos olhos.");
        regrasSeguroInvalidez.add("Invalidez Total: Ocorre quando há uma perda total das funções." +
                "Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um acidente e perde" +
                " a visão em ambos os olhos.");

        regrasSeguroDesemprego.add("Necessário trabalhar com registro CLT, com tempo mínimo" +
                " de estabilidade de 12 meses.");
        regrasSeguroDesemprego.add("Válido apenas para desligamentos involutários e sem justa causa.");
        regrasSeguroDesemprego.add("Não é valido em caso de demissão em massa (10% ou mais de demissões" +
                " simultâneas) ou falência/encerramento das atividades.");



        Seguro seguroVida = new Seguro(
                "01",
                "Seguro de Vida Next",
                regrasSeguroVida,
                36.00);

        listApolices.add(seguroVida);


        Seguro seguroInvalidez = new Seguro(
                "02",
                "Seguro de Invalidez Next",
                regrasSeguroInvalidez,
                26.00);

        listApolices.add(seguroInvalidez);

        Seguro seguroDesemprego = new Seguro(
                "03",
                "Seguro Desemprego Next",
                regrasSeguroDesemprego,
                16.00);

        listApolices.add(seguroDesemprego);


        observableList = FXCollections.observableList(listApolices);
        idCbSeguros.setItems(observableList);
        idPane.setVisible(false);
    }

}
