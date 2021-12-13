package com.itsol.recruit_managerment.service;


import java.util.List;

public interface Task<T> {
    List<T> getAll();

    T save(T t);

    T getById(Long id);

    void deleteById(Long id);
}
