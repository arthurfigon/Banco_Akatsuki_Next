package br.com.next.projetobanconext.utils;

import br.com.next.projetobanconext.model.ClienteBO;
import br.com.next.projetobanconext.model.ContaBO;
import br.com.next.projetobanconext.model.Endereco;
import br.com.next.projetobanconext.model.TipoConta;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BancoDeDados {
    private static long contasCriadas = 0;
    public static Map<Long, ContaBO> bancoDeDados = new HashMap<>();


    public static void insereContasPadrao(){
        Endereco endereco = new Endereco("1", "1","1","1", "1","1");
        Date data = new Date(System.currentTimeMillis());

        ClienteBO clienteBO1 = new ClienteBO("11111111111", "Gabriela", data, endereco);
        ContaBO contaBO = new ContaBO("1234567", clienteBO1.getCliente(), TipoConta.CORRENTE);
        insereConta(contaBO);
    }

    public static long getContasCriadas() {
        return contasCriadas;
    }

    private static void aumentaContasCriadas(){
        contasCriadas++;
    }

    public static void insereConta(ContaBO conta){
        if(conta == null){
            return;
        }
        BancoDeDados.bancoDeDados.put(contasCriadas, conta);
        aumentaContasCriadas();
    }

    public static ContaBO findContaByCPF(String cpf, TipoConta tipoConta){
        Long idEncontrado = findIdConta(cpf, tipoConta);
        if(idEncontrado == null){
            return null;
        }
        return BancoDeDados.bancoDeDados.get(idEncontrado);
    }

    private static Long findIdConta(String cpf, TipoConta tipoConta){
        for(var conta : BancoDeDados.bancoDeDados.entrySet()){
            if(conta.getValue().getConta().getCliente().getCpf().equals(cpf) &&
                    conta.getValue().getConta().getTipoConta().equals(tipoConta)) {
                return conta.getKey();
            }
        }
        return null;
    }

    public static ArrayList<ContaBO> returnTodasContas(){
        ArrayList<ContaBO> arrayContas = new ArrayList<>();
        for(var conta : BancoDeDados.bancoDeDados.entrySet()){
            arrayContas.add(conta.getValue());
        }
        return arrayContas;
    }
}
