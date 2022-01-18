package br.com.next.projetobanconext.model;

public enum TipoConta {

    CORRENTE(1), POUPANÇA(2);

    private int id;

    TipoConta(int id){
            this.id = id;

    }
    public int getId() {
            return id;
        }
}