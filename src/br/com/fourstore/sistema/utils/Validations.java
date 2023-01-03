package br.com.fourstore.sistema.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    Scanner sc = new Scanner(System.in);
    public static int id;

    public int menuValidation(String input, String verification) {
        int opc;

        if (input.matches(verification)) {
            opc = Integer.parseInt(input);
            return opc;
        } else
            opc = -1;
        return opc;
    }

    public boolean validateCard(String acceptedCards) {
        boolean retorno = false;

        if (acceptedCards.length() != 16 && acceptedCards.length() != 19) {
            return retorno;
        }
        retorno = checkLuhn(acceptedCards);

        return retorno;
    }

    private boolean checkLuhn(String value) {


        value = value.replace(" ", "");
        int sum = 0;
        boolean shouldDouble = false;

        for (int i = value.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(value.substring(i, i + 1));
            if (shouldDouble) {
                if ((digit *= 2) > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            shouldDouble = !shouldDouble;
        }
        return (sum % 10) == 0;
    }

    public boolean validateSkuRegex(String sku) {
        String pattern = "(\\d{8})";

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(sku);

        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public String regexInteger() {

        String quantity;
        boolean flag =true;
        do {//verificaçao para ver se tem somente numeros


            System.out.println("Digite a quantidade");
            quantity = sc.next();
            if (quantity.matches("\\d+")) {
                flag =false;
            }
        } while (flag);
        return quantity;

    }


    public String regexPurchasePrice() {
        String quantity;
        boolean flag =true;

        do {//verificaçao para ver se tem somente numeros

            System.out.println("Digite o valor da compra");
            quantity = sc.next();
            if (quantity.matches("^[0-9]+([,.][0-9]?)?$")) {
                  flag = false;

            }

        } while (flag);
        return quantity;
    }

    public String regexSalePrice() {
        String quantity;
        boolean flag =true;
        do {//verificaçao para ver se tem somente numeros


            System.out.println("Digite o valor da Venda");
            quantity = sc.next();
            if (quantity.matches("^[0-9]+([,.][0-9]?)?$")) {
                flag = false;
            }
        } while (flag);
        return quantity;

    }

    public void gerarId() {
        System.out.println("Id gerado");
        id++;
    }

    public boolean validateCpfRegex(String sku) {
        String pattern = "(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(sku);

        return matcher.matches();
    }

    public boolean cpfValidation(String cpf) {
        String S1, S2, S3, S4, S5, S6, S7, S8, S9, conf;
        int N1, N2, N3, N4, N5, N6, N7, N8, N9, verify1, verify2;

        S1 = cpf.substring(0, 1);
        N1 = Integer.parseInt(S1);
        S2 = cpf.substring(1, 2);
        N2 = Integer.parseInt(S2);
        S3 = cpf.substring(2, 3);
        N3 = Integer.parseInt(S3);
        S4 = cpf.substring(4, 5);
        N4 = Integer.parseInt(S4);
        S5 = cpf.substring(5, 6);
        N5 = Integer.parseInt(S5);
        S6 = cpf.substring(6, 7);
        N6 = Integer.parseInt(S6);
        S7 = cpf.substring(8, 9);
        N7 = Integer.parseInt(S7);
        S8 = cpf.substring(9, 10);
        N8 = Integer.parseInt(S8);
        S9 = cpf.substring(10, 11);
        N9 = Integer.parseInt(S9);

        verify1 = (N1 * 10 + N2 * 9 + N3 * 8 + N4 * 7
                + N5 * 6 + N6 * 5 + N7 * 4 + N8 * 3 + N9 * 2);
        if ((verify1 % 11) < 2) {
            verify1 = 0;
        } else {
            verify1 = 11 - (verify1 % 11);
        }
        verify2 = (N1 * 11 + N2 * 10 + N3 * 9 + N4 * 8
                + N5 * 7 + N6 * 6 + N7 * 5 + N8 * 4 + N9 * 3 + verify1 * 2);

        if ((verify2 % 11) < 2) {
            verify2 = 0;
        } else {
            verify2 = 11 - (verify2 % 11);
        }
        conf = (S1 + S2 + S3 + "." + S4 + S5 + S6 + "." + S7 + S8 + S9 + "-" + verify1 + "" + verify2);
        return cpf.equals(cpf);

    }
}





