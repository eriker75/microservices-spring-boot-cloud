### Aplicación Java utilizando arquitectura de microservicios y Spring Boot Cloud ###

**Modulos que componenen la aplicación**


**Levantando la aplicación**




### Trazabilidad de los microservicios con Spring Cloud Sleuth y Monitoreo con Zipkin ###

**TraceId**: identificador asociado a la petición que viaja entre los microservicios
**SpanId**: identificador de la unidad de trabajo de cada llamada a un microservicio


Un trace está formado por un conjunto de span.

**Modelo de un log:**

INFO[microservicio, traceID, spanID, <Boolean> exportar a Zipkin]

INFO[servicio-oauth, 8a40fc93ab4385007,f72188520726632d,false]


### TODO ###

[] Crear un docker-compose.yml y ubicarlo dentro del módulo de microservicio-productos
[] Instalar MySQL usando Docker - version 8 / buscar imagen oficial

```docker-compose build```


[] Crear un docker-compose.yml y ubicarlo dentro del módulo de microservicio-usuarios
[] Instalar PostgreSQL usando Docker - version 10.9 
```docker-compose build```


[] Entrar al contenedor y crear base de datos
o ejecutar un comando desde afuera del contenedor:

 ```docker exec -it [CONTAINER-NAME OR CONTAINER-ID] bash ```

[] Modificar servicio-productos-desarrollo.properties con la IP del contenedor. 
``` docker inspect [CONTAINER-NAME OR CONTAINER-ID] ```

[] Modificar servicio-usuarios-desarrollo.properties con la IP del contenedor. 
``` docker inspect [CONTAINER-NAME OR CONTAINER-ID] ```

[] Instalar herramienta visual - Workbench y configurar la IP del contenedor
[] Instalar herramienta visual pgAadmin y configurar la IP del contenedor

### Investigación ### 

[] Almacenar estructura del proyecto en un contenedor?
[] Instalar y configurar portainer, verificando el estado de los contenedores
[] Entender persistencia de los datos
[] ¿Cómo llevar la estructura del proyecto a la nuven AWS? Documentar el proceso
[] ¿Entender y configurar Kubernete y alternativas para la orquestación de los contenedores?


