package br.com.next.projetobanconext.utils;

import javafx.scene.control.TextField;

public class Constraints {

 // [^[a-z][A-Z]*] só letras
    public static void setTextFieldInteger(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && !(newValue.matches("[0-9]++"))){
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldMaxLength(TextField txt, int max){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && newValue.length() > max){
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldDouble(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && !(newValue.matches("\\d++([.]{1}){0,1}(\\d++){0,1}"))){
                txt.setText(oldValue);
            }
        });
    }
}
