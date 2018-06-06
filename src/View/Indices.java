package View;

import Model.TipoConta;
import java.util.Scanner;

/**
 * Esta classe implementa os métodos da interface do cliente.
 *
 * @author Leandro Pereira Sampaio.
 */
public class Indices {

    private Scanner entrada = new Scanner(System.in);
    private String numConta, senha, confirmacaoSenha, cpfCnpj;
    private int opcao, aux;
    private float valor;

    /**
     * Método responsável por pegar os dados digitados pelo usuário referente ao
     * login.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String fazerLogin() {
        entrada = new Scanner(System.in);
        System.out.println("Digite seu cpf/cnpj:");
        cpfCnpj = entrada.nextLine();
        System.out.println("Digite o número da conta:");
        numConta = entrada.nextLine();
        System.out.println("Digite sua senha:");
        senha = entrada.nextLine();
        return "login;" + numConta + ";" + senha + ";" + cpfCnpj + ";";
    }

    /**
     * Método responsável por pegar os dados digitados pelo usuário referente ao
     * cadastro.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String cadastrar() {
        System.out.println("CRIAR CONTA");
        System.out.println("1- CORRENTE (PESSOA FÍSICA)");
        System.out.println("2- CORRENTE (PESSOA JURÍDICA)");
        System.out.println("3- POUPANÇA (PESSOA FÍSICA)");
        System.out.println("4- POUPANÇA (PESSOA JURÍDICA)");
        System.out.print("OPCÃO: ");
        aux = entrada.nextInt();
        entrada.nextLine();
        switch (aux) {
            case 1:
                System.out.println("DIGITE SEU CPF:");
                String cpf = entrada.nextLine();
                System.out.println("DIGITE SEU NOME:");
                String nome = entrada.nextLine();
                System.out.println("DIGITE SUA SENHA:");
                String senha1 = entrada.nextLine();
                System.out.println("DIGITE NOVAMENTE SUA SENHA:");
                confirmacaoSenha = entrada.nextLine();
                if (confirmacaoSenha.equals(senha1)) {
                    return "cadastroPF;" + TipoConta.corrente + ";" + cpf + ";" + nome + ";" + senha1 + ";";
                } else {
                    System.out.println("SENHAS NÃO CONFEREM! TENTE NOVAMENTE...");
                }
            case 2:
                System.out.println("DIGITE SEU CNPJ:");
                String cnpj = entrada.nextLine();
                System.out.println("DIGITE SEU NOME:");
                nome = entrada.nextLine();
                System.out.println("DIGITE SUA SENHA:");
                senha1 = entrada.nextLine();
                System.out.println("DIGITE NOVAMENTE SUA SENHA:");
                confirmacaoSenha = entrada.nextLine();
                if (confirmacaoSenha.equals(senha1)) {
                    return "cadastroPJ;" + TipoConta.corrente + ";" + cnpj + ";" + nome + ";" + senha1 + ";";
                } else {
                    System.out.println("SENHAS NÃO CONFEREM! TENTE NOVAMENTE...");
                }
            case 3:
                System.out.println("DIGITE SEU CPF:");
                cpf = entrada.nextLine();
                System.out.println("DIGITE SEU NOME:");
                nome = entrada.nextLine();
                System.out.println("DIGITE SUA SENHA:");
                senha1 = entrada.nextLine();
                System.out.println("DIGITE NOVAMENTE SUA SENHA:");
                confirmacaoSenha = entrada.nextLine();
                if (confirmacaoSenha.equals(senha1)) {
                    return "cadastroPF;" + TipoConta.poupanca + ";" + cpf + ";" + nome + ";" + senha1 + ";";
                } else {
                    System.out.println("SENHAS NÃO CONFEREM! TENTE NOVAMENTE...");
                }
            case 4:
                System.out.println("DIGITE SEU CNPJ:");
                cnpj = entrada.nextLine();
                System.out.println("DIGITE SEU NOME:");
                nome = entrada.nextLine();
                System.out.println("DIGITE SUA SENHA:");
                senha1 = entrada.nextLine();
                System.out.println("DIGITE NOVAMENTE SUA SENHA:");
                confirmacaoSenha = entrada.nextLine();
                if (confirmacaoSenha.equals(senha1)) {
                    return "cadastroPJ;" + TipoConta.poupanca + ";" + cnpj + ";" + nome + ";" + senha1 + ";";
                } else {
                    System.out.println("SENHAS NÃO CONFEREM! TENTE NOVAMENTE...");
                }
            default:
                System.out.println("OPÇÃO INVÁLIDA! TENTE NOVAMENTE...");
                break;
        }
        return loginCadastrar();
    }

    /**
     * Método responsável por pegar os dados digitados pelo usuário referente as
     * opções oferecidas pelo banco.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String loginCadastrar() {
        System.out.println("_____________________________________________");
        System.out.println("******* BEM-VINDO AO BANCO COOPERATIVO ******");
        System.out.println("_____________________________________________");
        System.out.println("ESCOLHA UMA DAS OPÇÕES:");
        System.out.println("1- FAZER LOGIN");
        System.out.println("2- CADASTRAR CONTA");
        System.out.println("3- CADASTRAR PESSOA NA CONTA");
        System.out.println("4- SAIR DO SISTEMA");
        System.out.print("OPCÃO: ");
        opcao = entrada.nextInt();
        return "loginCadastrar;" + opcao;
    }

    /**
     * Método responsável por pegar os dados digitados pelo usuário referente as
     * opções.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String opcoes() {
        System.out.println("\n***** Bem-Vindo *****");
        System.out.println("ESCOLHA UMA DAS OPÇÕES:");
        System.out.println("1- VER SALDO");
        System.out.println("2- FAZER TRANSFERÊNCIA");
        System.out.println("3- FAZER DEPÓSITO");
        System.out.println("4- MOSTRAR HISTÓRICO DE TRANSAÇÕES");
        System.out.println("5- SAIR DA CONTA");
        System.out.print("OPCÃO: ");
        opcao = entrada.nextInt();

        switch (opcao) {
            case 1:
                return "saldo;";
            case 2:
                return fazerTransferencia();
            case 3:
                return fazerDeposito();
            case 4:
                return "historico;";
            case 5:
                return "sair;";
            default:
                System.out.println("OPÇÃO INVÁLIDA! ESCOLHA UMA DAS 4 OPÇÕES!");
        }

        return "NULL";
    }

    /**
     * Método responsável por pegar os dados digitados pelo usuário referente a
     * uma transferência.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String fazerTransferencia() {
        String tipoConta;
        System.out.println("O TRANSFERÊNCIA SERÁ PARA A CONTA");
        System.out.println("1- CORRENTE");
        System.out.println("2- POUPANÇA");
        System.out.print("OPCÃO: ");
        aux = entrada.nextInt();

        if (aux == 1) {
            tipoConta = "corrente";
        } else {
            tipoConta = "poupança";
        }
        entrada.nextLine();
        System.out.println("Número da conta para a transferência: ");
        numConta = entrada.nextLine();
        System.out.println("Valor: ");
        valor = entrada.nextFloat();

        return "transferencia;" + tipoConta + ";" + numConta + ";" + valor + ";";
    }

    /**
     * Método responsável por pegar os dados digitados pelo usuário referente a
     * um depósito.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String fazerDeposito() {
        System.out.println("\n*** O DEPÓSITO SERÁ NA CONTA *****");
        System.out.println("1- PRÓPRIA CONTA CORRENTE");
        System.out.println("2- PRÓPRIA CONTA POUPANÇA");
        System.out.println("3- CONTA CORRENTE DE OUTROS");
        System.out.println("4- CONTA POUPANÇA DE OUTROS");
        System.out.print("OPCÃO: ");
        opcao = entrada.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Valor: ");
                valor = entrada.nextFloat();
                return "deposito;" + opcao + ";" + valor + ";";
            case 2:
                System.out.println("Valor: ");
                valor = entrada.nextFloat();
                return "deposito;" + opcao + ";" + valor + ";";
            case 3:
                entrada.nextLine();
                System.out.println("Número da conta para a depósito na conta CORRENTE");
                numConta = entrada.nextLine();
                System.out.println("Valor: ");
                valor = entrada.nextFloat();
                return "deposito;" + opcao + ";" + numConta + ";" + valor + ";";
            case 4:
                entrada.nextLine();
                System.out.println("Número da conta para a depósito na conta CORRENTE");
                numConta = entrada.nextLine();
                System.out.println("Valor: ");
                valor = entrada.nextFloat();
                return "deposito;" + opcao + ";" + numConta + ";" + valor + ";";
            default:
                System.out.println("NÚMERO INVÁLIDO! TENTE NOVAMENTE...");
                break;
        }
        return opcoes();
    }

    /**
     * Método responsável por pegar os dados digitados pelo usuário ao cadastro
     * de uma conta conjunta.
     *
     * return String - Identificador do método juntamente com os informações
     * digitadas.
     */
    public String cadastrarContaConjunta() {
        entrada.nextLine();
        System.out.println("Número da Conta do titular:");
        numConta = entrada.nextLine();
        System.out.println("Senha do titular da Conta:");
        senha = entrada.nextLine();
        System.out.println("DIGITE SEU CPF:");
        String cpf = entrada.nextLine();
        System.out.println("DIGITE SEU NOME:");
        String nome = entrada.nextLine();
        return "cadastrarContaConjunta;" + numConta + ";" + senha + ";"
                + cpf + ";" + nome + ";";
    }
}
