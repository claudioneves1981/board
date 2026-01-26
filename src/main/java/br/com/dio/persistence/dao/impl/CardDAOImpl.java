package br.com.dio.persistence.dao.impl;

import br.com.dio.dto.CardDetails;
import br.com.dio.persistence.dao.CardDAO;
import lombok.AllArgsConstructor;

import java.sql.Connection;

@AllArgsConstructor
public class CardDAOImpl implements CardDAO {

    private Connection connection;

    @Override
    public CardDetails findById(final Long id){

        return null;

    }

}
