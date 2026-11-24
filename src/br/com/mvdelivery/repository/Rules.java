package br.com.mvdelivery.repository;
import br.com.mvdelivery.model.*;
public final class Rules {
 private static void req(boolean b,String m){if(!b)throw new IllegalArgumentException(m);}
 private static boolean text(String s){return s!=null&&!s.trim().isEmpty();}
 public static void validate(Repository r,Schema s,Object o)throws Exception {
 switch(s){
 case CLIENTE: Cliente c=(Cliente)o;req(text(c.nome)&&c.nome.length()<=20,"Nome obrigatório, até 20 caracteres.");req(text(c.telefone)&&text(c.endereco),"Telefone e endereço são obrigatórios.");break;
 case ENTREGADOR: Entregador e=(Entregador)o;req(text(e.nome)&&text(e.contato)&&text(e.placaMoto),"Preencha nome, contato e placa.");for(Object x:r.list(s)){Entregador v=(Entregador)x;req(v.idEntregador==e.idEntregador||!v.placaMoto.equalsIgnoreCase(e.placaMoto),"Placa já cadastrada.");}break;
 case PEDIDO: Pedido p=(Pedido)o;req(p.cliente!=null&&r.find(Schema.CLIENTE,p.cliente.idCliente)!=null,"Selecione um cliente válido.");req(p.entregador==null||r.find(Schema.ENTREGADOR,p.entregador.idEntregador)!=null,"Entregador inválido.");req(p.dataPedido!=null&&p.valor!=null&&p.valor.signum()>=0&&p.status!=null&&text(p.enderecoEntrega),"Preencha data, valor não negativo, status e endereço.");break;
 case HISTORICO: HistoricoStatus h=(HistoricoStatus)o;req(h.pedido!=null&&h.status!=null&&h.dataHora!=null,"Histórico incompleto.");break;
 case FEEDBACK: Feedback f=(Feedback)o;req(f.pedido!=null&&r.find(Schema.PEDIDO,f.pedido.idPedido)!=null&&f.dataHora!=null&&f.nota>=1&&f.nota<=5,"Pedido, data e nota de 1 a 5 são obrigatórios.");for(Object x:r.list(s)){Feedback v=(Feedback)x;req(v.idFeedback==f.idFeedback||v.pedido.idPedido!=f.pedido.idPedido,"Pedido já possui feedback.");}break;
 }}
 public static void checkDelete(Repository r,Schema s,int id)throws Exception {if(s==Schema.CLIENTE||s==Schema.ENTREGADOR)for(Object x:r.list(Schema.PEDIDO)){Pedido p=(Pedido)x;req(s==Schema.CLIENTE?p.cliente.idCliente!=id:p.entregador==null||p.entregador.idEntregador!=id,"Registro vinculado a pedido.");}}
}
