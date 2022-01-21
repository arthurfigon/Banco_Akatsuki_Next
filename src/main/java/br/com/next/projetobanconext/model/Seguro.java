package br.com.next.projetobanconext.model;

import java.util.List;

public class Seguro {
    private String id;
    private String nome;
    private List<String> regras;
    private double valorAno;

    public Seguro() {
    }

    public Seguro(String id, String nome, List<String> regras, double valor) {
        this.id = id;
        this.nome = nome;
        this.regras = regras;
        this.valorAno = valor;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public double getValorAno() {
        return valorAno;
    }

    public void setValorAno(double valorAno) {
        this.valorAno = valorAno;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getRegras() {
        return regras;
    }

    public void setRegras(List<String> regras) {
        this.regras = regras;
    }
}
