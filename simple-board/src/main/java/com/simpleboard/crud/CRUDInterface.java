package com.simpleboard.crud;

import org.springframework.data.domain.Pageable;

public interface CRUDInterface<ENTITY, REQUEST, RESPONSE> {

  RESPONSE save(REQUEST request);

  RESPONSE findById(Long id);

  RESPONSE update(REQUEST request);

  void deleteById(Long id);

  PageResponse<RESPONSE> findAll(Pageable pageable);

}
