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

    public Boolean updateBySku(Product updatedProduct) {
        String Sku = updatedProduct.getSku();

        Product originalProduct = productData.getProductBySku(Sku);
        if (originalProduct == null) {
            return false;
        }

        return update(updatedProduct, originalProduct);
    }

    private Boolean update(Product updateProduct, Product currentProduct) {
        Integer updateQuantity = updateProduct.getQuantity();
        Double updatePurchasePrice = updateProduct.getPurchasePrice();
        Double updateSalePrice= updateProduct.getSalePrice();

        if (updateQuantity == 0 && updatePurchasePrice == 0 && updateSalePrice == 0) {
            return false;
        }

        if (Integer.MAX_VALUE > updateQuantity && updateQuantity > 0) {
            currentProduct.setQuantity((updateProduct.getQuantity()));
        }
        if (Double.MAX_VALUE > updatePurchasePrice && updatePurchasePrice > 0) {
            currentProduct.setPurchasePrice(updateProduct.getPurchasePrice());

        }
        if (Double.MAX_VALUE > updateSalePrice && updateSalePrice > 0) {
            currentProduct.setSalePrice(updateProduct.getSalePrice());
        }

        productData.update(currentProduct);
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