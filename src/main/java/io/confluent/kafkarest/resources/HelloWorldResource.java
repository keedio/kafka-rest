/**
 * Copyright 2014 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.confluent.kafkarest.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import io.confluent.kafkarest.Context;
import io.confluent.rest.annotations.PerformanceMetric;
import io.confluent.rest.examples.helloworld.dao.HelloWorldDao;
import io.confluent.rest.examples.helloworld.entity.HelloResponse;

@Path("/hello")
@Produces("application/vnd.hello.v1+json")
public class HelloWorldResource {

  private final Context ctx;  
  HelloWorldDao dao;

  public HelloWorldResource(Context ctx) {
    this.ctx = ctx;
    this.dao = new HelloWorldDao(ctx.getConfig().getString("db"));
  }

  @GET
  @Path("/hello")
  public String sayHello(@javax.ws.rs.core.Context SecurityContext sc) {
    
    String user = sc.getUserPrincipal().getName();
    
    return "Hola " + user;
  }
  
  @GET
  @Path("/find")
  @PerformanceMetric("hello-find")
  public List<HelloResponse> find() {
    // Use a configuration setting to control the message that's written. The name is extracted from
    // the query parameter "name", or defaults to "World". You can test this API with curl:
    // curl http://localhost:8080/hello
    //   -> {"message":"Hello, World!"}
    // curl http://localhost:8080/hello?name=Bob
    //   -> {"message":"Hello, Bob!"}
    return dao.listAll();
  }
  
  @GET
  @Path("/find/{dni}")
  @PerformanceMetric("hello-with-name")
  public HelloResponse findByName(@PathParam("dni") String dni) {
    // Use a configuration setting to control the message that's written. The name is extracted from
    // the query parameter "name", or defaults to "World". You can test this API with curl:
    // curl http://localhost:8080/hello
    //   -> {"message":"Hello, World!"}
    // curl http://localhost:8080/hello?name=Bob
    //   -> {"message":"Hello, Bob!"}
    return dao.findByDNI(dni);
  }

  /*
  @GET
  @Path("/find/{name}/{surname}")
  @PerformanceMetric("hello-with-name-surname")
  public List<HelloResponse> findByNameAndSurname(@PathParam("name") String name, @PathParam("surname") String surname) {
    // Use a configuration setting to control the message that's written. The name is extracted from
    // the query parameter "name", or defaults to "World". You can test this API with curl:
    // curl http://localhost:8080/hello
    //   -> {"message":"Hello, World!"}
    // curl http://localhost:8080/hello?name=Bob
    //   -> {"message":"Hello, Bob!"}
    return dao.findByNameAndSurname(name, surname);
  }
  */
  
  @PUT
  @Path("/save")
  @Consumes(MediaType.APPLICATION_JSON)
  @PerformanceMetric("save")
  public HelloResponse save(HelloResponse input) {
    
    dao.save(input);
    
    return input;
     
  }
  
}
