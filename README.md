### Aplicación Java utilizando arquitectura de microservicios y Spring Boot Cloud ###

**Modulos que componenen la aplicación**

- springboot-servicio-commons: Almacena la entidad Producto que es compartido entre los microservicios de Producto y de Item
- springboot-servicio-config-server: utiliza Spring Cloud Config y concentra las configuraciones compartidas por todos los microservicios @EnableConfigServer
- springboot-servicio-eureka-server: utiliza Spring Cloud Eureka se encarga de registrar los microservicios se define en @EnableEurekaServer en nuestro @SpringBootApplication
- springboot-servicio-item: 
- springboot-servicio-oauth: Utiliza Spring OAuth con Spring Security para la parte de Autenticación y Autorización.
- springboot-servicio-productos:
- springboot-servicio-usuarios:
- springboot-servicio-usuarios-commons: Almacena entidades Usuario y Role compartidos por los microservicios Usuario y OAuth
- springboot-servicio-zuul-server:


**Levantando el ambiente**

```
docker-compose -f docker/postgresql/docker-compose.yml up -d
docker-compose -f docker/zipkin/docker-compose up -d
docker-compose -f docker/zipkin/docker-compose.yml up -d
docker-compose -f docker/rabbitmq/docker-compose.yml up -d
```


- RabbitMQ: localhost:15672
- Zipkin: localhost:9411
- PgAdmin: http://localhost:16543
- EurekaServer: http://localhost:8761
- RabbitMQ: http://localhost:15672/


### RabbitMQ - Middleware de mensajería que utiliza protocolo AMQP ###

**AMQP** es un protocolo de estándar abierto en la capa de aplicaciones de un sistema de comunicación orientado a mensajes, encolamiento (queueing), enrutamiento (punta a punta con PubSub), exactitud y seguridad.

RABBITMQ_USERNAME: user
RABBITMQ_PASSWORD: bitnami


### Trazabilidad de los microservicios con Spring Cloud Sleuth y Monitoreo con Zipkin ###

**TraceId**: identificador asociado a la petición que viaja entre los microservicios
**SpanId**: identificador de la unidad de trabajo de cada llamada a un microservicio


Un trace está formado por un conjunto de span.

**Modelo de un log:**

INFO[microservicio, traceID, spanID, <Boolean> exportar a Zipkin]

INFO[servicio-oauth, 8a40fc93ab4385007,f72188520726632d,false]



### ROADMAP ### 

[] Crear tarea que se encarga de descargar todas las dependencias y correr mvn spring-boot:run
[] Escribir pruebas unitarias y de integración dentro del contexto de microservicios
[] Crear frontend usando Angular que consuma de nuestra estructura
[] Deployar aplicación en Amazon AWS

