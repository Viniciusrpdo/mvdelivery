package br.com.mvdelivery.repository;
import java.util.*; import br.com.mvdelivery.model.*; import java.time.*;
public class MemoryRepository implements Repository {
 private final Map<Schema,List<Object>> data=new EnumMap<>(Schema.class); private final Map<Schema,Integer> seq=new EnumMap<>(Schema.class);
 public MemoryRepository(){for(Schema s:Schema.values()){data.put(s,new ArrayList<>());seq.put(s,0);}}
 public List<Object> list(Schema s)throws Exception {List<Object> out=new ArrayList<>();for(Object o:data.get(s))out.add(s.copy(o));return out;}
 public void save(Schema s,Object o)throws Exception {int id=s.id(o); Object old=find(s,id); Rules.validate(this,s,o); if(id==0){id=seq.get(s)+1;seq.put(s,id);s.fields[0].setInt(o,id);} final int key=id; for(Iterator<Object> it=data.get(s).iterator();it.hasNext();)if(s.id(it.next())==key)it.remove(); data.get(s).add(s.copy(o)); if(s==Schema.PEDIDO&&(old==null||((Pedido)old).status!=((Pedido)o).status)){HistoricoStatus h=new HistoricoStatus();h.pedido=(Pedido)o;h.status=h.pedido.status;h.dataHora=LocalDateTime.now();h.observacoes="Registro automático";save(Schema.HISTORICO,h);}}
 public void delete(Schema s,int id)throws Exception {Rules.checkDelete(this,s,id); if(s==Schema.PEDIDO){for(Schema child:new Schema[]{Schema.HISTORICO,Schema.FEEDBACK})for(Object o:list(child))if(((Pedido)child.fields[1].get(o)).idPedido==id)delete(child,child.id(o));} for(Iterator<Object> it=data.get(s).iterator();it.hasNext();)if(s.id(it.next())==id)it.remove();}
}
