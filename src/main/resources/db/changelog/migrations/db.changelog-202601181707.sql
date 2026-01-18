--liquibase formatted sql
--changeset claudioneves:202601181708
--comment: cards table create

    CREATE TABLE CARDS(
        id SERIAL PRIMARY KEY ,
        title VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,
        board_column_id BIGINT NOT NULL,
        CONSTRAINT boards_columns__cards_fk FOREIGN KEY(board_column_id) REFERENCES BOARDS_COLUMNS(id)ON DELETE CASCADE
    );

--rollback DROP TABLE CARDS
