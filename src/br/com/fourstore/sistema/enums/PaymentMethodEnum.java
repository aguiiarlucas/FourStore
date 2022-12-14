package br.com.fourstore.sistema.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaymentMethodEnum {

    MONEY("22","Money"),
    CREDITCARD("23","Credit Card"),
    DEBITCARD ("24","Debit Card"),
    PIX ("25", "Pix");


    private final String key;
    private final String description;

    public static final Map<String, PaymentMethodEnum> paymentMap = new HashMap<String, PaymentMethodEnum>();
    static {
        for(PaymentMethodEnum type : EnumSet.allOf(PaymentMethodEnum.class)){
            paymentMap.put(type.getKey(),type);
        }
    }

    public static PaymentMethodEnum get(String string) {
        return paymentMap.get(string);
    }
    PaymentMethodEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
