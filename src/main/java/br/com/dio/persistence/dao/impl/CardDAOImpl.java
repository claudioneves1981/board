package br.com.dio.persistence.dao.impl;

import br.com.dio.dto.CardDetails;
import lombok.AllArgsConstructor;

import java.sql.Connection;

@AllArgsConstructor
public class CardDAOImpl {

    private Connection connection;

    public CardDetails findById(final Long id){

        return null;

    }

}
