package com.plus.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author plus me
 * @date 2018/9/25
 */
public class CollectionUtils {

    public static Set<String> asSet(Collection<String> items) {
        if (items == null) {
            return Collections.emptySet();
        }
        return items.stream().map((item) -> item.toLowerCase(Locale.ENGLISH))
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static List<String> asList(String... names) {
        return Arrays.asList(names);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

}
