package br.com.dio.persistence.dao;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T>{

    Optional<T> findById(Long id) throws SQLException;

    void insert(T entity) throws SQLException;

}
