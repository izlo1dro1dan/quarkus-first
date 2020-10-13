package org.acme.commandmode.vertx;

import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class UserResource {

    @Inject
    PgPool client;

    @Inject
    @ConfigProperty(name = "myapp.schema.create",defaultValue = "true")
    boolean schemaCreate;

    @PostConstruct
    void config(){
        if(schemaCreate){
            initdb();
        }
    }

    private void initdb(){
        client.query("DROP TABLE IF EXISTS user").execute()
                .flatMap(r -> client.query("CREATE TABLE user (id SERIAL PRIMARY KEY, first_name TEXT NOT NULL,last_name TEXT NOT NULL,mobile_phone TEXT NOT NULL,password TEXT NOT NULL)").execute())
                .flatMap(r -> client.query("INSERT INTO user (first_name,last_name,mobile_phone,password) VALUES ('Alex','Alex','+77232','Alex')").execute())
                .flatMap(r -> client.query("INSERT INTO user (first_name,last_name,mobile_phone,password) VALUES ('Mark','Mark','+77232','Mark')").execute())
                .flatMap(r -> client.query("INSERT INTO user (first_name,last_name,mobile_phone,password) VALUES ('Akzhol','Akzhol','+77232','Akzhol')").execute())
                .await().indefinitely();
    }
}
