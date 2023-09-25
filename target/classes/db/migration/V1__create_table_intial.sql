create sequence if not exists "veiculo_seq" start 1 increment 1;
CREATE TABLE IF NOT EXISTS veiculo(
    id int8 NOT NULL DEFAULT nextval('veiculo_seq'),
    veiculo VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    ano int4 NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    vendido BOOLEAN NOT NULL,
    created DATE NOT NULL,
    updated DATE NOT NULL,
    primary key ("id")
);

