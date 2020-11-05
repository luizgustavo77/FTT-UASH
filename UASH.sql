USE FTT;

CREATE TABLE `Endereco` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `CEP` varchar(20),
  `Rua` varchar(50),
  `Numero` varchar(5),
  `Estado` varchar(20),
  `Cidade` varchar(20),
  `Complemento` varchar(20)
);

CREATE TABLE `TabelaGeral` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Descricao` varchar(20)
);

CREATE TABLE `ItemTabelaGeral` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Descricao` varchar(20),
  `Sigla` varchar(10),
  `TabelaGeralId` int
);

CREATE TABLE `Pagamento` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Valor` float,
  `Servico` int,
  `Descricao` varchar(50),
  `Comentario` varchar(255)
);

CREATE TABLE `Pessoa` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `FotoId` int,
  `Nome` varchar(20),
  `Telefone` varchar(20),
  `Email` varchar(20),
  `CPF` varchar(20),
  `Sexo` varchar(1),
  `EnderecoId` int
);

CREATE TABLE `Foto` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Arquivo` varbinary(255),
  `Extensao` varchar(5),
  `Nome` varchar(100)
);

CREATE TABLE `Acesso` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Usuario` varchar(20),
  `Senha` varchar(20),
  `PessoaId` int,
  `PerfilId` int
);

CREATE TABLE `Perfil` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Tipo` int,
  `Servico` int,
  `Disponivel` int
);

CREATE TABLE `Equipamento` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Modelo` varchar(20),
  `Marca` varchar(20),
  `Comentario` varchar(255),
  `Colaborador` int,
  `Situacao` int,
  `Funcoes` int
);

CREATE TABLE `Orcamento` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `PagamentoId` int,
  `EquipamentoId` int,
  `TrabalhoId` int,
  `Lavagens` int,
  `FormaPagamento` int,
  `Pago` bool,
  `Comentario` varchar(50)
);

CREATE TABLE `Trabalho` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Colaborador` int,
  `Usuario` int,
  `DataAbertura` datetime,
  `DataFechamento` datetime,
  `Status` int,
  `NotaServico` int
);

CREATE TABLE `Comentario` (
  `Id` int PRIMARY KEY AUTO_INCREMENT,
  `Descricao` varchar(20),
  `TrabalhoId` int,
  `Usuario` int
);

ALTER TABLE `ItemTabelaGeral` ADD FOREIGN KEY (`TabelaGeralId`) REFERENCES `TabelaGeral` (`Id`);

ALTER TABLE `Pagamento` ADD FOREIGN KEY (`Servico`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Pessoa` ADD FOREIGN KEY (`FotoId`) REFERENCES `Foto` (`Id`);

ALTER TABLE `Pessoa` ADD FOREIGN KEY (`EnderecoId`) REFERENCES `Endereco` (`Id`);

ALTER TABLE `Acesso` ADD FOREIGN KEY (`PessoaId`) REFERENCES `Pessoa` (`Id`);

ALTER TABLE `Acesso` ADD FOREIGN KEY (`PerfilId`) REFERENCES `Perfil` (`Id`);

ALTER TABLE `Perfil` ADD FOREIGN KEY (`Tipo`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Perfil` ADD FOREIGN KEY (`Servico`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Perfil` ADD FOREIGN KEY (`Disponivel`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Equipamento` ADD FOREIGN KEY (`Colaborador`) REFERENCES `Perfil` (`Id`);

ALTER TABLE `Equipamento` ADD FOREIGN KEY (`Situacao`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Equipamento` ADD FOREIGN KEY (`Funcoes`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Orcamento` ADD FOREIGN KEY (`PagamentoId`) REFERENCES `Pagamento` (`Id`);

ALTER TABLE `Orcamento` ADD FOREIGN KEY (`EquipamentoId`) REFERENCES `Equipamento` (`Id`);

ALTER TABLE `Orcamento` ADD FOREIGN KEY (`TrabalhoId`) REFERENCES `Trabalho` (`Id`);

ALTER TABLE `Orcamento` ADD FOREIGN KEY (`FormaPagamento`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Trabalho` ADD FOREIGN KEY (`Colaborador`) REFERENCES `Acesso` (`Id`);

ALTER TABLE `Trabalho` ADD FOREIGN KEY (`Usuario`) REFERENCES `Acesso` (`Id`);

ALTER TABLE `Trabalho` ADD FOREIGN KEY (`Status`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Trabalho` ADD FOREIGN KEY (`NotaServico`) REFERENCES `ItemTabelaGeral` (`Id`);

ALTER TABLE `Comentario` ADD FOREIGN KEY (`TrabalhoId`) REFERENCES `Acesso` (`Id`);

ALTER TABLE `Comentario` ADD FOREIGN KEY (`Usuario`) REFERENCES `Acesso` (`Id`);
