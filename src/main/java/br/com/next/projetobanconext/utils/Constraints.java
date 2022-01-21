package br.com.next.projetobanconext.utils;

import javafx.scene.control.TextField;

public class Constraints {

 // [^[a-z][A-Z]*] só letras
    public static void setTextFieldInteger(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("")){
                if(!newValue.matches("[0-9]*")){
                    txt.setText(oldValue);
                }
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
            if(!newValue.matches("")){
                if(!(newValue.matches("\\d++([.])?(\\d++)?"))){
                    txt.setText(oldValue);
                }
            }
        });
    }
    public static void setTextFieldLetters(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("")){
                if(!newValue.matches("[a-zA-Z]*([\\\\ ]?[a-zA-Z]*)*")){
                    txt.setText(oldValue);
                }
            }
        });
    }

    public static void setTextFieldDate(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("")){
                if(!newValue.matches("\\d{1,2}([\\\\/])?([\\d]){0,2}([\\\\/])?([\\d]){0,4}")){
                    txt.setText(oldValue);
                }
            }
        });
    }
}
