--liquibase formatted sql
--changeset claudioneves:202601181714
--comment: blocks table create

    CREATE TABLE BLOCKS(
        id SERIAL PRIMARY KEY ,
        blocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        block_reason VARCHAR(255) NOT NULL,
        unblocked_at TIMESTAMP NULL,
        unblock_reason VARCHAR(255) NOT NULL,
        card_id BIGINT NOT NULL,
        CONSTRAINT cards__blocks_fk FOREIGN KEY(card_id) REFERENCES CARDS(id)ON DELETE CASCADE
    );

--rollback DROP TABLE BLOCKS
