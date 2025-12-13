package com.simpleboard.crud;

import com.simpleboard.common.Pagination;
import java.util.List;

public record PageResponse<T>(
    List<T> body,
    Pagination pagination
) {

}
