create database maintenance_system;
use maintenance_system;


CREATE TABLE Maquina (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
setor VARCHAR(50) NOT NULL,
status Enum('OPERACIONAL', 'EM_MANUTENCAO') NOT NULL 
);
select * from Maquina;

CREATE TABLE Tecnico (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
especialidade VARCHAR(50)
);
select * from Tecnico;


CREATE TABLE Peca (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
estoque DOUBLE NOT NULL 
);
select * from Peca;


CREATE TABLE OrdemManutencao (
id INT PRIMARY KEY AUTO_INCREMENT,

idMaquina INT NOT NULL,
idTecnico INT NOT NULL,
dataSolicitacao DATETIME DEFAULT CURRENT_TIMESTAMP,
status Enum('PENDENTE', 'EXECUTADA', 'CANCELADA') NOT NULL, 
FOREIGN KEY (idMaquina) REFERENCES Maquina(id),
FOREIGN KEY (idTecnico) REFERENCES Tecnico(id)
);
select * from OrdemManutencao;

CREATE TABLE OrdemPeca (
idOrdem INT NOT NULL,
idPeca INT NOT NULL,
quantidade DOUBLE NOT NULL,
PRIMARY KEY (idOrdem, idPeca),
FOREIGN KEY (idOrdem) REFERENCES OrdemManutencao(id),
FOREIGN KEY (idPeca) REFERENCES Peca(id)
);
select * from OrdemPeca;