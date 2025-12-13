package com.simpleboard.crud;

import com.simpleboard.common.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class CRUDService<ENTITY, REQUEST, RESPONSE> implements CRUDInterface<ENTITY, REQUEST, RESPONSE> {

  private final JpaRepository<ENTITY, Long> jpaRepository;
  private final Converter<ENTITY, REQUEST, RESPONSE> converter;

  @Override
  public RESPONSE save(REQUEST dto) {
    ENTITY entity = converter.toEntity(dto);
    jpaRepository.save(entity);
    return converter.toDto(entity);
  }

  public RESPONSE findById(Long id) {
    ENTITY entity = jpaRepository.findById(id)
        .orElseThrow();
    return converter.toDto(entity);
  }

  public RESPONSE update(REQUEST dto) {
    ENTITY entity = converter.toEntity(dto);
    ENTITY savedEntity = jpaRepository.save(entity);
    return converter.toDto(savedEntity);
  }

  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  public PageResponse<RESPONSE> findAll(Pageable pageable) {
    Page<ENTITY> page = jpaRepository.findAll(pageable);
    List<RESPONSE> body = page.stream()
        .map(converter::toDto)
        .toList();
    Pagination pagination = Pagination.toDto(page);
    return new PageResponse<>(body, pagination);
  }

}
