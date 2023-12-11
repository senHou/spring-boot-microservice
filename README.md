# spring-boot-microservice
A SpringBoot Microservice Example

To use openfeign for api exchange, do the following:
1. add the dependency
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-openfeign</artifactId>
   </dependency>
2. add @EnableFeignClients in spring application java class
3. create a proxy class
4. add @FeignClient(name="currency-exchange") above class name. Please note the name should be the same a the applicaiton name that defined in application.properties file
   @FeignClient(name="currency-exchange", url = "localhost:8000")
   public interface CurrencyExchangeProxy {
		@GetMapping("/currency-exchange/from/{from}/to/{to}")
		public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
	}

Add Naming Server
1. create naming server application
2. add the dependency 
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
	</dependency>
3. add @EnableEurekaServer in spring application java class
4. config other service to connect to naming Server by adding dependency to pom.xml file
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>

Add API Gateway
1. create api gateway project
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-gateway</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>	
2. add spring.cloud.gateway.discovery.locator.enabled=true in application properties file
3. spring.cloud.gateway.discovery.locator.lowerCaseServiceId=true to change the url to lowercase

we can also define our own router. 
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
                .build();
    }

Add resilience4j
<dependency>
	<groupId>io.github.resilience4j</groupId>
	<artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>

resilience4j.retry.instances.sample-api.maxAttempts=5
	
