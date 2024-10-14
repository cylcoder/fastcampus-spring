package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;

import java.util.*;

public abstract class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

    private List<T> dataList = new ArrayList<>();

    private static long index = 1;

    @Override
    public T save(T data) {
        if (Objects.isNull(data)) {
            throw new NullPointerException("Data is null");
        }

        Optional<T> prevData = dataList.stream().filter(d -> d.getId().equals(data.getId())).findFirst();

        if (prevData.isPresent()) {
            // DB에 값이 있는 경우 -> update
            dataList.remove(prevData.get());
            dataList.add(data);
        } else {
            // DB에 값이 없는 경우 -> create
            data.setId(index++);
            dataList.add(data);
        }

        return data;
    }

    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    @Override
    public List<T> findAll() {
        return dataList.stream().sorted(Comparator.comparing(Entity::getId)).toList();
    }

    @Override
    public void delete(ID id) {
        dataList.removeIf(d -> d.getId().equals(id));
    }

}
