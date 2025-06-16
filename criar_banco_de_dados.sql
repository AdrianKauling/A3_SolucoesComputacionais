-- Criação do usuário e concessão de privilégios
CREATE USER 'db_adm_academia'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON *.* TO 'db_adm_academia'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- Criação do banco de dados
CREATE DATABASE academia;
USE academia;

-- Criação da tabela Aluno
CREATE TABLE Alunos (
    id INT PRIMARY KEY auto_increment,
    nome VARCHAR(100),
    cpf VARCHAR(14),
    dataNasc DATE,
    telefone VARCHAR(15),
    email VARCHAR(100)
);

-- Criação da tabela Treino
CREATE TABLE Treinos (
    id INT PRIMARY KEY auto_increment,
    tipoDeTreino VARCHAR(50),
    descricao text,
    duracao int,
    dataDeInicio DATE,
    id_aluno INT,
    FOREIGN KEY (id_aluno) REFERENCES Alunos(id)
);
