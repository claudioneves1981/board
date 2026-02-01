package br.com.dio.persistence.dao;

import br.com.dio.dto.CardDetailsDTO;
import br.com.dio.persistence.entity.CardEntity;

import java.sql.SQLException;
import java.util.Optional;

public interface CardDAO {

   Optional<CardDetailsDTO> findById(final Long id) throws SQLException;

   void insert(final CardEntity entity) throws SQLException;

   void update(final Long cardId, final Long columnId) throws SQLException;

}
