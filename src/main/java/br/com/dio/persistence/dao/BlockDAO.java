package br.com.dio.persistence.dao;

import java.sql.SQLException;

public interface BlockDAO {

    void block(final String reason, final Long cardId) throws SQLException;
}
