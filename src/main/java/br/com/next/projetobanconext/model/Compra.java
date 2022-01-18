package br.com.next.projetobanconext.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Compra {

    private String nomeProduto;
    private Date compra;
    private double valor;

    public Compra(String nomeProduto, double valor) {
        Calendar calendario = Calendar.getInstance();
        Date dataCompra = calendario.getTime();
        this.nomeProduto = nomeProduto;
        this.compra = dataCompra;
        this.valor = valor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Date getCompra() {
        return compra;
    }

    public void setCompra(Date compra) {
        this.compra = compra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataCompra = fmt.format(compra);
        return "Data da Compra: " + dataCompra +
                ", Valor: " + valor;
    }
}
