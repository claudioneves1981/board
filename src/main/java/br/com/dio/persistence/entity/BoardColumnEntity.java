package br.com.dio.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoardColumnEntity extends BaseEntity {

    private String name;
    private Integer order;
    private BoardColumnKindEnum kind;

    private BoardEntity board = new BoardEntity();
}
