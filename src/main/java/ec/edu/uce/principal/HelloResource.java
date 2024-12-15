package ec.edu.uce.principal;

import ec.edu.uce.jpa.Client;
import ec.edu.uce.jpa.FactureClient;
import ec.edu.uce.jpa.FactureDetail;
import ec.edu.uce.jpa.Product;
import ec.edu.uce.payments.Payment;
import ec.edu.uce.payments.PaymentType;
import ec.edu.uce.payments.QualifierPayment;
import ec.edu.uce.services.ClientService;
import ec.edu.uce.services.FactureClientService;
import ec.edu.uce.services.FactureDetailService;
import ec.edu.uce.services.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;


@Path("/hello-world")
public class HelloResource {


    @Inject
    private ClientService clientService;

    @Inject
    private FactureClientService factureClientService;

    @Inject
    private FactureDetailService factureDetailService;

    @Inject
    private ProductService productService;

    @Inject
    @QualifierPayment(PaymentType.CREDITCARD)
    private Payment paymentCreditCard;

    @Inject
    @QualifierPayment(PaymentType.PAYPAL)
    private Payment paymentPaypal;

    @Inject
    @QualifierPayment(PaymentType.TRANSFER)
    private Payment paymentTransfer;

    //CREAR UN CLIENTE DIRECTAMENTE DESDE EL METODO
    @GET
    @Produces("text/plain")
    @Path("/client")
    public String addCliente() {
        // Create and save Client
        Client client1 = new Client();
        client1.setName("Angelo Silva");
        client1.setEmail("amsilvac1@uce.edu.ec");
        client1.setPhone("0987169222");
        client1.setAddress("Quito-Caupicho");
        clientService.save(client1);
        return "El cliente se agrego de forma correcta";
    }

    //CREAR UN CLIENTE DESDE LA URL
    //EJEMPLO-1: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/addclient?name=Carlos+Perez&email=carlos99@gmail.com&phone=0988651623&address=Villaflora
    //EJEMPLO-2: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/addclient?name=Andres+Orozco&email=andresO20000@gmail.com&phone=0981234576&address=Guamani
    @GET
    @Produces("text/plain")
    @Path("/addclient")
    public String addClient(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("phone") String phone,
                            @QueryParam("address") String address) {

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setAddress(address);
        clientService.save(client);
        return "El cliente se agrego de forma adecuada";
    }

    //OBTENER TODOS LOS CLIENTES
    @GET
    @Produces("text/plain")
    @Path("/clients")
    public String getClients() {
        List<Client> clients = clientService.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("============================================ CLIENTES ============================================\n");
        sb.append(String.format("%-5s %-20s %-30s %-15s %-30s\n", "ID", "Nombre", "Email", "Teléfono", "Dirección"));
        sb.append("==================================================================================================\n");
        for (Client client : clients) {
            sb.append(String.format("%-5d %-20s %-30s %-15s %-30s\n",
                    client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.getAddress()));
        }
        sb.append("==================================================================================================\n");
        return sb.toString();
    }

    //CREAR UN PRODUCTO DIRECTAMENTE DESDE EL METODO
    @GET
    @Produces("text/plain")
    @Path("/product")
    public String addProduct() {

        Product product1 = new Product();
        product1.setName("Refrigerador");
        product1.setPrice(600.0);
        productService.save(product1);

        return "el Producto fue agregado de forma adecuada";
    }

    //CREAR UN PRODUCTO DESDE LA URL
    //EJEMPLO-1: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/addproduct?name=Televisor&price=500
    //EJEMPLO-2: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/addproduct?name=Play+Station+5&price=600
    @GET
    @Produces("text/plain")
    @Path("/addproduct")
    public String addProduct(@QueryParam("name") String name,
                             @QueryParam("price") double price) {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.save(product);

        return "el Producto fue agregado de forma adecuada";
    }

    //OBTENER TODOS LOS PRODUCTOS
    @GET
    @Produces("text/plain")
    @Path("/products")
    public String getProducts() {
        List<Product> products = productService.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("========= PRODUCTOS ==============\n");
        sb.append(String.format("%-5s %-20s %-10s\n", "ID", "Nombre", "Precio"));
        sb.append("==================================\n");
        for (Product product : products) {
            sb.append(String.format("%-5d %-20s %-10.2f\n", product.getId(), product.getName(), product.getPrice()));
        }
        sb.append("==================================\n");
        return sb.toString();
    }


    //CREAR UNA FACTURA DESDE LA URL
    //EJEMPLO-1: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/createfacture?clientId=1&paymentType=creditCard&productIds=1&productIds=2&quantities=2&quantities=3
    //EJEMPLO-2: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/createfacture?clientId=2&paymentType=paypal&productIds=1&productIds=3&quantities=1&quantities=4
    //EJEMPLO-3: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/createfacture?clientId=3&paymentType=transfer&productIds=2&productIds=3&quantities=2&quantities=3
    @GET
    @Produces("text/plain")
    @Path("/createfacture")
    public String createFacture(@QueryParam("clientId") int clientId,
                                @QueryParam("paymentType") String paymentType,
                                @QueryParam("productIds") List<Integer> productIds,
                                @QueryParam("quantities") List<Integer> quantities) {
        if (productIds.size() != quantities.size()) {
            return "La cantidad de productos y cantidades no coinciden";
        }

        Client client = clientService.findById(clientId);
        if (client == null) {
            return "Cliente no encontrado";
        }

        FactureClient factureClient = new FactureClient();
        factureClient.setClient(client);
        factureClient.setPaymentType(paymentType);
        factureClient.setDate(java.time.LocalDate.now().toString());

        double total = 0.0;
        List<FactureDetail> factureDetails = new ArrayList<>();

        for (int i = 0; i < productIds.size(); i++) {
            Product product = productService.findById(productIds.get(i));
            if (product == null) {
                return "Producto con ID " + productIds.get(i) + " no encontrado";
            }

            FactureDetail detail = new FactureDetail();
            detail.setProduct(product);
            detail.setQuantity(quantities.get(i));
            detail.setUnitPrice(product.getPrice());
            detail.setFactureClient(factureClient);

            // Calculando el total de cada producto: cantidad * precio
            total += product.getPrice() * quantities.get(i);
            factureDetails.add(detail);
        }

        factureClient.setTotal(total);
        factureClient.setFactureDetail(factureDetails);
        factureClientService.save(factureClient);

        // Guardar cada detalle de la factura
        for (FactureDetail detail : factureDetails) {
            factureDetailService.save(detail);
        }

        return "Factura creada con éxito. Total a pagar: " + total;
    }


    //OBTENER UNA IMPRESIÓN DE FACTURA POR ID
    //EJEMPLO: http://localhost:8080/PaymentsTypes-1.0-SNAPSHOT/api/hello-world/printfacture?factureId=1
    @GET
    @Produces("text/plain")
    @Path("/printfacture")
    public String printFacture(@QueryParam("factureId") int factureId) {
        //Buscar la factura en la base de datos
        FactureClient factureClient = factureClientService.findById(factureId);
        if (factureClient == null) {
            return "Factura no encontrada";
        }

        //Obtener la información del cliente
        Client client = factureClient.getClient();
        if (client == null) {
            return "El cliente asociado a esta factura no se encontró";
        }

        //Obtener los detalles de la factura
        List<FactureDetail> factureDetails = factureClient.getFactureDetail();
        if (factureDetails == null || factureDetails.isEmpty()) {
            return "La factura no tiene detalles de productos";
        }


        //Mostrar la información del cliente
        StringBuilder sb = new StringBuilder();
        sb.append("=================== FACTURA ===================\n");
        sb.append("Nombre del Cliente: ").append(client.getName()).append("\n");
        sb.append("Correo Electrónico: ").append(client.getEmail()).append("\n");
        sb.append("Teléfono: ").append(client.getPhone()).append("\n");
        sb.append("Dirección: ").append(client.getAddress()).append("\n");
        sb.append("Fecha de Emisión: ").append(factureClient.getDate()).append("\n");
        sb.append("===============================================\n");

        //Mostrar la lista de productos comprados
        sb.append("Productos Comprados:\n");
        sb.append(String.format("%-20s %-10s %-10s %-10s\n", "Producto", "Cantidad", "Precio Unit.", "Subtotal"));
        double total = 0.0;

        for (FactureDetail detail : factureDetails) {
            String productName = detail.getProduct().getName();
            double unitPrice = detail.getUnitPrice();
            int quantity = (int) detail.getQuantity(); // Asumimos que la cantidad es entera
            double subtotal = unitPrice * quantity;

            sb.append(String.format("%-20s %-10d %-10.2f %-10.2f\n", productName, quantity, unitPrice, subtotal));
            total += subtotal;
        }

        sb.append("===============================================\n");
        sb.append("TOTAL A PAGAR: $").append(String.format("%.2f", total)).append("\n");
        sb.append("===============================================\n");

        PaymentType paymentType;

        try {
            paymentType = PaymentType.valueOf(factureClient.getPaymentType().toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Error: Tipo de pago no soportado. Los tipos válidos son: CREDIT_CARD, TRANSFER, PAYPAL.";
        }

        Payment paymentProcessor;

        switch (paymentType) {
            case CREDITCARD:
                paymentProcessor = paymentCreditCard;
                break;
            case TRANSFER:
                paymentProcessor = paymentTransfer;
                break;
            case PAYPAL:
                paymentProcessor = paymentPaypal;
                break;
            default:
                return "Error: Tipo de pago no soportado.";
        }
        return sb.toString() + paymentProcessor.payProcess();
    }
}

