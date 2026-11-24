USE mv_delivery;
-- Execute uma vez em banco de demonstração vazio.
START TRANSACTION;
INSERT INTO cliente(nome,telefone,endereco,observacoes) VALUES ('Ana Souza','31999990001','Rua A, 10 - BH','Exemplo'),('Pedro Lima','31999990003','Rua B, 20 - BH',NULL);
SET @cliente=LAST_INSERT_ID();
INSERT INTO entregador(nome,contato,placa_moto,disponivel) VALUES ('João Silva','31999990002','ABC1D23',TRUE);
SET @entregador=LAST_INSERT_ID();
INSERT INTO pedido(id_cliente,id_entregador,data_pedido,valor,status,endereco_entrega) VALUES (@cliente,@entregador,NOW(),25.00,'ENTREGUE','Rua C, 30 - BH');
SET @pedido=LAST_INSERT_ID();
INSERT INTO historico_status(id_pedido,status,data_hora,observacoes) VALUES (@pedido,'PENDENTE',NOW(),'Abertura'),(@pedido,'EM_ROTA',NOW(),'Saída'),(@pedido,'ENTREGUE',NOW(),'Conclusão');
INSERT INTO feedback(id_pedido,nota,comentario,data_hora) VALUES (@pedido,5,'Entrega concluída',NOW());
COMMIT;
