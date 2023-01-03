package br.com.fourstore.sistema.data;

import br.com.fourstore.sistema.interfaces.DataInterface;
import br.com.fourstore.sistema.model.Product;

import java.util.ArrayList;

public class ProductData implements DataInterface<Product> {

    //  private static ArrayList<Product> inventory = new ArrayList<>();

    private static final ArrayList<Product> listProduct = new ArrayList<>();

    @Override
    public void save(Product product) {
     listProduct.add(product);
    }

    @Override
    public void delete(Product product) { // via ID
        for( int i =0 ; i < listProduct.size();i++){
            Product productList = listProduct.get(i);
            if (productList.getId().equals(productList.getId())){
                listProduct.remove(i);
            }
        }
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < listProduct.size(); i++) {
            Product productList = listProduct.get(i);

            if (productList.getId().equals(product.getId())) {
                    listProduct.set(i,product);


            }

        }
        System.out.println(product);
    }

    @Override
    public ArrayList<Product> listAll() {
        return listProduct;
    }

    public Product getById(String id) {
        for (Product product : listProduct) {
            String productId = product.getId();
            if (productId.equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product getProductBySku (String sku) {
        for (Product product : listProduct) {
            String productSku = product.getSku();
            if (productSku.equals(sku)) {
                return product;
            }
        }
        return null;
    }
}




