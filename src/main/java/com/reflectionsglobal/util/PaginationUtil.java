package com.reflectionsglobal.util;

import java.util.Collections;
import java.util.List;

public final class PaginationUtil {

    private PaginationUtil() {
    }

    public static <T> List<T> paginate(
            List<T> data,
            int page,
            int size
    ) {

        if (page < 0 || size <= 0) {
            return Collections.emptyList();
        }

        int start = page * size;

        if (start >= data.size()) {
            return Collections.emptyList();
        }

        int end = Math.min(start + size, data.size());

        return data.subList(start, end);
    }
}