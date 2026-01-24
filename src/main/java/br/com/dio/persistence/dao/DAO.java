package br.com.dio.persistence.dao;

import java.util.Optional;

public interface DAO<T>{

    Optional<T> findById(Long id);

    void insert(T entity);

    void delete(Long id);
}
