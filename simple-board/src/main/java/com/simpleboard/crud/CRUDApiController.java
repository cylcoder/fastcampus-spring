package com.simpleboard.crud;

import com.simpleboard.common.Api;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public abstract class CRUDApiController<ENTITY, REQUEST, RESPONSE>  {

  private final CRUDService<ENTITY, REQUEST, RESPONSE> crudService;

  @PostMapping
  public Api<RESPONSE> save(@RequestBody @Valid REQUEST dto) {
    return Api.ok(crudService.save(dto));
  }

  @GetMapping("/{id}")
  public Api<RESPONSE> findById(@PathVariable Long id) {
    return Api.ok(crudService.findById(id));
  }

  @PutMapping
  public Api<RESPONSE> update(@RequestBody @Valid REQUEST dto) {
    return Api.ok(crudService.update(dto));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    crudService.deleteById(id);
  }

  @GetMapping
  public Api<List<RESPONSE>> findAll(@PageableDefault Pageable pageable) {
    PageResponse<RESPONSE> pageResponse = crudService.findAll(pageable);
    return Api.ok(pageResponse.body(), pageResponse.pagination());
  }

}
