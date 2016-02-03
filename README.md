# karyon2-hystrix-stream

[![Build Status](https://travis-ci.org/scm-spain/karyon2-hystrix-stream.svg)](https://travis-ci.org/scm-spain/karyon2-hystrix-stream)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.scmspain.karyon/karyon2-hystrix-stream/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.scmspain.karyon/karyon2-hystrix-stream)

This a module for the Netflix framework [Karyon](https://github.com/Netflix/karyon) which adds an endpoint /hystrix.stream [GET] that uses HystrixMetricsStreamHandler.
It works close to [karyon-rest-router](https://github.com/scm-spain/karyon-rest-router)

## Documentation

Simply add this module into Modules specification for your AppServer.

```
@KaryonBootstrap(name = "AppServer")
@Modules(include = {
    ...
    HystrixStreamEndPointModule.class
})
public interface AppServerForTesting {
    ...
}
```

See AppServerForTesting for further details and a functional implementation.

## Gradle

Add dependency as follows:

```
    compile 'com.scmspain.karyon:karyon2-hystrix-stream:0.1.0'
```

Please look at the badge from maven central to know which is the latest version for this module.

## AppServer.properties

Make sure you do not set a too restrictive base package in order to get HystrixStreamController also included and enabled.

```
com.scmspain.karyon.rest.property.packages=com.scmspain
```
