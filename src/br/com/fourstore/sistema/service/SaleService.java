package br.com.fourstore.sistema.service;

import br.com.fourstore.sistema.controller.ProductController;
import br.com.fourstore.sistema.data.ProductData;
import br.com.fourstore.sistema.data.SaleData;
import br.com.fourstore.sistema.model.Product;
import br.com.fourstore.sistema.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleService {

    static ArrayList<Product> cart = new ArrayList<Product>();
    ProductController productController = new ProductController();
    //ProductData productData = new ProductData();
    SaleData saleData = new SaleData();

    public void saveSale(Sale sale) {
        saleData.save(sale);
    }

    public List<Sale> saleList() {
        return saleData.list();
    }

    public Double amountValue(List<Product> products) {
        double amountValue = 0.0;
        for (Product product : products) {
            amountValue += product.getSalePrice() * product.getQuantity();
        }
        return amountValue;
    }
    public boolean addToCart(String sku , Integer quantity){
        Product product = productController.getProductBySkuObject(sku);
        Product productGeneric =  new Product(sku,quantity,product.getPurchasePrice(),product.getSalePrice());
        product.setQuantity(quantity);
        cart.add(productGeneric);
        return true;
    }
    public  List<Product>cart(){
        return cart;
    }

    public void clearCart(){
        cart.clear();
    }
    public static ArrayList<Product>getCart(){
        return cart;
    }

}
