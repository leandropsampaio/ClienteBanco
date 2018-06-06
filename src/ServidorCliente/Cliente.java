package ServidorCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Esta classe implementa o Cliente do sistema, ela recebe o número de Host e da
 * porta do servidor.
 *
 * @author Leandro Pereira Sampaio
 */
public class Cliente {

    private final String host;
    private final int porta;

    /**
     * Construtor da classe, responsável pela criação um novo cliente.
     *
     * @param host - Número de Ip do servidor.
     * @param porta - Número da porta do servidor.
     */
    public Cliente(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    public void executa() {
        try (Socket cliente = new Socket(host, porta);
                //Scanner s = new Scanner(cliente.getInputStream()); //Receber Mensagem do servidor
                PrintStream saida = new PrintStream(cliente.getOutputStream())) { //Enviar Mensagem para o servidor
            System.out.println("Você se conectou ao servidor!");

            TratadorDeMensagemDoServidor r = new TratadorDeMensagemDoServidor(cliente);
            new Thread(r).start(); //Cria uma nova Thread e inicia para cada cliente conectado no sistema.

            while (true) {
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
