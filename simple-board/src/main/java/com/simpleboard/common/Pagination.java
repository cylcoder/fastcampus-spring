package com.simpleboard.common;

import com.simpleboard.post.db.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pagination {

  private Integer page;
  private Integer size;
  private Integer currentElements;
  private Integer totalPages;
  private Long totalElements;

  public static<T> Pagination toDto(Page<T> page) {
    return Pagination.builder()
        .page(page.getNumber())
        .size(page.getSize())
        .currentElements(page.getNumberOfElements())
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .build();
  }

}
