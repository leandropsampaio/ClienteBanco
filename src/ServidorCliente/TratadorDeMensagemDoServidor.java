package ServidorCliente;

import View.Indices;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Esta classe implementa o recebimento e envio de mensagens para o servidor.
 *
 * @author Leandro Pereira Sampaio
 */
public class TratadorDeMensagemDoServidor implements Runnable {

    private Socket servidor;
    private Indices indice;

    /**
     * Construtor da classe, responsável por inicializar os atributos.
     *
     * @param servidor - Socket de comunicação do servidor.
     *
     */
    public TratadorDeMensagemDoServidor(Socket servidor) {
        this.servidor = servidor;
        this.indice = new Indices();
    }

    @Override
    public void run() {
        try (Scanner s = new Scanner(servidor.getInputStream())) { //Receber Mensagem do servidor

            PrintStream saida = new PrintStream(servidor.getOutputStream()); //Enviar Mensagem para o servidor
            String mensagem, loginMsg;
            String[] login;

            while (true) {
                /* 
                    Será mostrado para o cliente a tela inicial, onde ele poderá escolher entre 
                    quatro opções: "FAZER LOGIN", "CADASTRAR CONTA", "CADASTRAR PESSOA NA CONTA"
                    e "SAIR DO SISTEMA".
                 */
                mensagem = indice.loginCadastrar();
                String[] dados = mensagem.split(";");

                switch (dados[1]) {
                    /* 
                        Se o cliente escolher a primeria opção da tela inicial, ele
                        será direcionado para o login de sua conta.
                     */
                    case "1":
                        loginMsg = indice.fazerLogin();
                        saida.println(loginMsg);
                        login = loginMsg.split(";");

                        if (s.hasNext() && !"null".equals(s.next())) {
                            System.out.println("LOGIN COM SUCESSO!");

                            OUTER:
                            while (true) {
                                /**
                                 * O split irá separar a mensagem recebida em um array de strings,
                                 * para posteriormente ser comparada no "switch case".
                                 */
                                mensagem = indice.opcoes();
                                dados = mensagem.split(";");
                                saida.println(mensagem + login[1] + ";" + login[2] + ";" + login[3]);

                                while (s.hasNext()) {
                                    switch (dados[0]) {
                                        case "saldo":
                                            System.out.println("SEU SALDO É: " + s.next());
                                            break;
                                        case "transferencia":
                                            if (s.next().equals("true")) {
                                                System.out.println("TRANSFERENCIA EFETUADA COM SUCESSO!");
                                            } else {
                                                System.out.println("TRANSFERENCIA NÃO EFETUADA! TENTE NOVAMENTE...");
                                            }
                                            break;
                                        case "deposito":
                                            if (s.next().equals("true")) {
                                                System.out.println("DEPOSITO EFETUADO COM SUCESSO!");
                                            } else {
                                                System.out.println("DEPOSITO NÃO EFETUADO! TENTE NOVAMENTE...");
                                            }
                                            break;
                                        case "historico":
                                            dados = s.next().split(";");
                                            System.out.println("           ___________HISTÓRICO_________");
                                            for (int i = 0; i < dados.length; i++) {
                                                if (!"null".equals(dados[i]) && dados[i].equals("deposito")) {
                                                    System.out.print("Transação: " + dados[i]);
                                                    System.out.print(" | Conta: " + dados[++i]);
                                                    System.out.println(" | Valor: " + dados[++i]);
                                                    System.out.println(" | CPF de quem fez a operação: " + dados[++i]);
                                                } else if (!"null".equals(dados[i]) && dados[i].equals("transferencia")) {
                                                    System.out.print("Transação: " + dados[i]);
                                                    System.out.print(" | Conta: " + dados[++i]);
                                                    System.out.print(" | Para a Conta: " + dados[++i]);
                                                    System.out.println(" | Valor: " + dados[++i]);
                                                    System.out.println(" | CPF de quem fez a operação: " + dados[++i]);
                                                }
                                            }
                                            break;
                                        case "sair":
                                            s.next();
                                            break OUTER;
                                    }
                                    break;
                                }
                            }
                        } else {
                            System.out.println("**** NÃO FOI POSSÍVEL REALIZAR O LOGIN! TENTE NOVAMENTE... ****");
                        }
                        break;

                    /* 
                        Se o cliente escolher a segunda opção da tela inicial, ele
                        será direcionado para o cadastramento de contas.
                     */
                    case "2":
                        mensagem = indice.cadastrar();
                        saida.println(mensagem);

                        if (s.hasNext()) {
                            String numConta = s.next();
                            if (!"null".equals(numConta)) {
                                System.out.println("CADASTRADO COM SUCESSO! SEU CÓDIGO DA CONTA É: " + numConta);
                            }
                        } else {
                            System.out.println("NÃO FOI POSSÍVEL REALIZAR O CADASTRO!");
                        }
                        break;

                    /* 
                        Se o cliente escolher a terceira opção da tela inicial, ele
                        será direcionado para o cadastramento de contas conjuntas.
                     */
                    case "3":
                        mensagem = indice.cadastrarContaConjunta();
                        saida.println(mensagem);
                        if (s.hasNext() && !"null".equals(s.next())) {
                            System.out.println("NOVO CLIENTE ADICIONADO A CONTA!");
                        } else {
                            System.out.println("NÃO FOI POSSÍVEL VINCULAR OUTRO CLIENTE A CONTA!");
                        }
                        break;

                    /* 
                        Se o cliente escolher a quarta opção da tela inicial, ele
                        irá sair do sistema e será desconectado do servidor.
                     */
                    case "4":
                        // Opção 4: Sair do Sistema
                        saida.println("sairSistema");
                        s.close();
                        saida.close();
                        servidor.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("ESCOLHA UMA OPÇÃO VÁLIDA! TENTE NOVAMENTE...");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
