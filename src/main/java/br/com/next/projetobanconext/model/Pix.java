package br.com.next.projetobanconext.model;

public class Pix {
    private int id;
    private TipoChavePix chavePix;
    private double valor;
    private String conteudoChave;
    private boolean isAtivado;
    private Conta conta;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoChavePix getChavePix() {
        return chavePix;
    }

    public void setChavePix(TipoChavePix chavePix) {
        this.chavePix = chavePix;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getConteudoChave() {
        return conteudoChave;
    }

    public void setConteudoChave(String conteudoChave) {
        this.conteudoChave = conteudoChave;
    }

    public boolean isAtivado() {
        return isAtivado;
    }

    public void setAtivado(boolean ativado) {
        isAtivado = ativado;
    }
}
