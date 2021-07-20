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

## Add SqlConnectionProviderFactory Binding

The `SqlConnectionProvider` interface needs a functional implementation so that a SQL connection can be provided to code within this library. This should be provided by an hk2 factory:

```
class SqlConnectionProviderFactory : Factory<SqlConnectionProvider> {
    override fun provide(): SqlConnectionProvider = SqlConnectionProvider { AppCore.dataSource.getConnection() }

    override fun dispose(provider: SqlConnectionProvider?) {}
}
```

It then needs to be bound in the consuming application's `Binder` class:

```
bindFactory(SqlConnectionProviderFactory::class.java)
                .to(SqlConnectionProvider::class.java)
```

## Add Path to Resource

For JAX RS resource scanning, the path to the resources needs to be added:

```
io.craigmiller160.jaxrs.oauth2.resource
```