package br.com.next.projetobanconext.model;

import br.com.next.projetobanconext.utils.BancoDeDados;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ContaBO{

    private Conta conta = new Conta();

    public ContaBO(String senha, Cliente cliente, TipoConta tipoConta) {
        cadastrarConta(senha,cliente,tipoConta);

        Calendar calendarAplicacaoTaxa = Calendar.getInstance();
        calendarAplicacaoTaxa.add(Calendar.MONTH, 1);
        Date dataAplicacaoTaxa = calendarAplicacaoTaxa.getTime();
        conta.setDataTaxa(dataAplicacaoTaxa);
    }

    public ContaBO(Conta conta) {
        this.conta = conta;
    }

    public String consultarConta(){
        return "Id: "+conta.getIdConta()+"\nCPF: "+conta.getCliente().getCpf()+"\nNome: "+conta.getCliente().getNome()+
                "\nSaldo: R$ "+conta.getSaldo()+ "\nTipo do Cliente: "+conta.getCliente().getTipo();
    }

    public boolean transferir(double valor, ContaBO externa){
        if(conta.getTipoConta().equals(externa.getConta().getTipoConta())){
            if(conta.getSaldo() < valor){
                return false;
            }else{
                externa.getConta().setSaldo(externa.getConta().getSaldo() + valor);
                conta.setSaldo(conta.getSaldo()- valor);

                if (externa.getConta().getSaldo() < 5000){
                    externa.getConta().getCliente().setTipo(TipoCliente.COMUM);
                } else if(externa.getConta().getSaldo() < 15000){
                    externa.getConta().getCliente().setTipo(TipoCliente.SUPER);
                }else{
                    externa.getConta().getCliente().setTipo(TipoCliente.PREMIUM);
                }

                if (conta.getSaldo() < 5000){
                    conta.getCliente().setTipo(TipoCliente.COMUM);
                } else if(conta.getSaldo() < 15000){
                    conta.getCliente().setTipo(TipoCliente.SUPER);
                }else{
                    conta.getCliente().setTipo(TipoCliente.PREMIUM);
                }
                return true;
            }
        }else{
            if(conta.getSaldo() < (valor + 5.6)) {
            return false;
            }else{
                externa.getConta().setSaldo(externa.getConta().getSaldo() + valor);
                conta.setSaldo(conta.getSaldo()-(valor + 5.6));

                if (externa.getConta().getSaldo() < 5000){
                    externa.getConta().getCliente().setTipo(TipoCliente.COMUM);
                } else if(externa.getConta().getSaldo() < 15000){
                    externa.getConta().getCliente().setTipo(TipoCliente.SUPER);
                }else{
                    externa.getConta().getCliente().setTipo(TipoCliente.PREMIUM);
                }

                if (conta.getSaldo() < 5000){
                    conta.getCliente().setTipo(TipoCliente.COMUM);
                } else if(conta.getSaldo() < 15000){
                    conta.getCliente().setTipo(TipoCliente.SUPER);
                }else{
                    conta.getCliente().setTipo(TipoCliente.PREMIUM);
                }
                return true;
            }
        }
    }

    public void depositar(double valor){
        conta.setSaldo(conta.getSaldo()+valor);
        if (conta.getSaldo() < 5000){
            conta.getCliente().setTipo(TipoCliente.COMUM);
        } else if(conta.getSaldo() < 15000){
            conta.getCliente().setTipo(TipoCliente.SUPER);
        }else{
            conta.getCliente().setTipo(TipoCliente.PREMIUM);
        }
    }

    private void cadastrarConta(String senha, Cliente cliente, TipoConta tipoConta){
        conta.setIdConta(BancoDeDados.getContasCriadas());
        conta.setSaldo(0.0);
        conta.setSenha(senha);
        conta.setCliente(cliente);
        conta.setTipoConta(tipoConta);

        if(tipoConta.equals(TipoConta.CORRENTE)){
            conta.setTaxa(0.0045);
        }else{
            conta.setTaxa(0.0003);
        }
        BancoDeDados.insereConta(this.conta);
    }

    public void sacar(double valor){
        this.conta.setSaldo(conta.getSaldo() - valor);
    }

    public static boolean checkSenhaValida(String senha){
        if(senha.contains(" ") || (senha.length() < 6 || senha.length() > 16)){
            return false;
        }
        return true;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void aplicaTaxa(){
        if(conta.getTipoConta() != null){
            if(conta.getDataTaxa().before(new Date())){
                if(conta.getTipoConta().equals(TipoConta.CORRENTE)){
                    conta.setSaldo(conta.getSaldo() - (conta.getSaldo() * conta.getTaxa()));
                }else{
                    conta.setSaldo(conta.getSaldo() + (conta.getSaldo() * conta.getTaxa()));
                }
                Calendar calendarAplicacaoTaxa = Calendar.getInstance();
                calendarAplicacaoTaxa.add(Calendar.MONTH, 1);
                Date dataAplicacaoTaxa = calendarAplicacaoTaxa.getTime();
                conta.setDataTaxa(dataAplicacaoTaxa);
            }
        }
    }

    @Override
    public String toString() {
        return "Nome: " + conta.getCliente().getNome()+
                " CPF: " + conta.getCliente().getCpf() +
                " Conta: " + conta.getTipoConta().name();
    }
}
