package br.com.next.projetobanconext;

import br.com.next.projetobanconext.model.Conta;
import br.com.next.projetobanconext.utils.BancoDeDados;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private static Stage stage;

    private static Scene sceneInicial;
    private static Scene sceneTransferencia;
    private static Scene sceneRegistrar;
    private static Conta conta;

    // Interface Gráfica -> View

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;
        Application.stage.setTitle("Next");

        FXMLLoader fxmlLoaderInicial = new FXMLLoader(Application.class.getResource("viewInicial.fxml"));
        sceneInicial = new Scene(fxmlLoaderInicial.load(), 800, 800);

        FXMLLoader fxmlLoaderTransferencia = new FXMLLoader(Application.class.getResource("view_transferencia.fxml"));
        sceneTransferencia = new Scene(fxmlLoaderTransferencia.load(), 400, 400);

        FXMLLoader fxmlLoaderRegistrar = new FXMLLoader(Application.class.getResource("view_registrar.fxml"));
        sceneRegistrar = new Scene(fxmlLoaderRegistrar.load());

        stage.setTitle("Web Banking Next");
        stage.setScene(sceneInicial);
        stage.show();
    }

    public static void changeScene(String scene){
        switch (scene){
            case "main" -> stage.setScene(sceneInicial);
            case "registrar" -> stage.setScene(sceneRegistrar);
            case "transferencia" -> stage.setScene(sceneTransferencia);
            default -> {break;}
        }
    }

    public static void main(String[] args) {
        BancoDeDados.insereContasPadrao();
        launch();
    }
}