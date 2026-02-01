package br.com.dio.service;

import br.com.dio.dto.BoardColumnInfoDTO;
import br.com.dio.dto.CardDetailsDTO;
import br.com.dio.exception.CardBlockException;
import br.com.dio.exception.CardFinishedException;
import br.com.dio.exception.EntityNotFoundException;
import br.com.dio.persistence.dao.impl.BlockDAOImpl;
import br.com.dio.persistence.dao.impl.CardDAOImpl;
import br.com.dio.persistence.entity.BoardColumnKindEnum;
import br.com.dio.persistence.entity.CardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static br.com.dio.persistence.entity.BoardColumnKindEnum.CANCEL;
import static br.com.dio.persistence.entity.BoardColumnKindEnum.FINAL;

@AllArgsConstructor
public class CardService {

    private final Connection connection;

    public CardEntity create(final CardEntity entity) throws SQLException{
        try{

            var dao = new CardDAOImpl(connection);
            dao.insert(entity);
            connection.commit();
            return entity;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }

    public void moveToNextColumn(final Long cardId, List<BoardColumnInfoDTO> boardColumnsInfo) throws SQLException{
        try{

           var dao = new CardDAOImpl(connection);
           var optional = dao.findById(cardId);
           var dto = optional.orElseThrow(
                   ()-> new EntityNotFoundException("O card de id %s não foi encontrado".formatted(cardId))
           );
           if(dto.blocked()){
               var message = "O card %s está bloqueado, é necessário desbloquea-lo para mover".formatted(cardId);
               throw new CardBlockException(message);
           }
           var currentColumn = boardColumnsInfo.stream()
                   .filter(bc -> bc.id().equals(dto.columnId()))
                   .findFirst()
                   .orElseThrow(()-> new IllegalStateException("O card informado pertence a outro board"));
           if(currentColumn.kind().equals(FINAL)){
               throw new CardFinishedException("O card já foi finalizado");
           }
           var nextColumn = boardColumnsInfo.stream()
                   .filter(bc -> bc.order() == currentColumn.order() + 1)
                   .findFirst()
                   .orElseThrow(() -> new IllegalStateException("O Card está cancelado"));
           dao.update(nextColumn.id(),cardId);
           connection.commit();


       }catch(SQLException ex){
           connection.rollback();
           throw ex;

       }

    }

    public void cancel(final Long cardId, final Long cancelColumnId, List<BoardColumnInfoDTO> boardColumnsInfo) throws SQLException {
        try{

            var dao = new CardDAOImpl(connection);
            var optional = dao.findById(cardId);
            var dto = optional.orElseThrow(
                    ()-> new EntityNotFoundException("O card de id %s não foi encontrado".formatted(cardId))
            );
            if(dto.blocked()){
                var message = "O card %s está bloqueado, é necessário desbloquea-lo para mover".formatted(cardId);
                throw new CardBlockException(message);
            }
            var currentColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.id().equals(dto.columnId()))
                    .findFirst()
                    .orElseThrow(()-> new IllegalStateException("O card informado pertence a outro board"));
            if(currentColumn.kind().equals(FINAL)){
                throw new CardFinishedException("O card já foi finalizado");
            }
           boardColumnsInfo.stream()
                    .filter(bc -> bc.order() == currentColumn.order() + 1)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("O Card está cancelado"));
            dao.update(cancelColumnId,cardId);
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            throw ex;

        }


    }

    public void block(final Long id, final String reason, List<BoardColumnInfoDTO> boardColumnsInfo) throws SQLException{

        try{

            var dao = new CardDAOImpl(connection);
            var optional = dao.findById(id);
            var dto = optional.orElseThrow(
                    ()-> new EntityNotFoundException("O card de id %s não foi encontrado".formatted(id))
            );
            if(dto.blocked()){
                var message = "O card %s já está bloqueado,".formatted(id);
                throw new CardBlockException(message);
            }
            var currentColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.id().equals(dto.columnId()))
                    .findFirst()
                    .orElseThrow();
            if(currentColumn.kind().equals(FINAL) || currentColumn.kind().equals(CANCEL)){

                var message = "O card está em um coluna do tipo %s e não pode ser bloqueado"
                        .formatted(currentColumn.kind());
                throw new IllegalStateException(message);

            }
            var blockDAO = new BlockDAOImpl(connection);
            blockDAO.block(reason, id);
            connection.commit();

        }catch(SQLException ex){
            connection.rollback();
            throw ex;
        }



    }



}
