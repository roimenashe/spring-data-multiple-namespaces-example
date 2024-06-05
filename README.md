# Spring Data Aerospike: Multiple Namespaces Example

The goal of this example is to demonstrate how to use multiple namespaces
with Spring Data Aerospike.

Using a single namespace with Spring Data Aerospike is pretty straight forward,
Configuration comes out-of-the-box, all you need to do is to extend `AbstractAerospikeDataConfiguration` class and
override two methods: `getHosts()` and `nameSpace()`.
You can see an example of using a single namespace in [Simple Web Application Using Java, Spring Boot, Aerospike and Docker
](https://medium.com/aerospike-developer-blog/simple-web-application-using-java-spring-boot-aerospike-database-and-docker-ad13795e0089).

Using multiple namespaces with Spring Data Aerospike requires a bit of additional code. Create a configuration class 
per namespace, and in each configuration class do the following: 
* extend `AbstractAerospikeDataConfiguration`
* override both `getHosts()` and `nameSpace()`
* create an `AerospikeTemplate` bean (with unique bean name and method name)
* point the `aerospikeTemplateRef`  property of the `@EnableAerospikeRepositories` annotation to the name of the 
newly created `AerospikeTemplate` bean.

You can see a fully working example in this repository.

### Version

The Spring Data Aerospike versions that support multiple namespaces are `3.1.0` and above.
Try to use the latest version whenever possible.

```
<dependency>
    <groupId>com.aerospike</groupId>
    <artifactId>spring-data-aerospike</artifactId>
    <version>4.8.0</version>
</dependency>
```

### Multiple repositories in a package

If both repositories are located in the same package - same as in this example (`AerospikeProductsRepository` and `AerospikeUsersRepository` are both part of the `repositories` package), then you need to use the `includeFilters` field
of the `@EnableAerospikeRepositories` annotation on your configuration class pointing to the relevant repository using customized annotations (`ProductsRepository` and `UsersRepository`).


### Upgrading to Spring 3

Please note that after upgrading to Spring 3 each of multiple `AerospikeTemplate` beans must contain method with a unique name which should not be called "aerospikeTemplate". Here is a simple example of a correct bean:

    @Bean(name = "aerospikeTemplateUsers")
    public AerospikeTemplate aerospikeTemplateUsers(
