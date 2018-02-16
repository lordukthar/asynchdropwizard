# asynchdropwizard
managed asynch rxjava and dropwizard

Create a shell with dummy service returning JSON and delay 5 seconds and log, done

Add liquebase done

Add proper test with Mockito not done

protect post/put/delete resource with basic auth and filter handling done

Add header 

uid:chief-wizard/password:secret and add that Auth header when doing post/put/delete

inject values into config depending on environment done

add GUI


START

Migrate

db migrate config/config.yml

Run server with program parameters

server config/config.yml


GET
http://localhost:8080/team-service/v1/player/1

Returns
{
    "name": "Superman",
    "age": 25,
    "male": true
}

POST
http://localhost:8080/team-service/v1/player/
with BODY
{
    "name": "Superman",
    "age": 25,
    "male": true
}
