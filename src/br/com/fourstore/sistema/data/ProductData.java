package br.com.fourstore.sistema.data;

import br.com.fourstore.sistema.interfaces.DataInterface;
import br.com.fourstore.sistema.model.Product;

import java.util.ArrayList;

public class ProductData implements DataInterface<Product> {

    //  private static ArrayList<Product> inventory = new ArrayList<>();

    private static final ArrayList<Product> listProduct = new ArrayList<>();

    @Override
    public void save(Product product) {
        System.out.println(
              "salvo"
        );listProduct.add(product);
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
                productList.update(product);

            }
        }
    }

    @Override
    public ArrayList<Product> listAll() {
        if (listProduct != null) {
            return listProduct;
        }
        return null;
    }

    public Product getById(String id) {
        for (int i = 0; i < listProduct.size(); i++) {
            String productId = listProduct.get(i).getId();
            if (productId.equals(id)) {
                return listProduct.get(i);
            }
        }
        return null;
    }

    public Product getProductBySku (String sku) {
        for(int i = 0; i < listProduct.size(); i++) {
            String productSku = listProduct.get(i).getSku();
            if(productSku.equals(sku)) {
                return listProduct.get(i);
            }
        }
        return null;
    }
}




