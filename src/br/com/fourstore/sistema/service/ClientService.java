package br.com.fourstore.sistema.service;

import br.com.fourstore.sistema.data.ClientData;
import br.com.fourstore.sistema.model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 ValidationCPF - credits :
 Wagner : https://www.youtube.com/@wagnercct2
 */
public class ClientService {

    ClientData clientData = new ClientData();

    public boolean validationCpf(String cpf) {
        String pattern = "(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(cpf);

        if (!matcher.matches()) {
            System.out.println("Cpf escrito errado");  // Apagar essa linha
            return false;
        }

        String S1, S2, S3, S4, S5, S6, S7, S8, S9, conf; // recebe o valor ate a posicao 9
        int N1, N2, N3, N4, N5, N6, N7, N8, N9, verify1, verify2; //

        S1 = cpf.substring(0, 1);        N1 = Integer.parseInt(S1);
        S2 = cpf.substring(1, 2);        N2 = Integer.parseInt(S2);
        S3 = cpf.substring(2, 3);        N3 = Integer.parseInt(S3);
        S4 = cpf.substring(4, 5);        N4 = Integer.parseInt(S4);
        S5 = cpf.substring(5, 6);        N5 = Integer.parseInt(S5);
        S6 = cpf.substring(6, 7);        N6 = Integer.parseInt(S6);
        S7 = cpf.substring(8, 9);        N7 = Integer.parseInt(S7);
        S8 = cpf.substring(9, 10);       N8 = Integer.parseInt(S8);
        S9 = cpf.substring(10, 11);      N9 = Integer.parseInt(S9);

        verify1 = (N1 * 10 + N2 * 9 + N3 * 8 + N4 * 7
                + N5 * 6 + N6 * 5 + N7 * 4 + N8 * 3 + N9 * 2);


        if ((verify1 % 11) < 2) {
            verify1 = 0;

        } else {
            verify1 = 11 - (verify1 % 11);
        }
        verify2 = (N1 * 11 + N2 * 10 + N3 * 9 + N4 * 8
                + N5 * 7 + N6 * 6 + N7 * 5 + N8 * 4 + N9 * 3 + verify1 * 2);

        if ((verify2 % 11) < 2) {
            verify2 = 0;
        } else {
            verify2 = 11 - (verify2 % 11);
        }
        conf = (S1 + S2 + S3 + "." + S4 + S5 + S6 + "." + S7 + S8 + S9 + "-" + verify1 + "" + verify2);
        return cpf.equals(cpf);

    }

    public boolean clientIsRegistered(String cpf) { // boolean pq tem uma condição ( if)
        return clientData.findByCpf(cpf) != null;
    }

    public void clientIsRegister(Client client) { // save client
        clientData.save(client);
    }

    public Client findByCpf(String cpf) {
        return clientData.findByCpf(cpf);
    }

    public void registerPix(String pix) {
        Client client = new Client(pix);
        clientData.save(client);
    }

    public void registerPixByCpf(String pix, String cpf) {
        Client client = clientData.findByCpf(cpf);
        client.setPixKey(pix);
    }

}


