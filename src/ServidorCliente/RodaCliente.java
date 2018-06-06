package ServidorCliente;

/**
 * Esta classe executa o cliente através do ip e a porta do servidor.
 *
 * @author Leandro Pereira Sampaio
 */
public class RodaCliente {

    public static void main(String[] args) {
        new Cliente("172.16.103.102", 12345).executa();
    }
}
