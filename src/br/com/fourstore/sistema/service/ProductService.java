package br.com.fourstore.sistema.service;

import br.com.fourstore.sistema.data.ProductData;
import br.com.fourstore.sistema.model.Product;

import java.util.ArrayList;

public class ProductService {
    private static final ProductData productData = new ProductData();

    public void cadProduct(Product product) {
        productData.save(product);
    }

    public boolean registeredProduct(String sku) {
        return productData.getProductBySku(sku) != null;
    }



    public Boolean updateBySku(Product updateProduct) {
        String sku = updateProduct.getSku();
        Product product = productData.getProductBySku(sku);
        if (product != null) {
            return false;
        }
        return update(updateProduct, product);
    }

    private Boolean update(Product updateProduct, Product currentProduct) {
        //UpdateQuantityProduct.
        Integer updateQuantity = updateProduct.getQuantity();
        if (updateQuantity == Integer.MAX_VALUE) {
            updateProduct.setQuantity(currentProduct.getQuantity());
        }
        if (updateQuantity < 0) {
            return false;
        }
        //UpdatePurchasePrice
        Double updatePurchasePrice = updateProduct.getPurchasePrice();
        if (updatePurchasePrice == 0.0) {
            updateProduct.setPurchasePrice(currentProduct.getPurchasePrice());
        }
        if (updatePurchasePrice < 0) {
            return false;
        }
        productData.update(updateProduct);
        return true;
    }

    public Boolean deleteProductById(String id) {
        Product product = productData.getById(id);
        return this.deleteProduct(product);
    }

    public Boolean deleteProductBySku(String sku) {
        Product product = productData.getProductBySku(sku);
        return this.deleteProduct(product);
    }

    public Boolean deleteProduct(Product product) {
        if (product == null) {
            return false;
        }
        productData.delete(product);
        return true;
    }

    public String listProduct() {
        String aux = "";
        ArrayList<Product> list = new ArrayList<Product>();
        list = productData.listAll();
        if (list.size() != 0) {
            for (Product product : list) {
                aux += product.toString();
            }
        } else {
            aux = "There are no registered products!";
        }
        return aux;
    }

    public Boolean haveInStock(String sku, Integer qtd) {
        Product productInStock = productData.getProductBySku(sku);
        if (productInStock != null) {
            Integer quantity = productInStock.getQuantity();
            if (quantity >= qtd) {
                return true;
            }
        }
        return false;

    }

    public Product getProductBySku(String sku) {
        return productData.getProductBySku(sku);
    }


    public Product getById(String id) {
        return productData.getById(id);
    }

    public Product getBySku(String sku) {
        return productData.getProductBySku(String.valueOf(sku));

    }

    public void decrementProduct(String sku, Integer qtd) {
        Product product = productData.getProductBySku(sku);
        product.decrementProduct(qtd);
    }


}