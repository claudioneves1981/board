package br.com.dio.persistence.dao;

import br.com.dio.dto.CardDetailsDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface CardDAO {

   Optional<CardDetailsDTO> findById(final Long id) throws SQLException;

}
