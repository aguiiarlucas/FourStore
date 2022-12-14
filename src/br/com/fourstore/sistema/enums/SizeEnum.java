package br.com.fourstore.sistema.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SizeEnum {

    PP("18","Size PP"),
    P("19","Size P"),
    M ("20","Size M"),
    G ("21", "Size G"),
    GG("22","Size GG");

    private final String key;
    private final String description;

    public static final Map<String, SizeEnum> sizeMap = new HashMap<String, SizeEnum>();
    static {
        for(SizeEnum type : EnumSet.allOf(SizeEnum.class)){
            sizeMap.put(type.getKey(),type);
        }
    }

    public static SizeEnum get(String string) {
        return sizeMap.get(string);
    }
    SizeEnum(String key, String description) {
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
