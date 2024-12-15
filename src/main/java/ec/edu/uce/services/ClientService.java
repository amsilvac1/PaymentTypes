package ec.edu.uce.services;

import ec.edu.uce.jpa.Client;
import jakarta.ejb.Stateless;

@Stateless
public class ClientService extends GenericServiceImpl<Client> {
    public ClientService() {
        super(Client.class);
    }
}