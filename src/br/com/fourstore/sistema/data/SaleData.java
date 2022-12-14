package br.com.fourstore.sistema.data;

import br.com.fourstore.sistema.model.Product;
import br.com.fourstore.sistema.model.Sale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaleData {

    private ArrayList<Sale> saleList = new ArrayList<>();

    public  void save(Sale sale){
        saleList.add(sale);
    }

    public  ArrayList<Sale> list() {
        return saleList;
    }


}
