package br.com.next.projetobanconext.controller;

import br.com.next.projetobanconext.Application;
import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.model.ContaBO;
import br.com.next.projetobanconext.model.PixBO;
import br.com.next.projetobanconext.model.TipoChavePix;
import br.com.next.projetobanconext.utils.Alerts;
import br.com.next.projetobanconext.utils.BancoDeDados;
import br.com.next.projetobanconext.utils.Constraints;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class PixController implements Initializable {


    public TextField txtConteudoChave;
    public TextField txtValorChave;
    public Label idChaveConteudo;
    public Label idChaveValor;
    public AnchorPane idCampoCadastro;
    public boolean cadastrando = true;
    public Label idLabelValorChave;
    public TextField txtCampoPagamentoPix;
    public AnchorPane idPanePagamentoPix;

    public void onBtCadastrarChaveAction(){
        cadastrando = false;
        idCampoCadastro.setVisible(true);
        txtValorChave.setVisible(true);
        idLabelValorChave.setVisible(true);
        idChaveValor.setVisible(true);
    }

    public void onBtConsultarChaveAction(){
        cadastrando = true;
        idCampoCadastro.setVisible(true);
        idLabelValorChave.setVisible(false);
        txtValorChave.setVisible(false);
        idLabelValorChave.setVisible(false);
    }

    public void cadastraChave(){
        if(cadastrando != true) {
            TipoChavePix tipoChavePix = verificarTipoChavePix();
            if (tipoChavePix == null) {
                idCampoCadastro.setVisible(false);
                cadastrando = true;
                return;
            }
            double valor;
            try {
                valor = Double.parseDouble(txtValorChave.getText());
            } catch (Exception e) {
                Alerts.showAlertError(null, null, "Numero Invalido...");
                cadastrando = true;
                return;
            }
            String chave;
            PixBO pixBO = new PixBO();
            if (TipoChavePix.CPF.equals(tipoChavePix)) {
                chave = Application.getConta().getCliente().getCpf();
            } else if (TipoChavePix.EMAIL.equals(tipoChavePix)) {
                chave = "emailpadrão@gmail.com";
            } else if (TipoChavePix.TELEFONE.equals(tipoChavePix)) {
                chave = "21 98765-4321";
            } else {
                Random random = new Random();
                chave = String.valueOf(random.nextLong(99999999));
            }
            System.out.println(tipoChavePix);
            System.out.println(valor);
            System.out.println(chave);
            System.out.println(Application.getConta());
            if (!pixBO.ativarChave(tipoChavePix, valor, chave, Application.getConta())) {
                Alerts.showAlertError("Erro ao cadastrar chave", null, "Conta associada não existe");
                idCampoCadastro.setVisible(false);
                cadastrando = true;
                return;
            }
            switch (tipoChavePix) {
                case CPF -> Application.getConta().getChavesPix()[0] = pixBO;
                case EMAIL -> Application.getConta().getChavesPix()[1] = pixBO;
                case TELEFONE -> Application.getConta().getChavesPix()[2] = pixBO;
                case ALEATORIO -> Application.getConta().getChavesPix()[3] = pixBO;
            }
            Alerts.showAlertConfirmation("Chave Criada", null, "Chave Criada com Sucesso!");
            cadastrando = true;
        }else{
            txtValorChave.setVisible(true);
            idChaveValor.setVisible(true);
            idLabelValorChave.setVisible(true);

            if(verificarTipoChavePix().equals(TipoChavePix.CPF)){

                if(Application.getConta().getChavesPix()[0] != null) {
                    idChaveConteudo.setText(Application.getConta().getChavesPix()[0].getPix().getConteudoChave());
                    idChaveValor.setText(String.valueOf(Application.getConta().getChavesPix()[0].getPix().getValor()));
                }else{
                    Alerts.showAlertError("Chave não cadastrada", null, "Chave não cadastrada");
                    idCampoCadastro.setVisible(false);
                    return;
                }

            }else if(verificarTipoChavePix().equals(TipoChavePix.EMAIL)){

                if(Application.getConta().getChavesPix()[1] != null) {
                    idChaveConteudo.setText(Application.getConta().getChavesPix()[1].getPix().getConteudoChave());
                    idChaveValor.setText(String.valueOf(Application.getConta().getChavesPix()[1].getPix().getValor()));
                }else{
                    Alerts.showAlertError("Chave não cadastrada", null, "Chave não cadastrada");
                    idCampoCadastro.setVisible(false);
                    return;
                }

            }else if(verificarTipoChavePix().equals(TipoChavePix.TELEFONE)){

                if(Application.getConta().getChavesPix()[2] != null) {
                    idChaveConteudo.setText(Application.getConta().getChavesPix()[2].getPix().getConteudoChave());
                    idChaveValor.setText(String.valueOf(Application.getConta().getChavesPix()[2].getPix().getValor()));
                }else{
                    Alerts.showAlertError("Chave não cadastrada", null, "Chave não cadastrada");
                    idCampoCadastro.setVisible(false);
                    return;
                }

            }else if(verificarTipoChavePix().equals(TipoChavePix.ALEATORIO)){
                if(Application.getConta().getChavesPix()[3] != null) {
                    idChaveConteudo.setText(Application.getConta().getChavesPix()[3].getPix().getConteudoChave());
                    idChaveValor.setText(String.valueOf(Application.getConta().getChavesPix()[3].getPix().getValor()));
                }else{
                    Alerts.showAlertError("Chave não cadastrada", null, "Chave não cadastrada");
                    idCampoCadastro.setVisible(false);
                    return;
                }
            }else{
            }
        }
        idCampoCadastro.setVisible(false);
    }

    public TipoChavePix verificarTipoChavePix() {
        String opcao = txtConteudoChave.getText().toLowerCase(Locale.ROOT);
            switch (opcao) {
                case "cpf":
                    return TipoChavePix.CPF;
                case "email":
                    return TipoChavePix.EMAIL;
                case "telefone":
                    return TipoChavePix.TELEFONE;
                case "aleatorio":
                    return TipoChavePix.ALEATORIO;
                default:
                    Alerts.showAlertError("Opção Inválida", null, "Por favor insira uma chave válida...");
                    return null;
            }
    }

    public void onBtProcurarAction(){
        idPanePagamentoPix.setVisible(!idPanePagamentoPix.isVisible());
    }

    public void transfereDinheiro(){
        Conta contaExterna = BancoDeDados.findContabyChavePix(txtCampoPagamentoPix.getText());
        double valorAPagar = descobreValor(txtCampoPagamentoPix.getText(), contaExterna);
        if(contaExterna == null || valorAPagar > Application.getConta().getSaldo()){
            Alerts.showAlertError("Transferência Inválida", null, "Erro Ao Realizar a Transferência");
            idPanePagamentoPix.setVisible(false);
        }else{
            ContaBO contaBO = new ContaBO(Application.getConta());
            contaBO.sacar(valorAPagar);
            contaBO.setConta(contaExterna);
            contaBO.depositar(valorAPagar);
            Alerts.showAlertConfirmation("Pagamento feito com Sucesso",
                    contaExterna.getCliente().getNome()+" recebeu seu pagamento!",
                    "Pagamento no valor de: R$"+valorAPagar);
            idPanePagamentoPix.setVisible(false);
        }
    }

    public double descobreValor(String chave, Conta conta){
        if(conta.getChavesPix()[0] != null &&
                conta.getChavesPix()[0].getPix().getConteudoChave().equals(chave)) {
            return conta.getChavesPix()[0].getPix().getValor();
        }else if(conta.getChavesPix()[1] != null &&
                conta.getChavesPix()[1].getPix().getConteudoChave().equals(chave)) {
            return conta.getChavesPix()[1].getPix().getValor();
        }else if(conta.getChavesPix()[2] != null &&
                conta.getChavesPix()[2].getPix().getConteudoChave().equals(chave)) {
            return conta.getChavesPix()[2].getPix().getValor();
        }else if(conta.getChavesPix()[3] != null &&
                conta.getChavesPix()[3].getPix().getConteudoChave().equals(chave)) {
            return conta.getChavesPix()[3].getPix().getValor();
        }else{
            return -1;
        }

    }

    public void onBtVoltarAction(){
        Application.changeScene("transferencia");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Constraints.setTextFieldLetters(txtConteudoChave);
        Constraints.setTextFieldDouble(txtValorChave);
    }
}
