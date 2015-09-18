package io.confluent.rest.examples.helloworld.dao;

import io.confluent.rest.examples.helloworld.entity.HelloResponse;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


public class HelloWorldDao {

  private Cluster cluster;

  public HelloWorldDao(String db) {
    cluster = Cluster.builder().addContactPoint(db).build();
  }
  
  public HelloResponse save(HelloResponse response) {
    Session session = cluster.connect("evo");
    ResultSet rs = session.execute("INSERT INTO hello (nombre, apellido, email, DNI) values ("
        + "','" + response.getNombre() 
        + "','" + response.getApellido() 
        + "','" + response.getEmail()
        + "','" + response.getDNI()
        + "')");
     
    return response;

  }
  
  public List<HelloResponse> listAll() {
    Session session = cluster.connect("evo");
    ResultSet rs = session.execute("SELECT nombre, apellido, email, DNI FROM hello");
    List<HelloResponse> res = new ArrayList<HelloResponse>();
    Iterator<Row> it = rs.iterator();
    
    while(it.hasNext()) {
      Row row = it.next();
      HelloResponse aux = new HelloResponse(row.getString(0), row.getString(1), row.getString(3), row.getString(2));
      res.add(aux);
    }
 
    return res;
  }

  /*
  public List<HelloResponse> findByName(String name) {
    em = emf.createEntityManager();
    Query q = em.createQuery("SELECT hr.nombre, hr.apellido, hr.email, hr.DNI FROM HelloResponse hr where hr.nombre=:name");
    q.setParameter("name", name);
    List<HelloResponse> responses = q.getResultList();
    
    return responses;
  }  

  public List<HelloResponse> findByNameAndSurname(String name, String surname) {
    em = emf.createEntityManager();
    Query q = em.createQuery("SELECT hr.nombre, hr.apellido, hr.email, hr.DNI FROM HelloResponse hr where hr.nombre=:name and hr.apellido=:surname");
    q.setParameter("name", name).setParameter("surname", surname);
    List<HelloResponse> responses = q.getResultList();
    
    return responses;
  }
  */
  
  public HelloResponse findByDNI(String dni) {
    Session session = cluster.connect("evo");
    ResultSet rs = session.execute("SELECT nombre, apellido, email, DNI FROM hello WHERE DNI=" + dni);
    List<HelloResponse> res = new ArrayList<HelloResponse>();
    Iterator<Row> it = rs.iterator();
    
    while(it.hasNext()) {
      Row row = it.next();
      HelloResponse aux = new HelloResponse(row.getString(0), row.getString(1), row.getString(3), row.getString(2));
      res.add(aux);
    }
 
    return res.get(0);
  }  

}
