# Projeto A3 - SOLUÇÕES COMPUTACIONAIS

## Descrição
Este projeto é um sistema de gerenciamento de alunos e treinos para academias. 
Ele permite o cadastro, atualização, recuperação e gerenciamento de dados de alunos e seus respectivos treinos. 
Utiliza Java com JDBC para acesso ao banco de dados MySQL.

##  Estrutura do Projeto
   BO/
    Aluno.java         # Representa a entidade Aluno
    Treino.java        # Representa a entidade Treino, associada a um Aluno
DAO/
    AlunoDao.java      # DAO para operações CRUD com Aluno
    TreinoDao.java     # DAO para operações CRUD com Treino
   FabricaDao.java    # Fábrica de DAOs que gerencia a conexão com o banco
Main.java              # Classe principal de execução do sistema

##  Funcionalidades

- Cadastro de alunos
- Cadastro de treinos por aluno
- Consulta de alunos e seus treinos
- Atualização de dados
- Uso de DAO e padrão Factory para desacoplamento da lógica de persistência

## Conceitos aplicados:
  - Linguagem JAVA;
  - Orientação a Objetos;
  - Persistência de dados com MYSQL;
  - LIB JDBC e arquitetura MVP com as classes de Entidades e Classes DAO;

## Programas necessários para execução do projeto:
  - MYSQL;
  - Editor de código ou IDE de prefencia (recomenda-se o Eclipse;
  - JAVA 11;
  - Biblioteca Java externa JDBC;

## Como executar o projeto com o Eclipse:
  - Importar repositório para sua máquina;
      - Para clonar o repositório de forma simples pelo Eclipse, primeiramente vamos ativar as views do git que irão auxiliar no processo:
          - ![image](https://github.com/user-attachments/assets/56362583-b636-4c47-ae3f-7f15546d582d)
      - Selecione a View "Git Repositories" e clique em Open:
          - ![image](https://github.com/user-attachments/assets/23565e02-816e-493c-98f6-418814353be7)
      - Para clonar o projeto do GitHub clique no botão abaixo:
          - ![image](https://github.com/user-attachments/assets/08b961cd-e985-4b67-b732-b71286e8a7c4)
      - Clique em "clone url":
          - ![image](https://github.com/user-attachments/assets/408d3366-7b3b-40d8-8161-33b65e5e3271)
      - No campo URL informe o link do projeto no github -> https://github.com/AdrianKauling/A3_SOLUCOES_COMPUTACIONAIS.git e informe o usuário de autenticação, juntamente com o token que você gera dentro do seu perfil do GitHub:
          - ![image](https://github.com/user-attachments/assets/92c79680-6ce2-4979-a714-145b90b1a104)
      - Após isso, clique em next até a ultima página e, por fim, finish;
      -Clique com o botão direito do mouse sobre o repositório:
          - ![image](https://github.com/user-attachments/assets/33f76a1f-1f5e-4e73-a36c-8376fd278c39)
      - Import Project:
        
          - ![image](https://github.com/user-attachments/assets/a76be80e-4c60-423c-8545-a86afc1608e3)
  - Executar o script do mysql no banco de dados;
      - ![image](https://github.com/user-attachments/assets/ff03aad5-6fd4-496d-bc81-c7243c33bb00)
        
      - O script cria o usuário que o sistema usará, cria o banco e as tabelas necessárias. Caso prefirir, voce pode acessar o script e editar o nome de usuário e senha para qual achar melhor.
      - OBS: não esqueça de altarar no arquivo de conexão com banco do projeto:
        
      - ![image](https://github.com/user-attachments/assets/f319b265-d7b9-49f8-a45d-a49e8001d956)

  - Feito isso, é só executar o arquivo main.java dentro da pasta "Application";
      - ![image](https://github.com/user-attachments/assets/7096caca-75cd-4c00-8554-88713b025c23)
    
      - ![image](https://github.com/user-attachments/assets/9ebfbbcf-3c97-4145-a142-b6a32b9a69dc)

##  Autor

- Desenvolvido por: 
- Adrian Kauling dos Santos;
- Henrique de Borba Alves;
- Miguel Soares de Souza;
- Arthur Vignolis Vinter;
