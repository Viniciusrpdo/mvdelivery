package br.com.mvdelivery.repository;
import br.com.mvdelivery.model.*;
import java.lang.reflect.*;
public enum Schema {
 CLIENTE(Cliente.class,"cliente","Clientes","id_cliente,nome,telefone,endereco,observacoes","ID,Nome,Telefone,Endereço,Observações"),
 ENTREGADOR(Entregador.class,"entregador","Entregadores","id_entregador,nome,contato,placa_moto,disponivel","ID,Nome,Contato,Placa da moto,Disponível"),
 PEDIDO(Pedido.class,"pedido","Pedidos","id_pedido,id_cliente,id_entregador,data_pedido,valor,status,endereco_entrega","ID,Cliente,Entregador,Data do pedido,Valor,Status,Endereço de entrega"),
 HISTORICO(HistoricoStatus.class,"historico_status","Histórico","id_hist,id_pedido,status,data_hora,observacoes","ID,Pedido,Status,Data/hora,Observações"),
 FEEDBACK(Feedback.class,"feedback","Feedback","id_feedback,id_pedido,nota,comentario,data_hora","ID,Pedido,Nota,Comentário,Data/hora");
 public final Class<?> type; public final String table,title; public final String[] columns,labels; public final Field[] fields;
 Schema(Class<?> t,String table,String title,String columns,String labels){this.type=t;this.table=table;this.title=title;this.columns=columns.split(",");this.labels=labels.split(",");this.fields=new Field[this.columns.length];try{for(int i=0;i<this.columns.length;i++){String col=this.columns[i];StringBuilder name=new StringBuilder();boolean upper=false;for(char ch:col.toCharArray()){if(ch=='_'){upper=true;continue;}name.append(upper?Character.toUpperCase(ch):ch);upper=false;}String n=name.toString();if(i>0&&n.equals("idCliente"))n="cliente";if(i>0&&n.equals("idEntregador"))n="entregador";if(i>0&&n.equals("idPedido"))n="pedido";this.fields[i]=t.getField(n);}}catch(NoSuchFieldException ex){throw new IllegalStateException(ex);}}
 public Object create() throws Exception{return type.getDeclaredConstructor().newInstance();}
 public int id(Object o) throws Exception{return fields[0].getInt(o);}
 public static Schema of(Class<?> t){for(Schema s:values())if(s.type==t)return s;return null;}
 public Object copy(Object o) throws Exception{Object n=create();for(Field f:fields)f.set(n,f.get(o));return n;}
}
