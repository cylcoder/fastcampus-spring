package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class SimpleDataRepository<T extends Entity, ID> implements DataRepository<T, ID> {

  private List<T> dataList = new ArrayList<>();

  private static long index = 0;

  @Override
  public T save(T data) {
    if (Objects.isNull(data)) {
      throw new RuntimeException("Data is null");
    }

    Optional<T> optionalData = dataList.stream()
        .filter(e -> e.getId().equals(data.getId()))
        .findFirst();

    if (optionalData.isPresent()) {
      dataList.remove(optionalData.get());
      dataList.add(data);
    } else {
      data.setId(index++);
      dataList.add(data);
    }

    return data;
  }

  @Override
  public List<T> findAll() {
    return dataList.stream()
        .sorted(Comparator.comparing(Entity::getId))
        .toList();
  }

  @Override
  public Optional<T> findById(ID id) {
    return dataList.stream()
        .filter(e -> e.getId().equals(id))
        .findAny();
  }

  @Override
  public void delete(ID id) {
    dataList.stream()
        .filter(e1 -> e1.getId().equals(id))
        .findAny()
        .ifPresent(e -> dataList.remove(e));
  }

}
