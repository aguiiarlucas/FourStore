package br.com.fourstore.sistema.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ColorEnum {
    YELLOW("01" , "Yellow"),
    BLUE("02" , "Blue"),
    WHITE("03", "White"),
    BLACK("04", "Black"),
    GREEN("05" , "Green"),
    RED("06", "Red");

    private final String key;
    private final String description;

    public static final Map<String,ColorEnum>colorMap = new HashMap<String,ColorEnum>();
    static {
        for(ColorEnum color : EnumSet.allOf(ColorEnum.class)){
            colorMap.put(color.getKey(),color);
        }
    }

    public static ColorEnum get(String string) {
        return colorMap.get(string);
    }
    ColorEnum(String key, String description) {
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
