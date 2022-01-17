package br.com.next.projetobanconext.model;

import java.util.Random;

public abstract class Cartao {
    private static String totalCartoes = "0";
    private String id;
    private String numero;
    private String bandeira;
    private String senha;
    private boolean isAtivo = false;

    public Cartao(String bandeira, String senha, boolean isAtivo) {
        this.id = totalCartoes;
        this.numero = setNumeroRandom();
        this.bandeira = bandeira;
        this.senha = senha;
        this.isAtivo = isAtivo;
        totalCartoes = String.valueOf(Integer.parseInt(totalCartoes)+1);
    }

    private String setNumeroRandom(){
        Random random = new Random();
        String novoNumero = "";
        int numeroDaVez = 0;
        for(int i = 0; i < 4; i++){
            numeroDaVez = random.nextInt(10000);
            if(numeroDaVez<10){
                novoNumero += "000";
            }else if(numeroDaVez<100){
                novoNumero += "00";
            }else if(numeroDaVez<1000){
                novoNumero += "0";
            }
            novoNumero += String.valueOf(numeroDaVez);
            novoNumero += " ";
        }
        return novoNumero.strip();
    }

    public static String getTotalCartoes() {
        return totalCartoes;
    }

    public static void setTotalCartoes(String totalCartoes) {
        Cartao.totalCartoes = totalCartoes;
    }

    public String getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }
}
