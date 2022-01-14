package br.com.next.projetobanconext.model;

public enum TipoChavePix {
    CPF(0), EMAIL(1), TELEFONE(2), ALEATORIO(3);

    private int id;

    TipoChavePix(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
