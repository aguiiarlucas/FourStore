package br.com.fourstore.sistema.data;

import br.com.fourstore.sistema.interfaces.DataInterface;
import br.com.fourstore.sistema.model.Client;

import java.util.ArrayList;

public class ClientData implements DataInterface<Client> {
    private static final ArrayList<Client> clientList = new ArrayList<Client>();

    @Override
    public void save(Client client) {
        if (!ClientData.clientList.contains(client)) {
            ClientData.clientList.add(client);
        }
    }

    @Override
    public void delete(Client client) {
        ClientData.clientList.remove(client);
    }

    @Override
    public void update(Client client) { /// ARRUMAR
        for (int x = 0; x < ClientData.clientList.size(); x++) {
            if (client.getCpf().equals(ClientData.clientList.get(x).getCpf())) {
                ClientData.clientList.set(x, client);
            }
        }
    }

    @Override
    public ArrayList<Client> listAll() {
        return clientList;
    }


    public Client findByCpf(String cpf) {
        Client client = null;
        for (int x = 0; x < ClientData.clientList.size(); x++) {
            if (cpf.equals(ClientData.clientList.get(x).getCpf())) {
                client = ClientData.clientList.get(x);
                return client;
            }
        }
        return null;
    }
}

