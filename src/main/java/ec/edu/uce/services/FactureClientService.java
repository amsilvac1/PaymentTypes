package ec.edu.uce.services;

import ec.edu.uce.jpa.FactureClient;
import jakarta.ejb.Stateless;

@Stateless
public class FactureClientService extends GenericServiceImpl<FactureClient> {
    public FactureClientService() {
        super(FactureClient.class);
    }
}