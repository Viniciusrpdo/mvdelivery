-- Reconstrução baseada na documentação da Etapa 1. Tipos e comprimentos não documentados são premissas.
CREATE DATABASE IF NOT EXISTS mv_delivery CHARACTER SET utf8mb4;
USE mv_delivery;
CREATE TABLE IF NOT EXISTS cliente (id_cliente INT AUTO_INCREMENT PRIMARY KEY,nome VARCHAR(20) NOT NULL,telefone VARCHAR(30) NOT NULL,endereco VARCHAR(255) NOT NULL,observacoes TEXT) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS entregador (id_entregador INT AUTO_INCREMENT PRIMARY KEY,nome VARCHAR(100) NOT NULL,contato VARCHAR(30) NOT NULL,placa_moto VARCHAR(10) NOT NULL UNIQUE,disponivel BOOLEAN NOT NULL DEFAULT TRUE) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS pedido (id_pedido INT AUTO_INCREMENT PRIMARY KEY,id_cliente INT NOT NULL,id_entregador INT NULL,data_pedido DATETIME NOT NULL,valor DECIMAL(10,2) NOT NULL,status VARCHAR(20) NOT NULL,endereco_entrega VARCHAR(255) NOT NULL,FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),FOREIGN KEY(id_entregador) REFERENCES entregador(id_entregador)) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS historico_status (id_hist INT AUTO_INCREMENT PRIMARY KEY,id_pedido INT NOT NULL,status VARCHAR(20) NOT NULL,data_hora DATETIME NOT NULL,observacoes TEXT,FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido)) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS feedback (id_feedback INT AUTO_INCREMENT PRIMARY KEY,id_pedido INT NOT NULL UNIQUE,nota INT NOT NULL,comentario TEXT,data_hora DATETIME NOT NULL,FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido)) ENGINE=InnoDB;
