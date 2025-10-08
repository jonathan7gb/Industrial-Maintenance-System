# Sistema de Manutenção Industrial

Este projeto é um sistema de manutenção industrial desenvolvido em Java, utilizando técnicas de programação estruturada e orientada a objetos, com acesso a banco de dados via JDBC. O sistema permite o cadastro e gerenciamento de máquinas, técnicos, peças, ordens de manutenção e suas respectivas associações.

## Funcionalidades Principais

- **Cadastrar Máquina**
- **Cadastrar Técnico**
- **Cadastrar Peça**
- **Criar Ordem de Manutenção**
- **Associar Peças à Ordem de Manutenção**
- **Executar Manutenção**
- **Sair**

---

## Descrição Geral

O sistema realiza o cadastro de usuários com autenticação por CPF e senha e permite as seguintes operações:

- Cadastro de máquinas e setores
- Cadastro de técnicos e especialidades
- Cadastro de peças de reposição e controle de estoque
- Criação e execução de ordens de manutenção
- Associação de peças às ordens
- Atualização automática do estoque, status da máquina e status da ordem de manutenção

---

## Visão Geral do Sistema

- **Máquinas:** cadastro, setor, status operacional (`OPERACIONAL` ou `EM_MANUTENCAO`)
- **Técnicos:** cadastro e especialidade
- **Peças de reposição:** cadastro e estoque (não pode ser negativo)
- **Ordens de manutenção:** criação, execução, associação de peças
- **Fluxo de execução:** atualização de estoque, status da máquina e da ordem

---

## Requisitos Técnicos Avaliados

- Uso de **JDBC** para conexão e manipulação do banco de dados
- Comandos **SQL** para resolução dos problemas
- Aplicação de **Orientação a Objetos** (Model + DAO)
- Implementação correta das **regras de negócio**

---

## Estrutura Sugerida do Menu

```
1️⃣ Cadastrar Máquina
2️⃣ Cadastrar Técnico
3️⃣ Cadastrar Peça
4️⃣ Criar Ordem de Manutenção
5️⃣ Associar Peças à Ordem
6️⃣ Executar Manutenção
0️⃣ Sair
```

---

## Esquema SQL Sugerido

```sql
-- Tabela de Máquinas
CREATE TABLE Maquina (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    setor VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL -- OPERACIONAL / EM_MANUTENCAO
);

-- Tabela de Técnicos
CREATE TABLE Tecnico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50)
);

-- Tabela de Peças
CREATE TABLE Peca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    estoque DOUBLE NOT NULL
);

-- Tabela de Ordens de Manutenção
CREATE TABLE OrdemManutencao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idMaquina INT NOT NULL,
    idTecnico INT NOT NULL,
    dataSolicitacao DATE NOT NULL,
    status VARCHAR(20) NOT NULL, -- PENDENTE / EXECUTADA / CANCELADA
    FOREIGN KEY (idMaquina) REFERENCES Maquina(id),
    FOREIGN KEY (idTecnico) REFERENCES Tecnico(id)
);

-- Tabela de Peças utilizadas em cada ordem
CREATE TABLE OrdemPeca (
    idOrdem INT NOT NULL,
    idPeca INT NOT NULL,
    quantidade DOUBLE NOT NULL,
    PRIMARY KEY (idOrdem, idPeca),
    FOREIGN KEY (idOrdem) REFERENCES OrdemManutencao(id),
    FOREIGN KEY (idPeca) REFERENCES Peca(id)
);
```

---

## Fluxo Detalhado das Funcionalidades

### 1️⃣ Cadastrar Máquina

- Solicitar: nome e setor da máquina (obrigatórios)
- **Validações:** Não cadastrar máquina duplicada no mesmo setor
- **Inserção:** Máquina inicia com status `OPERACIONAL`
- **Confirmação:** Mensagem de sucesso ou erro

### 2️⃣ Cadastrar Técnico

- Solicitar: nome (obrigatório) e especialidade
- **Validações:** Evitar duplicidade
- **Inserção:** Sem restrição de status
- **Confirmação:** Mensagem de sucesso ou erro

### 3️⃣ Cadastrar Peça

- Solicitar: nome (obrigatório) e quantidade inicial de estoque (≥ 0)
- **Validações:** Evitar duplicidade
- **Inserção:** Estoque inicial definido pelo usuário
- **Confirmação:** Mensagem de sucesso ou erro

### 4️⃣ Criar Ordem de Manutenção

- Selecionar máquina (`OPERACIONAL`) e técnico
- Data de solicitação = data atual
- Status inicial = `PENDENTE`
- **Inserção:** Atualiza status da máquina para `EM_MANUTENCAO`
- **Confirmação:** Mensagem de sucesso ou erro

### 5️⃣ Associar Peças à Ordem

- Selecionar ordem (`PENDENTE`)
- Selecionar peças e informar quantidade (≥ 0)
- **Validações:** Ordem só pode receber peças se estiver `PENDENTE`
- **Inserção:** Permite adicionar várias peças por ordem
- **Confirmação:** Mensagem de sucesso ou erro

### 6️⃣ Executar Manutenção

- Selecionar ordem (`PENDENTE`)
- **Verificar estoque:** Para cada peça, comparar estoque com quantidade
- **Se estoque insuficiente:** Mensagem de erro, aborta execução
- **Se estoque suficiente:**
    - Atualiza estoque, diminui quantidade usada
    - Atualiza ordem para `EXECUTADA`
    - Atualiza máquina para `OPERACIONAL`
- **Confirmação:** Mensagem de sucesso ou erro

---

## Regras de Negócio Gerais

- Não é permitido cadastro/uso de peças com estoque negativo
- Máquinas só podem receber ordens se estiverem `OPERACIONAL`
- Ordem só recebe peças se estiver `PENDENTE`
- Todas as peças devem ter estoque suficiente ao executar manutenção
- Ordem muda para `EXECUTADA` após execução
- Máquina volta para `OPERACIONAL` após execução de ordem

---

## Encerramento

- **0️⃣ Sair:** Encerra a aplicação

---

## Instruções Gerais

- Implemente os fluxos conforme descrito acima, utilizando **Java**, **JDBC** e **SQL**.
- Siga os princípios de orientação a objetos: utilize Modelos e DAOs para cada entidade.
- Valide todas as entradas e regras de negócio antes de persistir informações no banco de dados.
- Utilize mensagens claras de sucesso ou erro para facilitar a operação do usuário.
