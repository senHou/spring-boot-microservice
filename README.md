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
