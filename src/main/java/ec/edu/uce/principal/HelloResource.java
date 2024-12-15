package ec.edu.uce.principal;

import ec.edu.uce.jpa.Client;
import ec.edu.uce.services.ClientService;
import ec.edu.uce.payments.Payment;
import ec.edu.uce.payments.QualifierPayment;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    @Inject
    @QualifierPayment("creditCard")
    Payment creditCard;

    @Inject
    @QualifierPayment("paypal")
    Payment paypal;

    @Inject
    @QualifierPayment("transfer")
    Payment transfer;

    @Inject
    ClientService clientService;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @GET
    @Produces("text/plain")
    @Path("/transfer")
    public String TranferPayment() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentUnity");
        return transfer.payProcess(100);
    }

    @GET
    @Produces("text/plain")
    @Path("/add")
    public String addCliente() {
        Client client = new Client();
        client.setName("Angelo");
        client.setEmail("angelomsn0@gmail.com");
        client.setPhone("0987654321");
        client.setAddress("Quito");
        clientService.save(client);
        return "Cliente agregado";
    }

    @GET
    @Produces("text/plain")
    @Path("/paypal")
    public String PayPalPayment() {

        return paypal.payProcess(200);
    }

    @GET
    @Produces("text/plain")
    @Path("/credit-card")
    public String CreditCardPayment() {

        return creditCard.payProcess(300);
    }


}