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

    public Pix getPix() {
        return pix;
    }

}
