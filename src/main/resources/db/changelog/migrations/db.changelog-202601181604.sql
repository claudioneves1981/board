--liquibase formatted sql
--changeset claudioneves:202601181604
--comment: boards table create

    CREATE TABLE BOARDS(
        id serial primary key,
        name VARCHAR(255) NOT NULL
    );

--rollback DROP TABLE BOARDS
