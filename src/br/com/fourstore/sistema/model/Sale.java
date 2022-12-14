package br.com.fourstore.sistema.model;

import br.com.fourstore.sistema.enums.PaymentMethodEnum;

import java.util.List;

public class Sale {


    private Client client;
    private List<Product> products;
    private Double amountValue;
    private PaymentMethodEnum paymentMethodEnum; // <<<<----- deveria ser PaymentMethod ?

    public Sale(Client client, List<Product> products, Double amountValue, PaymentMethodEnum paymentMethodEnum) {
        this.client = client;
        this.products = products;
        this.amountValue = amountValue;
        this.paymentMethodEnum = paymentMethodEnum;
    }

    public Sale(List<Product> products, Double amountValue, PaymentMethodEnum paymentMethodEnum) {
        this.products = products;
        this.amountValue = amountValue;
        this.paymentMethodEnum = paymentMethodEnum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getAmouthValue() {
        return amountValue;
    }

    public void setAmouthValue(Double amouthValue) {
        this.amountValue = amouthValue;
    }

    public PaymentMethodEnum getPaymentMethodEnum() {
        return paymentMethodEnum;
    }

    public void setPaymentMethodEnum(PaymentMethodEnum paymentMethodEnum) {
        this.paymentMethodEnum = paymentMethodEnum;
    }

    @Override
    public String toString() {
        if (this.client != null) {
            return "\n\nCliente: " + client.getName()
                    + "\n\nChave pix: " + client.getPixKey()
                    + "\nProdutos: " + products.toString()
                    + "\nValor total: " + amountValue
                    + "\nMétodo de pagamento: " + paymentMethodEnum.getDescription() + "\n";
        } else {
            return "\nVenda para o cliente: cliente não informado"
                    + "\nProdutos: " + products.toString()
                    + "\nValor total: " + amountValue
                    + "\nMétodo de pagamento: " + paymentMethodEnum.getDescription() + "\n";
        }

    }
}