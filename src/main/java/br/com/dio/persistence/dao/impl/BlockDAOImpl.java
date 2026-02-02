package br.com.dio.persistence.dao.impl;

import br.com.dio.persistence.dao.BlockDAO;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import static br.com.dio.persistence.converter.OffsetDateTimeConverter.toTimestamp;

@AllArgsConstructor
public class BlockDAOImpl implements BlockDAO {

    private final Connection connection;

    @Override
    public void block(final String reason, final Long cardId) throws SQLException {
        var sql = "INSERT INTO BLOCKS(blocked_at, block_reason, card_id) VALUES(?,?,?);";
        prepareStatementBlock(reason, cardId, sql);
    }

    @Override
    public void unblock(final String reason, final Long cardId) throws SQLException{
        var sql = "UPDATE BLOCKS SET unblocked_at = ?, unblock_reason = ? WHERE card_id = ? AND unblock_reason IS NULL;";
        prepareStatementBlock(reason, cardId, sql);
    }

    private void prepareStatementBlock(String reason, Long cardId, String sql) throws SQLException {
        try(var statement = connection.prepareStatement(sql)){
            var i = 1;
            statement.setTimestamp(i++, toTimestamp(OffsetDateTime.now()));
            statement.setString(i++, reason);
            statement.setLong(i, cardId);
            statement.executeUpdate();


        }
    }

}
