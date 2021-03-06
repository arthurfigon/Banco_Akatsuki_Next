package br.com.next.projetobanconext.utils;

import br.com.next.projetobanconext.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BancoDeDados {
    private static long contasCriadas = 0;
    public static Map<Long, Conta> bancoDeDados = new HashMap<>();


    public static void insereContasPadrao(){
        Endereco endereco = new Endereco("1", "1","1","1", "1","1");
        Date data = new Date(System.currentTimeMillis());

        ClienteBO clienteBO1 = new ClienteBO("11111111111", "Gabriela", data, endereco);
        Conta contaBO = new ContaBO("1234567", clienteBO1.getCliente(), TipoConta.CORRENTE).getConta();

        ClienteBO clienteBO2 = new ClienteBO("22222222222", "Gabriel", data, endereco);
        Conta contaBO2 = new ContaBO("1234567", clienteBO2.getCliente(), TipoConta.POUPANCA).getConta();
    }

    public static Conta findContabyChavePix(String chave){
        for(Map.Entry<Long, Conta> conta : BancoDeDados.bancoDeDados.entrySet()){
            if(conta.getValue().getChavesPix()[0] != null &&
                    conta.getValue().getChavesPix()[0].getPix().getConteudoChave().equals(chave)) {
                return conta.getValue();
            }else if(conta.getValue().getChavesPix()[1] != null &&
                    conta.getValue().getChavesPix()[1].getPix().getConteudoChave().equals(chave)) {
                return conta.getValue();
            }else if(conta.getValue().getChavesPix()[2] != null &&
                    conta.getValue().getChavesPix()[2].getPix().getConteudoChave().equals(chave)) {
                return conta.getValue();
            }else if(conta.getValue().getChavesPix()[3] != null &&
                    conta.getValue().getChavesPix()[3].getPix().getConteudoChave().equals(chave)) {
                return conta.getValue();
            }
        }
        return null;
    }

    public static long getContasCriadas() {
        return contasCriadas;
    }

    private static void aumentaContasCriadas(){
        contasCriadas++;
    }

    public static void insereConta(Conta conta){
        if(conta == null){
            return;
        }
        BancoDeDados.bancoDeDados.put(contasCriadas, conta);
        aumentaContasCriadas();
    }

    public static Conta findContaByCPF(String cpf, TipoConta tipoConta){
        Long idEncontrado = findIdContaByCPF(cpf, tipoConta);
        if(idEncontrado == null){
            return null;
        }
        return BancoDeDados.bancoDeDados.get(idEncontrado);
    }

    private static Long findIdContaByCPF(String cpf, TipoConta tipoConta){
        for(Map.Entry<Long, Conta> conta : BancoDeDados.bancoDeDados.entrySet()){
            if(conta.getValue().getCliente().getCpf().equals(cpf) &&
                    conta.getValue().getTipoConta().equals(tipoConta)) {
                return conta.getKey();
            }
        }
        return null;
    }

    public static ArrayList<Conta> returnTodasContas(){
        ArrayList<Conta> arrayContas = new ArrayList<>();
        for(Map.Entry<Long, Conta> conta : BancoDeDados.bancoDeDados.entrySet()){
            arrayContas.add(conta.getValue());
        }
        return arrayContas;
    }
}
