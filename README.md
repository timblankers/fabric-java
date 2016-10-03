Under construction, nothing works.

----------------------------------

# Fabric Java

To build the peer, run in this directory:

```
$ ./gradlew installDist
```

This creates the script `hello-world-client`.

To launch a regular Go Fabric peer, first run:

```
$ docker-compose up
```

Then try the java peer:

```
$ ./build/install/examples/bin/hello-world-client
```

That's it!

Please refer to gRPC Java's [README](../README.md) and
[tutorial](http://www.grpc.io/docs/tutorials/basic/java.html) for more
information.

## Maven

If you prefer to use Maven:
```
$ mvn verify
$ mvn exec:java -Dexec.mainClass=io.grpc.examples.helloworld.HelloWorldClient
```
