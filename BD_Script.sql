create database maintenance_system;
use maintenance_system;


CREATE TABLE Maquina (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
setor VARCHAR(50) NOT NULL,
status Enum('OPERACIONAL', 'EM_MANUTENCAO') NOT NULL 
);
select * from Maquina;
INSERT INTO Maquina (nome, setor, status) VALUES
('Prensa Hidráulica 50T', 'Prensaria', 'OPERACIONAL'),
('Torno CNC K16', 'Usinagem', 'OPERACIONAL'),
('Centro de Usinagem Vertical', 'Usinagem', 'EM_MANUTENCAO'),
('Injetora de Plástico 200T', 'Injeção', 'OPERACIONAL'),
('Robô de Solda FANUC', 'Montagem', 'OPERACIONAL'),
('Esteira Transportadora Principal', 'Logística', 'OPERACIONAL'),
('Empacotadora Automática', 'Embalagem', 'EM_MANUTENCAO'),
('Compressor de Ar 150HP', 'Utilidades', 'OPERACIONAL');

CREATE TABLE Tecnico (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
especialidade VARCHAR(50)
);
select * from Tecnico;
INSERT INTO Tecnico (nome, especialidade) VALUES
('Carlos Silva', 'Eletricista'),
('Ana Paula Souza', 'Automação'),
('Ricardo Oliveira', 'Mecânico'),
('Mariana Costa', 'Instrumentista'),
('Bruno Ferreira', 'Hidráulica'),
('Juliana Almeida', 'Eletrônica'),
('Fernando Lima', 'Mecânico'),
('Patrícia Pereira', 'Soldadora');


CREATE TABLE Peca (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
estoque DOUBLE NOT NULL 
);
select * from Peca;
INSERT INTO Peca (nome, estoque) VALUES
('Parafuso Allen M6', 500),
('Rolamento 6203-ZZ', 80),
('Correia V-Belt A32', 45.5),
('Filtro de Ar Padrão', 120),
('Óleo Lubrificante ISO VG 46 (Litro)', 30),
('Fusível de Vidro 10A', 250),
('Contator Tripolar 25A', 22),
('Sensor de Proximidade Indutivo', 15),
('Válvula Solenoide 5/2 Vias', 10),
('Graxa de Lítio (Kg)', 18.5);

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
FOREIGN KEY (idOrdem) REFERENCES OrdemManutencao(id),
FOREIGN KEY (idPeca) REFERENCES Peca(id)
);
select * from OrdemPeca;