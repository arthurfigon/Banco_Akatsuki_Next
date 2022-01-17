package br.com.next.projetobanconext.model;

public class PixBO {
    private Pix pix = new Pix();
    private static int transacoesFeitas = 0;

    public boolean ativarChave(TipoChavePix chavePix, double valor, String conteudoChave, Conta conta){
        if(conta == null){
            return false;
        }
        pix.setId(transacoesFeitas);
        pix.setChavePix(chavePix);
        pix.setValor(valor);
        pix.setConteudoChave(conteudoChave);
        pix.setAtivado(true);
        pix.setConta(conta);
        transacoesFeitas++;
        return true;
    }

    public String retornaDados(){
        return "ID: "+pix.getId()+"\nConteudo da Chave: "+pix.getConteudoChave()+"\nValor da Chave: "+pix.getValor()
                +"\nStatus da Chave: Ativada"+"\nCPF do Dono da Chave: "+pix.getConta().getCliente().getCpf();
    }

    public Pix getPix() {
        return pix;
    }

    public void setPix(Pix pix) {
        this.pix = pix;
    }
}
