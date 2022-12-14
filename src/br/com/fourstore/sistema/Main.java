package br.com.fourstore.sistema;
import br.com.fourstore.sistema.communication.MainMenu;
import br.com.fourstore.sistema.data.ClientData;
import br.com.fourstore.sistema.service.ClientService;
import br.com.fourstore.sistema.utils.Validations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();

    }

       // service.validationCpf(sc.next());

       /* ProductService productService = new ProductService();
        String description = "Calca Jeans";
        int id = 10;
        double value = 100.0;
        int quantity = 25;

        System.out.println(productService.CadProduct(quantity,id,value,description)  );
        System.out.println(productService.getList());

        description = "Blusa azul";
        id=12;
        value=110.0;
        quantity=42;

        System.out.println(productService.CadProduct(quantity,id,value,description)  );
        System.out.println(productService.getList());


        productService.removeItem(12);
        System.out.println("Item removido");

*/






    }


