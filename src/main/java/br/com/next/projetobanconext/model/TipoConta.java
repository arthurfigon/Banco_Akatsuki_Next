package br.com.next.projetobanconext.model;

public enum TipoConta {

    CORRENTE(1), POUPANCA(2);

    private final int id;

    TipoConta(int id){
            this.id = id;

    }
    public int getId() {
            return id;
        }
}