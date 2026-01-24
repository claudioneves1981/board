package br.com.dio.persistence.dao.impl;

import br.com.dio.persistence.dao.BoardDAO;
import br.com.dio.persistence.entity.BoardColumnEntity;
import br.com.dio.persistence.entity.BoardEntity;
import com.mysql.cj.jdbc.StatementImpl;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardDAOImpl implements BoardDAO {

   private final Connection connection;

   @Override
   public void insert(final BoardEntity entity){

       var sql = "INSERT INTO BOARDS(name) values(?);";
       try(var statement = connection.prepareStatement(sql)) {
           statement.setString(1, entity.getName());
           statement.executeUpdate();
           if(statement instanceof StatementImpl impl){
               entity.setId(impl.getLastInsertID());
           }



       }catch(Exception e){
           System.err.printf("Erro ao inserir board. %s\n", e);
       }


   }

    @Override
    public void delete(final Long id) {

        var sql = "DELETE FROM BOARDS WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)) {
            statement.setLong(1,id);
            statement.executeUpdate();
        }catch(SQLException e){

            System.err.println(e.getMessage());

        }

    }

    @Override
    public Optional<BoardEntity> findById(final Long id) {
        var sql = "SELECT id, name FROM BOARDS WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if(resultSet.next()){
                var entity = new BoardEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                return Optional.of(entity);

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());

        }

        return Optional.empty();
    }

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM BOARDS WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeQuery();
            return statement.getResultSet().next();
        }
    }

}
