package com.example.memorydb.book.db.entity;

import com.example.memorydb.entity.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class BookEntity extends Entity {

  private String name;
  private String category;
  private BigDecimal amount;

}
