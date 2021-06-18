# How to Integrate This Library

These are the instructions for how to integrate this library into any SpringBoot project.

## TLS Certificate

Because the application using this library will be communicating with the OAuth2 Auth Server, the TLS public key from that application will need to be added to a TrustStore in the application.

## Add Dependency

This library should be installed locally (unless it gets published to a repo at some point). Then it can be added to the pom.xml.

```
<dependency>
    <groupId>io.craigmiller160</groupId>
    <artifactId>jaxrs-oauth2-utils</artifactId>
    <version>${oauth2.utils.version}</version>
</dependency>
```

## Add Binder

In a JAX-RS Application implementation (such as Jersey's ResourceConfig), the OAuth2InjectionBinder needs to be added:

```
register(OAuth2InjectionBinder())
```