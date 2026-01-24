--liquibase formatted sql
--changeset claudioneves:202601181604
--comment: boards table create

    CREATE TABLE BOARDS(
        id BIGINT AUTO_INCREMENT primary key ,
        name VARCHAR(255) NOT NULL
    )
ENGINE=InnoDB;

--rollback DROP TABLE BOARDS
