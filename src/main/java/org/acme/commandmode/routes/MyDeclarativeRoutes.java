package org.acme.commandmode.routes;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyDeclarativeRoutes {
    @Route(methods = HttpMethod.GET)
    void getUsers(RoutingContext rc){
        rc.response().end("hello");
    }
    @Route(path = "/world")
    String helloWorld(){
        return "Hello world";
    }
    @Route(path = "/greetings", methods = HttpMethod.GET)
    void greetings(RoutingExchange ex) {
        ex.ok("hello " + ex.getParam("name").orElse("world"));
    }
}
