package io.confluent.rest.examples.helloworld.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "hello", schema = "evo@evo_pu")
public class HelloResponse {
  @JsonProperty
  @NotNull
  @Column(name="nombre")
  private String nombre;
  @JsonProperty
  @NotNull
  @Column(name="apellido")
  private String apellido;
  @JsonProperty
  @NotNull
  @Id
  @Column(name="dni")
  private String DNI;
  @JsonProperty
  @NotNull
  @Column(name="email")
  private String email;
  
  public HelloResponse() { /* Jackson deserialization */ }

  public HelloResponse(String nombre, String apellido, String DNI, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.DNI = DNI;
    this.email = email;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getDNI() {
    return DNI;
  }

  public void setDNI(String dNI) {
    DNI = dNI;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



}
