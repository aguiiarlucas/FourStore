package br.com.fourstore.sistema.controller;

import br.com.fourstore.sistema.model.Product;
import br.com.fourstore.sistema.service.ProductService;
import br.com.fourstore.sistema.utils.Validations;

public class ProductController {

    private static final ProductService productService = new ProductService();
    private static final Validations validations = new Validations();


    public String cadProduct(String sku, String description, String id, Integer quantity, Double purchasePrice, Double salePrice) {
        String aux = "";
        Product product = new Product(sku, description, id, quantity, purchasePrice, salePrice);
        productService.cadProduct(product);

        aux = "\nO produto foi registrado com sucesso\n"
                + "\n SKU: " + product.getSku()
                + "\n ID: " + product.getId()
                + "\n Description: " + product.getDescription()
                + "\n Type: " + product.getType()
                + "\n Size: " + product.getSize()
                + "\n Category" + product.getCategory()
                + "\n Color:" + product.getColor()
                + "\n Quantity: " + product.getQuantity()
                + "\n Purchase Price: " + product.getPurchasePrice()
                + "\n Sale Price: " + product.getSalePrice();
        return aux;


    }


    public Boolean productIsRegistered(String sku) {
        return productService.registeredProduct(sku);
    }


    public String getProductById(String id) {
        Product product = productService.getById(id);
        if (product == null) return "Não existe produto com o id" + id;
        return product.toString();
    }

    public String getProductBySku(String sku) {
        Product product = productService.getBySku(sku);
        if (product == null) return "Não existe produto com o sku\"" + sku;
        return product.toString();
    }

    public String deleteProductById(String id) {
        if (productService.deleteProductById(id)) return "Produto deletado";
        return "Não existe nenhum produto com o id" + id;
    }

    public String deleteProductBySku(String sku) {
        if (productService.deleteProductBySku(sku)) return "Produto deletado";
        return "Não existe nenhum produto com o sku" + sku;
    }


    public String updateProductBySku(String sku, Integer quantity, Double purchasePrice, Double salePrice) {
        Product product = new Product(sku, quantity, purchasePrice, salePrice);
        return (productService.updateBySku(product)) ? "Produto alterado com sucesso!" : "Dados invalidos! O produto nao foi alterado.";
    }

    public String updateProductById(String id, Integer quantity, Double purchasePrice, Double salePrice) {
        Product oldProduct = productService.getById(id);
        String sku = oldProduct.getSku();
        Product product = new Product(sku, quantity, purchasePrice, salePrice);
        return (productService.updateBySku(product)) ? "Produto alterado com sucesso!" : "Dados invalidos! O produto nao foi alterado.";
    }

    public String listProducts() {
        return productService.listProduct();
    }

    public Boolean haveStock(String sku, Integer qtt) {
        return productService.haveInStock(sku, qtt);
    }

    public Product getProductBySkuObject(String sku) {
        return productService.getProductBySku(sku);
    }

    public static void decrementProduct(String sku, Integer qtt) {
        productService.decrementProduct(sku, qtt);
    }

    public boolean validateSku(String sku) {
        if (!validations.validateSkuRegex(sku)) {
            return false;
        }
        int size = Integer.parseInt(sku.substring(0, 2));
        int category = Integer.parseInt(sku.substring(2, 4));
        int color = Integer.parseInt(sku.substring(4, 6));
        int type = Integer.parseInt(sku.substring(6));


        return (size <= 22 && size >= 18) && (category <= 34 && category >= 30) && (color <= 6 && color >= 1) && (type <= 51 && type >= 44);
    }


}
