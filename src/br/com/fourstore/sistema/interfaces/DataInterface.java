package br.com.fourstore.sistema.interfaces;

import java.util.ArrayList;

public interface DataInterface<Object> {
    void save(Object object);

    void delete(Object object);

    void update(Object object);

    ArrayList<Object> listAll();

}
