CREATE DATABASE test DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE test;

CREATE TABLE board (
    chess_position VARCHAR(12) NOT NULL,
    piece_type VARCHAR(64) NOT NULL,
    color VARCHAR(64) NOT NULL
);
