Spring Cloud Zone Affinity
------------------------------------------------------------------------------------------------------------------------------------------


This document will guide you through the process to setup zone affinity in Spring Cloud Netflix Eureka.

What You Will Build
You will build three applications:

**zuul server** - Spring Cloud Netflix Zuul

**eureka server** - Spring Cloud Netflix Eureka

**micro Service** - Spring Cloud

All those are necessary to make sure that our zone affinity setup is correct. Each of them will be deployed twice, one per zone.

**Pre-Req**

JDK 1.8

Text editor or your favorite IDE

Maven 3.0+

**Zone Affinity**

It doesn’t matter which kind of architectural style the application is using, it’s a common use case to have the same application deployed in different regions/data centers and use some technique to keep the requests within the same zone.

In microservices architecture, there’s also a need to achieve the same thing but the technique needs to be applied using the Service Registry Design Pattern.

------------------------------------------------------------------------------------------------------------------------------------------
**Build & Run**
It’s time to build the applications; if you are building the application using maven (like I did), just build them executing:

$ mvn clean package
Right after that just run each application adding the specific profile to the command line, e.g:

$ java -jar target/*.jar --spring.profiles.active=zone1

Remember that you need to run each application twice, once each profile: zone1 and zone2.
------------------------------------------------------------------------------------------------------------------------------------------
**Validation**
To validate if the requests are respecting each zone we need to make a request to the simple-service through each zuul server.

$ curl http://localhost:8765/zone-service/zone

{"zone"="zone1"}

$ curl http://localhost:8766/zone-service/zone

{"zone"="zone2"}


The difference between each zone here is the server.port.
------------------------------------------------------------------------------------------------------------------------------------------
**Zone Failover Validation**
To validate the failover between zones you just need to stop one of the instances and make a request to the opposite zone, e.g:

Stop zone-service on zone2.
Make a request to zone-service through the zuul on zone2.

$ curl http://localhost:8766/zone-service/zone

The expected result now will be a JSON containing {"zone"="zone1"}.
Once the zone-service for zone1 is up, running and registered in Eureka Server the same curl has to respond {"zone"="zone2"} again.

------------------------------------------------------------------------------------------------------------------------------------------



**Reference:**
https://cloud.spring.io/spring-cloud-netflix/multi/multi__service_discovery_eureka_clients.html


