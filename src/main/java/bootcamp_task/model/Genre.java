package bootcamp_task.model;

import java.util.HashMap;
import java.util.Map;

public enum Genre {
    Rock("Rock"),
    Alternative("Alternative"),
    R_AND_B("R&B");

    private final String name;

    private static final Map<String, Genre> map;
    private static final Map<String, Genre> mapNoCaseSensivity;

    static {
        map = new HashMap<String,Genre>();
        for (Genre v : Genre.values()) {
            map.put(v.name, v);
        }
    }
    public static Genre findByName(String name) {
        return map.get(name);
    }

    static {
        mapNoCaseSensivity = new HashMap<String,Genre>();
        for (Genre v : Genre.values()) {
            map.put(v.name.toLowerCase(), v);
        }
    }

    public static Genre findByNameNoCaseSensvity(String name) {
        return map.get(name.toLowerCase());
    }


    Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}



