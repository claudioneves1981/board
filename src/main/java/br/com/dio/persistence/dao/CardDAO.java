package br.com.dio.persistence.dao;

import br.com.dio.dto.CardDetails;

public interface CardDAO {

    CardDetails findById(final Long id);
}
