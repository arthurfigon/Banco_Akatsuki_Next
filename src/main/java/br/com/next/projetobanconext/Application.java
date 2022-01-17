package br.com.next.projetobanconext;

import br.com.next.projetobanconext.model.*;
import br.com.next.projetobanconext.utils.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private static Stage stage;

    private static Scene sceneInicial;
    private static Scene sceneTransferencia;
    private static Scene sceneRegistrar;
    private static Scene scenePix;
    private static Scene sceneCartoes;
    private static Conta conta;

    public static Conta getConta() {
        return conta;
    }

    public static void setConta(Conta conta) {
        Application.conta = conta;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Application.stage = stage;
        Application.stage.setTitle("Next");

        FXMLLoader fxmlLoaderInicial = new FXMLLoader(Application.class.getResource("view_inicial.fxml"));
        sceneInicial = new Scene(fxmlLoaderInicial.load());

        FXMLLoader fxmlLoaderTransferencia = new FXMLLoader(Application.class.getResource("view_menulogado.fxml"));
        sceneTransferencia = new Scene(fxmlLoaderTransferencia.load());

        FXMLLoader fxmlLoaderRegistrar = new FXMLLoader(Application.class.getResource("view_registrar.fxml"));
        sceneRegistrar = new Scene(fxmlLoaderRegistrar.load());

        FXMLLoader fxmlLoaderPix = new FXMLLoader(Application.class.getResource("view_pix.fxml"));
        scenePix = new Scene(fxmlLoaderPix.load());

        FXMLLoader fxmlLoaderCartoes = new FXMLLoader(Application.class.getResource("view_cartoes.fxml"));
        sceneCartoes = new Scene(fxmlLoaderCartoes.load());

        stage.setTitle("Web Banking Next");
        stage.setScene(sceneInicial);
        stage.show();
    }

    public static void changeScene(String scene){
        switch (scene){
            case "main" -> stage.setScene(sceneInicial);
            case "registrar" -> stage.setScene(sceneRegistrar);
            case "transferencia" -> stage.setScene(sceneTransferencia);
            case "pix" -> stage.setScene(scenePix);
            case "cartoes" -> stage.setScene(sceneCartoes);
            default -> {break;}
        }
    }

    public static void main(String[] args) {
        BancoDeDados.insereContasPadrao();
        launch();
    }
}