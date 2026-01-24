package br.com.dio.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CardEntity extends BaseEntity{

    //private Long id;
    private String title;
    private String description;
}
