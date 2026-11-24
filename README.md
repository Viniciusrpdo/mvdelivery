# MV.Delivery — Etapa 5
Sistema desktop Java Swing + JDBC + MySQL. Mantém as cinco entidades da Etapa 1 e o fluxo da Etapa 3. Requisitos: JDK 8+, Ant/NetBeans, MySQL Server e Connector/J compatível. MySQL Workbench é cliente de administração, não o servidor.

## Instalação
1. No Workbench conectado ao MySQL, execute `sql/mv_delivery.sql` e depois `sql/dados_exemplo.sql` (uma vez, em banco vazio). O SQL foi reconstruído da documentação, pois o original não foi anexado; compare com o original antes de usar dados existentes.
2. Baixe o Connector/J em https://dev.mysql.com/downloads/connector/j/ e coloque o JAR em `lib/`.
3. Copie `config.example.properties` para `config.properties` e edite com usuário e senha locais. Não publique credenciais.
4. Abra a pasta MVDelivery no NetBeans como projeto Ant freeform, execute Run; alternativa: `ant run` na pasta do projeto.
5. Cadastre cliente, entregador e pedido. Edite status e confira histórico; registre feedback. Feche e reabra para verificar persistência. Consulte tabelas no Workbench.

## Arquitetura
`model` entidades; `repository` interface, memória, JDBC e regras; `app` interface Swing e teste. JDBC usa PreparedStatement e transações para pedido/histórico e exclusão dependente. O modo padrão exige MySQL; `java -cp build/classes br.com.mvdelivery.app.MVDeliveryApp --memoria` permite demonstração sem banco.

## Validação
Compilação e teste em memória realizados. Conexão real, execução dos scripts e persistência no MySQL não foram verificadas neste ambiente (sem servidor/driver). NetBeans não foi executado; projeto inclui metadados Ant freeform. Não há autenticação de produção.

## Publicação
Crie um repositório vazio no GitHub, configure o remoto e execute `git push -u origin main`. Não inclua senhas. Consulte o relatório de evidências da Etapa 5.
