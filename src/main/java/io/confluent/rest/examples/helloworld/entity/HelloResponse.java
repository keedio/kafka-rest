package io.confluent.rest.examples.helloworld.entity;



import com.fasterxml.jackson.annotation.JsonProperty;


public class HelloResponse {
  @JsonProperty
  private String nombre;
  @JsonProperty
  private String apellido;
  @JsonProperty

  private String DNI;

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
