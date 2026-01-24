package br.com.dio.service;

import br.com.dio.persistence.dao.impl.BoardColumnDAOImpl;
import br.com.dio.persistence.dao.impl.BoardDAOImpl;
import br.com.dio.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class BoardService {

    private final Connection connection;

    public void insert(final BoardEntity entity) throws SQLException{
        var dao = new BoardDAOImpl(connection);
        var boardColumnDAO = new BoardColumnDAOImpl(connection);
        try{
            dao.insert(entity);
            var columns = entity.getBoardColumns().stream().peek(c -> c.setBoard(entity)).toList();
            for(var column: columns){
                boardColumnDAO.insert(column);

            }
            connection.commit();
        }catch(SQLException e){
            connection.rollback();
            throw e;
        }
    }

    public boolean delete(final Long id) throws SQLException{
        var dao = new BoardDAOImpl(connection);
        try {
            if (!dao.exists(id)){
                return false;
            }
            dao.delete(id);
            connection.commit();
            return true;

        }catch(SQLException e){
            connection.rollback();
            throw e;
        }
    }
}
