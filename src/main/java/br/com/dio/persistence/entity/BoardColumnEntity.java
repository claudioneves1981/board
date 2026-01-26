package br.com.dio.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoardColumnEntity extends BaseEntity {

    private String name;
    private Integer order;
    private BoardColumnKindEnum kind;

    private BoardEntity board = new BoardEntity();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CardEntity> cards = new ArrayList<>();
}
