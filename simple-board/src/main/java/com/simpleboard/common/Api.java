package com.simpleboard.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Api<T> {

  private T body;
  private Pagination pagination;

  public static <U> Api<U> ok(U body, Pagination pagination) {
    return Api.<U>builder()
        .body(body)
        .pagination(pagination)
        .build();
  }

  public static <U> Api<U> ok(U body) {
    return Api.<U>builder()
        .body(body)
        .build();
  }

}
