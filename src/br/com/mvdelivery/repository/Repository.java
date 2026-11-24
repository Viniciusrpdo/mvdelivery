package br.com.mvdelivery.repository;
import java.util.*;
public interface Repository {
 List<Object> list(Schema s) throws Exception;
 void save(Schema s,Object o) throws Exception;
 void delete(Schema s,int id) throws Exception;
 default Object find(Schema s,int id) throws Exception { for(Object o:list(s))if(s.id(o)==id)return o;return null; }
}
