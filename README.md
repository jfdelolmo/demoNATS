# Demo NATS 

### Reference Documentation
For further reference, please consider the following:

* [NATS Official documentation](https://docs.nats.io/)

### NATS Server

Check the [NATS official Docker image](https://hub.docker.com/_/nats)

The server is started executing:

``` docker run -d --name nats-main -p 4222:4222 -p 6222:6222 -p 8222:8222 nats```

The server 'admin' console can be accessed at:
    
``` http://localhost:8222 ```

### Testing this application

Once the NATS server is up and the application has been started:
- To send a message to the queue:    
    ``` curl -X POST -d "message=HOOOOLA" http://localhost:8080/demo/send ```      
- To retrieve the messages from the queue:
    ``` curl -X GET http://localhost:8080/demo/read ```
    
### Additional Links
These additional references should also help you:

* [Microservice messaging: The event that changed everything](https://resgate.io/blog/the-event/)
* [NATS java-client](https://github.com/nats-io/nats.java/releases/tag/2.8.0)
* [Baeldung examples](https://www.baeldung.com/nats-java-client)
* [NATS spring-nats](https://github.com/nats-io/spring-nats)