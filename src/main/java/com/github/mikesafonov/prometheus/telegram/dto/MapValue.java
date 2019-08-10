package com.github.mikesafonov.prometheus.telegram.dto;

import java.util.Map;
import java.util.Optional;

/**
 * @author MikeSafonov
 */
interface MapValue {

    default Optional<String> getValue(Map<String, String> map, String key) {
        if (map != null) {
            return Optional.ofNullable(map.get(key));
        }

        return Optional.empty();
    }
}
