package io.confluent.rest.examples.helloworld.dao;

import io.confluent.rest.examples.helloworld.entity.HelloResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.impetus.client.cassandra.common.CassandraConstants;

public class HelloWorldDao {

  public EntityManager em;
  public EntityManagerFactory emf;

  public HelloWorldDao(String persistenceUnitName) {
    if (emf == null) {
      Map<String, String> propertyMap = new HashMap<String, String>();
      propertyMap.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
      emf = Persistence.createEntityManagerFactory(persistenceUnitName/*"cassandra_pu"*/, propertyMap);
    }
  }
  
  public HelloResponse save(HelloResponse response) {
    em = emf.createEntityManager();
    em.persist(response);
    em.close();    
    
    return response;
  }
  
  public List<HelloResponse> listAll() {
    em = emf.createEntityManager();
    Query q = em.createQuery("SELECT hr.nombre, hr.apellido, hr.email, hr.DNI FROM HelloResponse hr");
    List<HelloResponse> responses = q.getResultList();
    
    return responses;
  }
  
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

  public List<HelloResponse> findByDNI(String dni) {
    em = emf.createEntityManager();
    Query q = em.createQuery("SELECT hr.nombre, hr.apellido, hr.email, hr.DNI FROM HelloResponse hr where hr.DNI=:dni");
    q.setParameter("dni", dni);
    List<HelloResponse> responses = q.getResultList();
    
    return responses;
  }  

}
