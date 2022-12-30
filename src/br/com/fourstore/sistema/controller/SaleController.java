package br.com.fourstore.sistema.controller;

import br.com.fourstore.sistema.data.ClientData;
import br.com.fourstore.sistema.enums.PaymentMethodEnum;
import br.com.fourstore.sistema.model.Client;
import br.com.fourstore.sistema.model.Product;
import br.com.fourstore.sistema.model.Sale;
import br.com.fourstore.sistema.service.SaleService;

import java.util.ArrayList;
import java.util.List;

public class SaleController {

    private final SaleService saleService = new SaleService();
    private final ClientData clientData = new ClientData();

    public String addToCart(Integer quantity, String sku) { // adicionei o sku e a quantity que vai no carrinho ( o sku ja contei o produto :?)

        if (saleService.addToCart(sku, quantity)) {
            return "\n Produto adicionado com sucesso!\n";
        }
        return "\nEste produto não pode ser adicionado\n";
    }

    public String clearCart() { // limpei o carrinho
        saleService.clearCart();
        return "Carrinho vazio";
    }

    public List<Product> cart() {
        return saleService.cart();
    }

    public Double amountValue(List<Product> products) {
        Double aux = 0.0;
        aux = saleService.amountValue(products);
        return aux;
    }

    public String consultSale() { // consultar venda  ( ? )
        String aux = "";
        if (saleService.saleList().size() == 0) {
            aux = "\n Não há histórico de vendas!\n";
            return aux;
        }
        aux = saleService.saleList().toString();
        return aux;
    }

    public String saleRegister(PaymentMethodEnum paymentMethodEnum, String cpf) {
        ArrayList<Product> products = SaleService.getCart(); //
        Client client = clientData.findByCpf(cpf);
        Double amountValue = this.amountValue(products);
        Sale sale = new Sale(client, products, amountValue, paymentMethodEnum);

        saleService.saveSale(sale);
        return "\nVenda concluída com sucesso\n" + sale;
    }

    public String saleRegister(PaymentMethodEnum paymentMethodEnum) {
        ArrayList<Product> products = SaleService.getCart();
        Double amountValue = this.amountValue(products);
        Sale sale = new Sale(products, amountValue, paymentMethodEnum);

        saleService.saveSale(sale);
        return "\nVenda concluída com sucesso\n" + sale;
    }
}
