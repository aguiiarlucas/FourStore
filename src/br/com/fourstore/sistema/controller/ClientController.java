package br.com.fourstore.sistema.controller;

import br.com.fourstore.sistema.model.Client;
import br.com.fourstore.sistema.service.ClientService;

public class ClientController {

    ClientService clientService = new ClientService();

    public boolean clientIsRegistered(String cpf) { // talvez de erro
        return clientService.clientIsRegistered(cpf);
    }

    public Client findByCpf(String cpf) {
        return clientService.findByCpf(cpf);
    }

    public void clientIsRegister(String name, String cpf) { // save client
        Client client = new Client(name, cpf);
        clientService.clientIsRegister(client);
    }

    public void registerPix(String pix) {
        clientService.registerPix(pix);
    }

    public void registerPixByCpf(String pix, String cpf) {
        clientService.registerPixByCpf(cpf, cpf);
    }
}
