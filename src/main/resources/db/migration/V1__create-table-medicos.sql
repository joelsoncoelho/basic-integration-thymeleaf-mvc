CREATE TABLE IF NOT EXISTS `medicos` (

    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` varchar(20),
    `email` varchar(50),
    `crm` varchar(6) NOT NULL,
    `especialidade` varchar(50) NOT NULL,
    `logradouro` varchar(100) NOT NULL,
    `bairro` varchar(100) NOT NULL,
    `cep` varchar(9) NOT NULL,
    `complemento` varchar(100),
    `numero` varchar(20),
    `uf` char(2) NOT NULL,
    `cidade` varchar(100) NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;