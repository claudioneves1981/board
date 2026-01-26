package br.com.dio.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoardEntity extends BaseEntity{

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<BoardColumnEntity> boardColumns = new ArrayList<>();

}
