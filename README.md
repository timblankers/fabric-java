Under construction, nothing works.

----------------------------------

# Fabric Java

To build the peer, run in this directory:

```
$ ./gradlew installDist
```

This creates the script `fabric-client`.

To launch a regular Go Fabric peer, first run:

```
$ docker-compose up
```

Then try the java peer:

```
$ ./build/install/examples/bin/fabric-client
```

## Maven

If you prefer to use Maven:
```
$ mvn verify
$ mvn exec:java -Dexec.mainClass=io.grpc.fabric.FabricClient
```
