package br.com.next.projetobanconext.model;

import java.util.Date;
import java.util.List;

public class Apolice {

    private static long numeroApolices = 0L;

    private String id;
    private double valorApolice;
    private List<String> descricaoCondicoes;
    private Seguro seguro = new Seguro();
    private Date DataAssinatura;
    private Date DataCarencia;

    public Apolice(String id, double valorApolice, List<String> descricaoCondicoes, Seguro seguro, Date dataAssinatura, Date dataCarencia) {
        this.id = id;
        this.valorApolice = valorApolice;
        this.descricaoCondicoes = descricaoCondicoes;
        this.seguro = seguro;
        DataAssinatura = dataAssinatura;
        DataCarencia = dataCarencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValorApolice() {
        return valorApolice;
    }

    public void setValorApolice(double valorApolice) {
        this.valorApolice = valorApolice;
    }

    public static long getNumeroApolices() {
        return numeroApolices;
    }

    public static void setNumeroApolices(long numeroApolices) {
        Apolice.numeroApolices = numeroApolices;
    }

    public List<String> getDescricaoCondicoes() {
        return descricaoCondicoes;
    }

    public void setDescricaoCondicoes(List<String> descricaoCondicoes) {
        this.descricaoCondicoes = descricaoCondicoes;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public Date getDataAssinatura() {
        return DataAssinatura;
    }

    public void setDataAssinatura(Date dataAssinatura) {
        DataAssinatura = dataAssinatura;
    }

    public Date getDataCarencia() {
        return DataCarencia;
    }

    public void setDataCarencia(Date dataCarencia) {
        DataCarencia = dataCarencia;
    }
}
