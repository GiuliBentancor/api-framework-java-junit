package utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static <K, V> Map<K, V> combineMaps(
        Map<K, V> baseMap,
        Map<K, V> overwritingMap
    ) {
        HashMap<K, V> combinedMap = new HashMap<>();

        if (baseMap != null && !baseMap.isEmpty()) {
            combinedMap.putAll(baseMap);
        }

        if (overwritingMap != null && !overwritingMap.isEmpty()) {
            combinedMap.putAll(overwritingMap);
        }

        return combinedMap;
    }
}
