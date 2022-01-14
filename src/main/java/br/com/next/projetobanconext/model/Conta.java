package br.com.next.projetobanconext.model;


import java.util.Date;

public class Conta{
    private long idConta;
    private double saldo;
    private String numero;
    private String senha;
    private Cliente cliente = new Cliente();
    private TipoConta tipoConta;
    private boolean isAdmin = false;
    private PixBO[] chavesPix = new PixBO[4];
    private double taxa;
    private Date dataTaxa = new Date();

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public Date getDataTaxa() {
        return dataTaxa;
    }

    public void setDataTaxa(Date dataTaxa) {
        this.dataTaxa = dataTaxa;
    }

    public PixBO[] getChavesPix() {
        return chavesPix;
    }

    public void setChavesPix(PixBO chavesPix, int index) {
        this.chavesPix[index] = chavesPix;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
