package com.simpleboard.crud;

public interface Converter<ENTITY, REQUEST, RESPONSE> {

  ENTITY toEntity(REQUEST request);

  RESPONSE toDto(ENTITY entity);

}
