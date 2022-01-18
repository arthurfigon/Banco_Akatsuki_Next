package br.com.next.projetobanconext.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CartaoCredito extends Cartao{
    private double limite = 1000;
    private double valorFatura = 0;
    private Date dataVencimento;
    private List<Compra> compras = new ArrayList<>();


    public CartaoCredito(String bandeira, String senha, Date dataVencimento) {
        super(bandeira, senha, true);
        this.limite = limite;
        this.valorFatura = valorFatura;
        this.dataVencimento = dataVencimento;
    }

    public String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        return sdf.format(this.dataVencimento);
    }

    public Date getDateAddOneMonth(){
        Calendar calendarAplicacaoTaxa = Calendar.getInstance();
        calendarAplicacaoTaxa.add(Calendar.MONTH, 1);
        return calendarAplicacaoTaxa.getTime();
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public void addCompra(Compra compra){
        valorFatura += compra.getValor();
        compras.add(compra);
    }

    public String toStringCompras(){
        String resultado = "";
        for (Compra compra : compras){
            resultado += compra.toString();
            resultado += "\n";
        }
        return resultado;
    }

    public boolean pagarFatura(Conta conta){
        if (conta.getSaldo() < valorFatura){
            return false;
        }
        conta.setSaldo(conta.getSaldo() - valorFatura);
        this.valorFatura = 0;
        return true;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(double valorFatura) {
        this.valorFatura = valorFatura;
    }
}
