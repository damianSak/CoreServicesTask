package bootcamptask.utils.parsers;

import bootcamptask.model.Genre;

import java.util.HashMap;
import java.util.Map;

public class GenreUtils {

    private static final Map<String, Genre> mapCaseInsensitive;

    static {
        mapCaseInsensitive = new HashMap<>();
        for (Genre v : Genre.values()) {
            mapCaseInsensitive.put(v.toString().toLowerCase(), v);
        }
    }

    public static Genre findByNameCaseInsensitive(String name) {
        return mapCaseInsensitive.get(name.toLowerCase());
    }
}
