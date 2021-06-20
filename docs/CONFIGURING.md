# How to Configure This Library

To flexibly support various applications, this library is very configuration driven. A handful of configuration properties control everything that this library does.

## Property Location

All properties should be placed in a file called `oauth2.properties` at the root of the classpath. Also, environment variables with the expected keys will override the properties file.

## Standard OAuth2 Properties

This project supports all the properties from `oauth2-utils`. Please see that project for details about its properties.

## Logging

SLF4J is used in this library. The following loggers need to be exposed to see the logging:

```
io.craigmiller160.oauth2
io.craigmiller160.jaxrs.oauth2
```