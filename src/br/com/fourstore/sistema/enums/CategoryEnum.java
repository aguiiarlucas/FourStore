package br.com.fourstore.sistema.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {

    SHORTS("30", "Shorts"), //Bermudas
    SHOES("31", "Shoes"), //Calçados
    TSHIRTS("32", "Tshirts"),//Camisetas
    SUIT("33", "Suit"),//Social
    ACESSORIES("34", "Acessories");//Acessorio


    private final String key;
    private final String description;

    public static final Map<String, CategoryEnum> categoryMap = new HashMap<String, CategoryEnum>();

    static {
        for (CategoryEnum categoryEnum : EnumSet.allOf(CategoryEnum.class)) {
            categoryMap.put(categoryEnum.getKey(), categoryEnum);
        }
    }

    public static CategoryEnum get(String string) {
        return categoryMap.get(string);
    }

    CategoryEnum(String key, String description) {
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
