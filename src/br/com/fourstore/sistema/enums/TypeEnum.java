package br.com.fourstore.sistema.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TypeEnum {

    LONGSLEEVED("44","Long Sleeved"), // Manga Longa
    SHORTSLEEVE("45","Short Sleeve"), //Manga Curta
    POLONECK ("46","POLO NECK"),//Gola Polo
    SHOES ("47", "Shoes"), //Tenis
    SWEATSHIRT("48","SweatShit"),// Moletom
    DRESS("49","Dress"),//Vestido
    HALF("50","Half"),
    JEANS("51","Jeans");//Calça Jenas


    private final String key;
    private final String description;

    public static final Map<String,TypeEnum> typeMap = new HashMap<String,TypeEnum>();
    static {
        for(TypeEnum type : EnumSet.allOf(TypeEnum.class)){
            typeMap.put(type.getKey(),type);
        }
    }

    public static TypeEnum get(String string) {
        return typeMap.get(string);
    }
    TypeEnum(String key, String description) {
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
