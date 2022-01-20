package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.CartaoCredito;
import br.com.next.projetobanconext.model.CartaoDebito;
import br.com.next.projetobanconext.model.Compra;
import br.com.next.projetobanconext.utils.Alerts;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartoesController {


    public Label idNomeCartao;
    public Label idNumeroCartao;
    public AnchorPane idPaneCadastrarCredito;
    public TextField txtSenhaCredito;
    public TextField txtBandeiraCredito;
    public TextField txtBandeiraDebito;
    public TextField txtVencimentoCredito;
    public AnchorPane idPaneCadastrarDebito;
    public TextField txtSenhaDebito;
    public TextField txtLimiteDebito;
    public AnchorPane idPaneAcessarCredito;
    public Label idLabelValorFaturaCredito;
    public Label idLabelBandeiraCredito;
    public Label idLabelDataVencimentoCredito;
    public ImageView idBtBloquearCredito;
    public Label idLabelLimiteCredito;
    public AnchorPane idPaneAcessarDebito;
    public Label idLabelBandeiraDebito;
    public ImageView idBtBloquearDebito;
    public Label idLabelLimiteDebito;
    public AnchorPane idPaneCompraCredito;
    public TextField idTxtValor;
    public TextField idTxtNomeProduto;

    @FXML
    public void onBtCreditoAction(){
        if(Application.getConta().getCartaoCredito() == null) {
            fechaTudo();
            idPaneCadastrarCredito.setVisible(!idPaneCadastrarCredito.isVisible());
        } else{
            if(!idPaneAcessarCredito.isVisible()){
                idPaneAcessarCredito.setVisible(true);
            }else{
                idPaneAcessarCredito.setVisible(false);
            }
            idNomeCartao.setText(Application.getConta().getCliente().getNome());
            idNumeroCartao.setText(Application.getConta().getCartaoCredito().getNumero());
            idLabelValorFaturaCredito.setText("Fatura: "+String.valueOf(Application.getConta().getCartaoCredito().getValorFatura()));
            idLabelLimiteCredito.setText("Limite: "+String.valueOf(Application.getConta().getCartaoCredito().getLimite()));
            idLabelBandeiraCredito.setText("Bandeira: "+Application.getConta().getCartaoCredito().getBandeira());
            idLabelDataVencimentoCredito.setText("Vencimento: "+Application.getConta().getCartaoCredito().getDateString());
        }
    }

    public void onBtSegurosAction(){
        if(Application.getConta().getCartaoCredito() != null) {
            Application.changeScene("apolices");
        }else{
            Alerts.showAlertError("Cartão Não Cadastrado", null, "Cartão de Crédito não cadastrado...");
        }
    }

    @FXML
    public void onBtComprarAction(){
        if(Application.getConta().getCartaoCredito() != null) {
            fechaTudo();
            idPaneCompraCredito.setVisible(!idPaneCompraCredito.isVisible());
        }else{
            Alerts.showAlertError("Cartão Não Cadastrado", null, "Cartão de Crédito não cadastrado...");
        }
    }

    public void onBtConfirmarCompraAction(){
        Compra compra = new Compra(idTxtNomeProduto.getText(),Double.parseDouble(idTxtValor.getText()));
        Application.getConta().getCartaoCredito().addCompra(compra);
        fechaTudo();
    }

    public void onBtPagarFaturaAction(){
        if(Application.getConta().getCartaoCredito() != null) {
            if(Application.getConta().getCartaoCredito().pagarFatura(Application.getConta())) {
                Alerts.showAlertConfirmation("Sucesso", null, "Fatura paga com sucesso!");
            }else{
                Alerts.showAlertError("Erro no Pagamento", null, "Saldo Insuficiente...");
            }
        }else{
            Alerts.showAlertError("Cartão Não Cadastrado", null, "Cartão de Crédito não cadastrado...");
        }
    }

    public void fechaTudo(){
        idPaneCadastrarDebito.setVisible(false);
        idPaneCompraCredito.setVisible(false);
        idPaneAcessarCredito.setVisible(false);
        idPaneCadastrarCredito.setVisible(false);
        idPaneAcessarDebito.setVisible(false);
    }

    @FXML
    public void onBtCadastrarCreditoAction(){
        String senha = txtSenhaCredito.getText();
        String bandeira = txtBandeiraCredito.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        Date data = null;
        try {
            data = sdf.parse(txtVencimentoCredito.getText().strip());
        } catch (ParseException e) {
            Alerts.showAlertError("Erro na Data de Nascimento", "Data de Nascimento Inválida", "Por favor, insira uma Data Válida...");
            idPaneCadastrarCredito.setVisible(false);
            return;
        }

        CartaoCredito cartaoCredito = new CartaoCredito(bandeira, senha, data);
        Application.getConta().setCartaoCredito(cartaoCredito);
        idPaneCadastrarCredito.setVisible(false);
    }

    @FXML
    public void onBtCadastrarDebitoAction(){
        String senha = txtSenhaDebito.getText();
        String bandeira = txtBandeiraDebito.getText();
        double limite = Double.parseDouble(txtLimiteDebito.getText());

        CartaoDebito cartaoDebito = new CartaoDebito(bandeira, senha, limite);
        Application.getConta().setCartaoDebito(cartaoDebito);
        idPaneCadastrarDebito.setVisible(false);
    }

    @FXML
    public void onBtDebitoAction(){
        if(Application.getConta().getCartaoDebito() == null) {
            fechaTudo();
            idPaneCadastrarDebito.setVisible(!idPaneCadastrarDebito.isVisible());
        }else{
            if(!idPaneAcessarDebito.isVisible()){
                idPaneAcessarDebito.setVisible(true);
            }else{
                idPaneAcessarDebito.setVisible(false);
            }
            idNomeCartao.setText(Application.getConta().getCliente().getNome());
            idNumeroCartao.setText(Application.getConta().getCartaoDebito().getNumero());
            idLabelLimiteDebito.setText("Limite: "+String.valueOf(Application.getConta().getCartaoDebito().getLimitePorTransacao()));
            idLabelBandeiraDebito.setText("Bandeira: "+Application.getConta().getCartaoDebito().getBandeira());
        }
    }

    public void onBtBloquearDebitoAction(){
        Application.getConta().setCartaoDebito(null);
        idPaneAcessarDebito.setVisible(false);
    }

    public void onBtBloquearCreditoAction(){
        Application.getConta().setCartaoCredito(null);
        idPaneAcessarCredito.setVisible(false);
    }

    public void onBtVoltarAction(){
        Application.changeScene("transferencia");
    }
}
